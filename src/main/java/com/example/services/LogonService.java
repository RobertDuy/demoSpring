package com.example.services;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import com.example.model.LogonResponse;
import com.example.services.Interfaces.LogonInterface;

@Component("LogonService")
public class LogonService implements LogonInterface{
	public static final String END_POINT = "http://10.0.1.54:8080/nl/jsp/soaprouter.jsp";

	@Override
	public String getSessionToken(String username, String password) {
		String token = "";
		try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), END_POINT);
            Node logonResponseNode = soapResponse.getSOAPBody().getFirstChild();
            JAXBContext jc = JAXBContext.newInstance(LogonResponse.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            LogonResponse logonResponse = (LogonResponse) unmarshaller.unmarshal(logonResponseNode);
            token = logonResponse.getPstrSessionToken();
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
        }
		return token;
	}
	
	private SOAPMessage createSOAPRequest() throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = "urn:xtk:session";
        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("urn", serverURI);

        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("Logon", "urn");
        soapBodyElem.addChildElement("sessiontoken", "urn");
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("strLogin", "urn");
        soapBodyElem2.addTextNode("admin");
        SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("strPassword", "urn");
        soapBodyElem3.addTextNode("$1$abcd#adm");
        soapBodyElem.addChildElement("elemParameters", "urn");

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", "xtk:session#Logon");
        soapMessage.saveChanges();

        System.out.print("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }

}
