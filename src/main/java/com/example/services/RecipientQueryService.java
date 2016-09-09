package com.example.services;

import java.util.List;

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

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import com.example.criteria.Criteria;
import com.example.criteria.Operator;
import com.example.model.Recipient;
import com.example.model.RecipientCollection;
import com.example.services.Interfaces.QueryInterface;

@Component("recipientQueryService")
public class RecipientQueryService implements QueryInterface<Recipient, Criteria>{
	public static final String GET_ONE = "get";
	public static final String GET_MUTIPLE = "select";
	public static final String GET_COUNT = "count";

	@Override
	public int getCount(String sessionToken, Criteria criteria) {
		try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createMessage(sessionToken, GET_COUNT, criteria), LogonService.END_POINT);
            Node recipientNode = soapResponse.getSOAPBody().getFirstChild().getFirstChild().getFirstChild();
            JAXBContext jc = JAXBContext.newInstance(Recipient.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            Recipient recipient = (Recipient) unmarshaller.unmarshal(recipientNode);
            soapConnection.close();
            return recipient.getCount();
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
        }
		return 0;
	}

	@Override
	public Recipient getEntity(String sessionToken, Criteria criteria) {
		try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createMessage(sessionToken, GET_ONE, criteria), LogonService.END_POINT);
            Node recipientNode = soapResponse.getSOAPBody().getFirstChild().getFirstChild().getFirstChild();
            JAXBContext jc = JAXBContext.newInstance(Recipient.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            Recipient recipient = (Recipient) unmarshaller.unmarshal(recipientNode);
            soapConnection.close();
            return recipient;
        } catch (Exception e) {
            return new Recipient();
        }
	}
	
	@Override
	public List<Recipient> getListEntity(String sessionToken, Criteria criteria) {
		try {
			System.out.println("Access to Method #getListEntity");
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createMessage(sessionToken, GET_MUTIPLE, criteria), LogonService.END_POINT);
            Node recipientNode = soapResponse.getSOAPBody().getFirstChild().getFirstChild().getFirstChild();
            JAXBContext jc = JAXBContext.newInstance(RecipientCollection.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            RecipientCollection recipientCollection = (RecipientCollection) unmarshaller.unmarshal(recipientNode);
            soapConnection.close();
            return recipientCollection.getRecipient();
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
        }
		return null;
	}

	
	private SOAPMessage createMessage(String sessionToken, String mode, Criteria criteria) throws Exception{
		MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = "urn:xtk:queryDef";
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("urn", serverURI);

        SOAPBody soapBody = envelope.getBody();
        SOAPElement executeQueryNode = soapBody.addChildElement("ExecuteQuery", "urn");
        SOAPElement sessionNode = executeQueryNode.addChildElement("sessiontoken", "urn");
        sessionNode.addTextNode(sessionToken);
        SOAPElement entity = executeQueryNode.addChildElement("entity", "urn");
        SOAPElement queryDef = entity.addChildElement("queryDef");
        queryDef.addAttribute(new QName("operation"), mode);
        queryDef.addAttribute(new QName("schema"), "nms:recipient");
        queryDef.addAttribute(new QName("xtkschema"), "xtk:queryDef");
        SOAPElement select = queryDef.addChildElement("select");
        
        //email
        addToSelect(select, "@email");
        //lastName
        addToSelect(select, "@lastName");
        //firstName
        addToSelect(select, "@firstName");
        //tonariwaId
        addToSelect(select, "@tonariwaid");
        
        //where
        if(criteria != null && criteria.getListOperators()!=null && criteria.getListOperators().size() > 0
        		&& !criteria.getListOperators().get(0).getKey().isEmpty()){
        	SOAPElement whereElement = queryDef.addChildElement("where");
            for(int i = 0; i < criteria.getListOperators().size(); i++){
            	Operator operator = criteria.getListOperators().get(i);
            	SOAPElement condition = whereElement.addChildElement("condition");
            	String expresion = "";
            	if(i == criteria.getListOperators().size() - 1){
            		expresion = operator.getKey()+ " "+ operator.getOperand().getValue()+ " "+ operator.getValue();
            	}else{
            		expresion = "@"+ operator.getKey()+ " "+ operator.getOperand().getValue()+ " \'"+ operator.getValue()+"\'";
            	}
                condition.addAttribute(new QName("expr"), expresion);
            }
        }
        
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", "xtk:queryDef#ExecuteQuery");
        soapMessage.saveChanges();
        return soapMessage;
	}
	
	private void addToSelect(SOAPElement select, String attribute) throws SOAPException{
		SOAPElement emailNode = select.addChildElement("node");
        emailNode.addAttribute(new QName("expr"), attribute);
	}
}
