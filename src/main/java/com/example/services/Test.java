package com.example.services;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;

import com.example.model.Recipient;

public class Test {

    /**
     * Starting point for the SAAJ - SOAP Client Testing
     */
    public static void main(String args[]) {
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            String url = "http://10.0.1.54:8080/nl/jsp/soaprouter.jsp";
            SOAPMessage soapResponse = soapConnection.call(createMessage("___3EC80790-8271-42EE-9BA2-248E76DB3696"), url);

            //___56027C08-AF60-4D2C-8E21-8BD20F342E95
            // Process the SOAP Response
            printSOAPResponse(soapResponse);
            
            Node recipientNode = soapResponse.getSOAPBody().getFirstChild().getFirstChild().getFirstChild();
            JAXBContext jc = JAXBContext.newInstance(Recipient.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            Recipient recipient = (Recipient) unmarshaller.unmarshal(recipientNode);
            
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
        }
    }
    
    private static SOAPMessage createMessage(String sessionToken) throws SOAPException{
		MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        SOAPEnvelope envelope = soapPart.getEnvelope();

        SOAPBody soapBody = envelope.getBody();
        SOAPElement executeQueryNode = soapBody.addChildElement("ExecuteQuery");
        SOAPElement sessionNode = executeQueryNode.addChildElement("sessiontoken");
        sessionNode.addTextNode(sessionToken);
        SOAPElement entity = executeQueryNode.addChildElement("entity");
        SOAPElement queryDef = entity.addChildElement("queryDef");
        queryDef.addAttribute(new QName("operation"), "count");
        queryDef.addAttribute(new QName("schema"), "nms:recipient");
        queryDef.addAttribute(new QName("xtkschema"), "xtk:queryDef");
        SOAPElement select = queryDef.addChildElement("select");
        SOAPElement emailNode = select.addChildElement("node");
        emailNode.addAttribute(new QName("expr"), "@email");
        //lastName
        SOAPElement lastNameNode = select.addChildElement("node");
        lastNameNode.addAttribute(new QName("expr"), "@lastName");
        //firstName
        SOAPElement firstName = select.addChildElement("node");
        firstName.addAttribute(new QName("expr"), "@firstName");
        //tonariwaId
        SOAPElement tonariwaId = select.addChildElement("node");
        tonariwaId.addAttribute(new QName("expr"), "@tonariwaid");
        
        //where
        SOAPElement whereElement = queryDef.addChildElement("where");
        SOAPElement condition = whereElement.addChildElement("condition");
        condition.addAttribute(new QName("expr"), "@email <> ''");
        
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", "xtk:queryDef#ExecuteQuery");
        soapMessage.saveChanges();

        return soapMessage;
	}
    
    
    /**
     * Method used to print the SOAP Response
     */
    public static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        System.out.print("\nResponse SOAP Message = ");
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);
    }

}