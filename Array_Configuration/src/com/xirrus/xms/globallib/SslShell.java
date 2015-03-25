	package com.xirrus.xms.globallib;

	import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SslShell {


	/**
	 * Base class for SSH Shell objects. Provides methods to set up and close an SSH session and shell. Provides send and
	 * expect commands, abstracting away the underlying stdin and stdout streams.
	 * 
	 * @author Kartik Aiyar
	 * 
	 */

		public static final String TERM_TYPE = "xterm";
		
		public static final int TERM_WIDTH_COLS = 1000;
		public static final int TERM_WIDTH_PIXELS = 1024000;
		public static final int TERM_HEIGHT_ROWS = 1000;
		public static final int TERM_HEIGHT_PIXELS = 1024000;

		public static final String DEFAULT_SSH_PORT = "22";
		
		private static final int CONNECT_TIMEOUT = 10000;

		static final long DEFAULT_EXPECT_TIMEOUT = 30000L;
		static final long DEFAULT_TIMEOUT = 250L;
		static final long CHECK_MILLIS = 10L * 1000L;

		static final String READ_START_SYMBOL = "[RECEIVE_START]";
		static final String READ_STOP_SYMBOL = "[RECEIVE_END]";
		static final String WRITE_START_SYMBOL = "[SEND]";

		final byte[] stdOutBuffer = new byte[8192];
		final byte[] stdErrBuffer = new byte[8192];

		String statusString = "";

		JSch jsch = new JSch();
		Session session = null;
		Channel channel = null;

		String hostname = null;
		String port = null;
		String username = null;
		String password = null;

		boolean consoleLog = true;

		InputStream stdout = null;
		OutputStream stdin = null;
		BufferedWriter bufferedWriter = null;

		StringBuilder stringBuilder = null;
		String expectString = "";
		MatchResult matchResult = null;
		String lastExpectOutput = "";
		
		int authenticationAttempts = 0;

		public int getAuthenticationAttempts() {
			return authenticationAttempts;
		}

//		private static final Logger logger = Logger.getLogger(BackupProviderLinuxApplianceImpl.class.getName());

		public SslShell (String hostname , String port, String username, String password) {
			this.hostname = hostname;
			this.port = port;
			this.username = username;
			this.password = password;
		}

		/**
		 * Provides a password authenticated SSH connection.
		 * 
		 * @return true if connected, false if connection could not be made.
		 */
		public boolean connect () {
			try {
//				log(Level.INFO, "In connect().");
				if ((null == hostname) || (null == port)) {
					statusString = "Hostname or port was not set.";
					return false;
				}
				try {
					/* Create a session */
//					log(Level.INFO, "Constructing new session to " + username + "@" + hostname + ":" + port + ".");
					session = jsch.getSession(username, hostname, Integer.valueOf(port));
					session.setPassword(password);
					session.setConfig("StrictHostKeyChecking", "no");

					 /* Now connect */
//					log(Level.INFO, "Invoking connection.connect().");
					session.connect();
				} catch (final JSchException e) {
//					log(Level.INFO, "Failed to connect via SSH: " + e.toString());
					statusString = "Failed to connect via SSH: " + e.getMessage();
					return false;
				}
//				log(Level.INFO, "Connect was successful.");

			} catch (final NumberFormatException e) {
//				log(Level.SEVERE, "NumberFormatException (port?): " + e.toString());
				statusString = "SSH Port is not a valid number.";
				return false;
			} catch (final Exception e) {
//				log(Level.SEVERE, "Exception: " + e.toString());
				statusString = "Connection exception ocurred.";
				return false;
			}
			return true;
		}

		/**
		 * Starts a session shell and sets up stdout and stdin streams.
		 * 
		 * @return true if shell session started, false if an IO exception occurred.
		 */
		public boolean startSession () {
			try {
//				log(Level.INFO, "session.openChannel(\"shell\")");
				channel = session.openChannel("shell");

				((ChannelShell)channel).setPtySize(TERM_WIDTH_COLS, TERM_HEIGHT_ROWS, TERM_WIDTH_PIXELS, TERM_HEIGHT_PIXELS);
				
//				log(Level.INFO, "channel.getInputStream()");
				
//				channel.setInputStream(System.in);
				stdout = channel.getInputStream();
				
				
//				log(Level.INFO, "channel.getOutputStream()");
				
				//channel.setOutputStream(System.out);
				stdin = channel.getOutputStream();
				
//				log(Level.INFO, "new BufferedWriter");
				bufferedWriter = new BufferedWriter(new OutputStreamWriter(stdin));
				
				channel.connect(CONNECT_TIMEOUT);

			
			} catch (JSchException e) {
//				log(Level.INFO, "JSchException in startSession: " + e.toString());
				return false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}

		/*
		 * Return whether string matched existing response.
		 */
		public boolean reread (final String match) {
			final Pattern p = Pattern.compile(match);
			final Matcher m = p.matcher(expectString);
			final boolean found = m.find();
			if (found) {
				matchResult = m.toMatchResult();
				return true;
			}
			return false;
		}

		/**
		 * Returns string used by the latest expect command.
		 * 
		 * @return String used by the last expect command.
		 */
		public String getExpectResponse () {
			return expectString;
		}

		/**
		 * Expect command that uses default timeout.
		 * 
		 * @param match
		 *            String to find in the response from the shell.
		 * @return true if match found, false if timeout occurred.
		 */
		public boolean expect (final String match) {
			return expect(match, DEFAULT_EXPECT_TIMEOUT);
		}

		/**
		 * Expect command attempts to match a string from the outgoing response of the shell.
		 * 
		 * @param match
		 *            String to find in the response from the shell.
		 * @param timeout
		 *            Milliseconds to time out reading response from shell.
		 * @return
		 */
		public boolean expect (final String match, final long timeout) {
			boolean found = false;
			final Pattern p = Pattern.compile(match);
			Matcher m = null;
			long stopTime = System.currentTimeMillis() + timeout;
			try {
				stringBuilder = new StringBuilder();

				while (!found) {
					final String output = getOutput();
					if (output != null && !output.isEmpty()) {
						stringBuilder.append(output);
						expectString = stringBuilder.toString();
						m = p.matcher(expectString);
						if (m.find()) {
							found = true;
							break;
						}
						stopTime = System.currentTimeMillis() + timeout;				
					}
					if (stopTime <= System.currentTimeMillis()) {
						break;
					} else {
						Thread.sleep(DEFAULT_TIMEOUT);
					}
				}
				lastExpectOutput = stringBuilder.toString();
			} catch (final IOException e) {
				lastExpectOutput = stringBuilder.toString();
			} catch (final Exception e) {
				lastExpectOutput = stringBuilder.toString();
			}
			return found;
		}

		/**
		 * Internal method of the expect command. Manages the stdout stream from the Array.
		 * 
		 * @return
		 * @throws IOException
		 */
		public String getOutput () throws IOException {

			int stdOutLen = 0;
			if (stdout.available() > 0) {
				stdOutLen = stdout.read(stdOutBuffer);
				if (stdOutLen > 0) {
//					log(Level.INFO, READ_START_SYMBOL);
//					log(Level.INFO, new String(stdOutBuffer, 0, stdOutLen));
//					log(Level.INFO, READ_STOP_SYMBOL);
				}
			}

			return new String(stdOutBuffer, 0, stdOutLen);
		}

		/**
		 * Commands are sent to the Array through sendLine.
		 * 
		 * @param command
		 *            String to send to the Array, with a line feed added.
		 */
		public void sendLine (final String command) {
			send(command + "\n");
		}

		/**
		 * Commands are sent to the Array through send.
		 * 
		 * @param command
		 *            String to send to the Array, as is.
		 */
		public void send (final String command) {
//			log(Level.INFO, WRITE_START_SYMBOL + command);

			expectString = "";
			try {
				bufferedWriter.write(command);
				bufferedWriter.flush();
			} catch (final IOException e) {
				return;
			}
		}

		/**
		 * @return Match result
		 */
		public MatchResult getMatchResult () {
			return matchResult;
		}

//		void log (final Level level, final String string) {
//			logger.info("(" + hostname + ")" + string);
//			if (consoleLog) {
//				//
//			}
//		}

		/**
		 * Session close and connection close.
		 */
		public void close () {
//			log(Level.INFO, "ArrayShell close.");
			if (null != channel) {
				channel.disconnect();
				channel = null;
			}
			if (null != session) {
				session.disconnect();
				session = null;
			}
		}

		@Override
		public  void finalize () {
			if (null != channel) {
				channel.disconnect();
//				log(Level.INFO, "ArrayShell finalize closed channel.");
			}
			if (null != session) {
				session.disconnect();
//				log(Level.INFO, "ArrayShell finalize closed session.");
			}
		}

		/**
		 * Takes length and returns string of asterisks of that length.
		 * 
		 * @param length
		 * @return
		 */
		static public String createAsteriskString (final int length) {
			final StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < length; i++) {
				buffer.append('*');
			}
			return buffer.toString();
		}

		public String getHostname () {
			return hostname;
		}

		public void setHostname (final String hostname) {
			this.hostname = hostname;
		}

		public String getPassword () {
			return password;
		}

		public void setPassword (final String password) {
			this.password = password;
		}

		public String getUsername () {
			return username;
		}

		public void setUsername (final String username) {
			this.username = username;
		}

		public void setConsoleLog (final boolean consoleLog) {
			this.consoleLog = consoleLog;
		}

		public String getStatusString () {
			return statusString;
		}

		public String getPort () {
			return port;
		}

		public void setPort (final String port) {
			this.port = port;
		}
		
		public String getLastExpectOutput() {
			return lastExpectOutput;
		}
		
		public Session getSession() {
			return session;
		}


}
