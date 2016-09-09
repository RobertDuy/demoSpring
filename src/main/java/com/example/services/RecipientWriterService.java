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
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.w3c.dom.Node;

import com.example.model.Recipient;
import com.example.model.WriteCollectionResponse;
import com.example.services.Interfaces.WriterInterface;

@Component("recipientWriterService")
public class RecipientWriterService implements WriterInterface<Recipient>{

	@Override
	public boolean writeRecipient(String sessionToken, List<Recipient> recipients, String folderId) {
		try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            tryToInsertAlllistCompany(soapConnection, sessionToken, recipients);
            
            // try to call insert Recipient
            SOAPMessage soapResponse = soapConnection.call(createMessage(sessionToken, recipients, folderId), LogonService.END_POINT);
            soapResponse.writeTo(System.out);
            
            Node writeCollectionResponse = soapResponse.getSOAPBody().getFirstChild();
            JAXBContext jc = JAXBContext.newInstance(WriteCollectionResponse.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            unmarshaller.unmarshal(writeCollectionResponse);
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
            return false;
        }
		return true;
	}
	
	private void tryToInsertAlllistCompany(SOAPConnection soapConnection, String sessionToken, List<Recipient> recipients) throws Exception{
		MessageFactory messageFactory = MessageFactory.newInstance();
        for (Recipient recipient : recipients) {
        	if(!StringUtils.isEmpty(recipient.getCompany())){
        		SOAPMessage soapMessage = messageFactory.createMessage();
                SOAPPart soapPart = soapMessage.getSOAPPart();
                SOAPEnvelope envelope = soapPart.getEnvelope();

                SOAPBody soapBody = envelope.getBody();
                SOAPElement writeCollectionNode = soapBody.addChildElement("Write");
                writeCollectionNode.addAttribute(new QName("xmlns"), "urn:xtk:persist");
                SOAPElement sessionNode = writeCollectionNode.addChildElement("sessiontoken");
                sessionNode.addTextNode(sessionToken);
                SOAPElement domDoc = writeCollectionNode.addChildElement("domDoc");
        		SOAPElement companyNode = domDoc.addChildElement("company");
                companyNode.addAttribute(new QName("xtkschema"), "crm:company");
                companyNode.addAttribute(new QName("name"), recipient.getCompany());
                companyNode.addAttribute(new QName("lowerName"), recipient.getCompany().toLowerCase());
                companyNode.addAttribute(new QName("_key"), "@name");
                companyNode.addAttribute(new QName("_operation"), "insert");
                
            	MimeHeaders headers = soapMessage.getMimeHeaders();
                headers.addHeader("SOAPAction", "xtk:persist#Write");
                headers.addHeader("Operation", "Write");
                soapMessage.saveChanges();
                // just call try to insert new company
                soapConnection.call(soapMessage, LogonService.END_POINT);
        	}
		}
	}
	
	
	private SOAPMessage createMessage(String sessionToken, List<Recipient> recipients, String folderId) throws Exception{
		MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        SOAPEnvelope envelope = soapPart.getEnvelope();

        SOAPBody soapBody = envelope.getBody();
        SOAPElement writeCollectionNode = soapBody.addChildElement("WriteCollection");
        writeCollectionNode.addAttribute(new QName("xmlns"), "urn:xtk:persist");
        SOAPElement sessionNode = writeCollectionNode.addChildElement("sessiontoken");
        sessionNode.addTextNode(sessionToken);
        SOAPElement domDoc = writeCollectionNode.addChildElement("domDoc");
        SOAPElement recipientCollection = domDoc.addChildElement("recipient-collection");
        recipientCollection.addAttribute(new QName("xtkschema"), "nms:recipient");
        
        for (Recipient recipient : recipients) {
        	SOAPElement recipientElement = recipientCollection.addChildElement("recipient");
        	recipientElement.addAttribute(new QName("xtkschema"), "nms:recipient");
        	recipientElement.addAttribute(new QName("email"), recipient.getEmail());
        	recipientElement.addAttribute(new QName("firstName"), recipient.getFirstName());
        	recipientElement.addAttribute(new QName("lastName"), recipient.getLastName());
        	recipientElement.addAttribute(new QName("tonariwaid"), recipient.getTonariwaid());
        	if(!StringUtils.isEmpty(recipient.getMobilePhone())){
        		recipientElement.addAttribute(new QName("mobilePhone"), recipient.getMobilePhone());	
        	}
        	recipientElement.addAttribute(new QName("_key"), "@email");
        	if(folderId != null){
        		SOAPElement folderElment = recipientElement.addChildElement("folder");
            	folderElment.addAttribute(new QName("id"), folderId);
            	folderElment.addAttribute(new QName("_operation"), "none");
        	}else{
        		// Folder 
        		if(!StringUtils.isEmpty(recipient.getFolderName())){
        			SOAPElement folderElment = recipientElement.addChildElement("folder");
                	folderElment.addAttribute(new QName("name"), recipient.getFolderName());
                	folderElment.addAttribute(new QName("_operation"), "none");
        		}
        		// Country
        		if(!StringUtils.isEmpty(recipient.getCountry())){
        			SOAPElement countryElment = recipientElement.addChildElement("country");
                	countryElment.addAttribute(new QName("label"), recipient.getCountry());
                	countryElment.addAttribute(new QName("_key"), "@label");
                	countryElment.addAttribute(new QName("_operation"), "none");
        		}
        		// Company
        		if(!StringUtils.isEmpty(recipient.getCompany())){
        			SOAPElement companyElement = recipientElement.addChildElement("company");
                	companyElement.addAttribute(new QName("name"), recipient.getCompany());
                	companyElement.addAttribute(new QName("lowerName"), recipient.getCompany().toLowerCase());
                	companyElement.addAttribute(new QName("_key"), "@name");
                	companyElement.addAttribute(new QName("xtkschema"), "crm:company");
                	companyElement.addAttribute(new QName("_operation"), "update");
        		}
        	}
		}
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", "xtk:persist#WriteCollection");
        headers.addHeader("Operation", "WriteCollection");
        soapMessage.saveChanges();
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
	}
}
