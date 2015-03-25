package com.xirrus.xms.globallib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.IpAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SnmpClient {

	String ipAddress;  
	String port = "161";
	String community;
	Snmp snmp;
	PDU pdu;
	OID oid;
	CommunityTarget comtarget;
	
	public SnmpClient (String targetIPAddress, String targetPort, String targetCommunity) throws IOException {
		ipAddress = targetIPAddress;
		port = targetPort;
		int snmpVersion = SnmpConstants.version2c;
		community = targetCommunity;
		
		TransportMapping transport = new DefaultUdpTransportMapping();
		transport.listen();
		snmp = new Snmp(transport);
		
		comtarget = new CommunityTarget();
		comtarget.setCommunity(new OctetString(community));
		comtarget.setVersion(snmpVersion);
		comtarget.setAddress(new UdpAddress(ipAddress + "/" + port));
		comtarget.setRetries(2);
		comtarget.setTimeout(1500);
	}
	
	public SnmpClient (String targetIPAddress, String community) throws IOException {
		this(targetIPAddress, "161", community);
	}
	

	public List<String> getSubtree (String oid, int maxResults) throws IOException {
		List <String> returnList = new ArrayList<String>();
		pdu = new PDU();
		this.oid = new OID(oid);
		
		pdu.add(new VariableBinding(this.oid));
		pdu.setType(PDU.GETBULK);
		pdu.setMaxRepetitions(maxResults);
		
		ResponseEvent response = snmp.getBulk(pdu, comtarget);
		VariableBinding[] bulkoutput = response.getResponse().toArray();
		for (int i = 0; i < bulkoutput.length ; i++) {
			String tempstring = bulkoutput[i].toString();
			if (tempstring.contains(oid.replaceAll("^.", "")))  {
				String[] splitarray = tempstring.split("= ");
				int length = splitarray.length;
				returnList.add(splitarray[length - 1]);
			}
		}
		
		return returnList;
		
	}
	
	public String get(String oid) throws IOException {
		pdu = new PDU();
		this.oid = new OID(oid);
		
		pdu.add(new VariableBinding(this.oid));
		pdu.setType(PDU.GET);
		
		ResponseEvent response = snmp.get(pdu, comtarget);
		String[] oidresponse =  response.getResponse().toArray()[0].toString().split("= ");
		return oidresponse[oidresponse.length - 1];
		
	}
	
	public String set(String oid, String value) throws IOException  {
		pdu = new PDU();
		this.oid = new OID(oid);
		
		Variable var = new OctetString(value);
		VariableBinding varBind = new VariableBinding(this.oid, var);
		pdu.add(varBind);
		
		pdu.setType(PDU.SET);
		
		ResponseEvent response = snmp.send(pdu, comtarget);
		String[] oidresponse =  response.getResponse().toArray()[0].toString().split("= ");
		return oidresponse[oidresponse.length - 1];
	}
	
	public String set(String oid, int value) throws IOException  {
		pdu = new PDU();
		this.oid = new OID(oid);
		
		Variable var = new Integer32(value);
		VariableBinding varBind = new VariableBinding(this.oid, var);
		pdu.add(varBind);
		
		pdu.setType(PDU.SET);
		
		ResponseEvent response = snmp.send(pdu, comtarget);
		String[] oidresponse =  response.getResponse().toArray()[0].toString().split("= ");
		return oidresponse[oidresponse.length - 1];
	}
	
	public String setIPAddress(String oid, String value) throws IOException {
		pdu = new PDU();
		this.oid = new OID(oid);
		
		Variable var = new IpAddress(value);
		VariableBinding varBind = new VariableBinding(this.oid, var);
		pdu.add(varBind);
		
		pdu.setType(PDU.SET);
		
		ResponseEvent response = snmp.send(pdu, comtarget);
		String[] oidresponse =  response.getResponse().toArray()[0].toString().split("= ");
		return oidresponse[oidresponse.length - 1];
	}
	
//	public static void main(String[] args) throws IOException {
//		SnmpClient client = new SnmpClient("10.100.55.132", "tornado");
//		client.setIPAddress(".1.3.6.1.4.1.21013.1.2.10.2.0", "1.1.11.1");
////		System.out.println(client.getSubtree(".1.3.6.1.4.1.21013.1.2.12.1.1.1.9", 22).get(2));
//		System.out.println(client.get(".1.3.6.1.4.1.21013.1.2.12.1.1.1.9.3"));
//		
//		System.out.println(client.set(".1.3.6.1.4.1.21013.1.2.12.1.1.1.5.1", 0));
//	}
	
}
