package com.example.services;

import java.util.ArrayList;
import java.util.List;

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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.example.model.RecipientFolder;
import com.example.services.Interfaces.FolderInterface;

@Component("RecipientFolderService")
public class RecipientFolderService implements FolderInterface{
	private static final int ROOT_RECIPIENT_KEY = 1189;
	private static final String ROOT_NAME = "Recipients";
	private static final String ROOT_FULL_NAME = "/Profiles and Targets/Recipients/";
	private static final RecipientFolder RecipientRoot = new RecipientFolder(ROOT_RECIPIENT_KEY, ROOT_NAME, ROOT_FULL_NAME);

	@Override
	public List<RecipientFolder> getListRecipientFolder(String sessionToken, boolean isIncludeRoot) {
		try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            SOAPMessage soapResponse = soapConnection.call(createMessage(sessionToken), LogonService.END_POINT);
            Node folder = soapResponse.getSOAPBody().getFirstChild().getFirstChild().getFirstChild().getFirstChild();
            
            List<RecipientFolder> listResult = new ArrayList<RecipientFolder>();
            if(isIncludeRoot){
            	listResult.add(RecipientRoot);
            }
            processAddResult(listResult, folder);
            
            while(folder != null && folder.getNextSibling() != null){
            	processAddResult(listResult, folder.getNextSibling());
            }
            return listResult;
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
        }
		return null;
	}

	private void processAddResult(List<RecipientFolder> listResult, Node folder){
		if(listResult == null) listResult = new ArrayList<RecipientFolder>();
		NamedNodeMap nodemap = folder.getAttributes();
		int folderId = Integer.parseInt(nodemap.getNamedItem("id").getNodeValue());
		String folderName = nodemap.getNamedItem("label").getNodeValue();
		String folderFullName = nodemap.getNamedItem("fullName").getNodeValue();
        listResult.add(new RecipientFolder(folderId, folderName, folderFullName));
	}
	
	
	private SOAPMessage createMessage(String sessionToken) throws SOAPException{
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = "urn:xtk:folder";
        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("urn", serverURI);

        SOAPBody soapBody = envelope.getBody();
        SOAPElement loadChildrenElem = soapBody.addChildElement("LoadChildren", "urn");
        SOAPElement sessionElement = loadChildrenElem.addChildElement("sessiontoken", "urn");
        sessionElement.addTextNode(sessionToken);
        SOAPElement strParentKeyElem = loadChildrenElem.addChildElement("strParentKey", "urn");
        strParentKeyElem.addTextNode(ROOT_RECIPIENT_KEY + "");
        loadChildrenElem.addChildElement("strFolderFilter", "urn");
        SOAPElement bWriteAccessElem = loadChildrenElem.addChildElement("bWriteAccess", "urn");
        bWriteAccessElem.addTextNode("true");
        SOAPElement strFullNameElem = loadChildrenElem.addChildElement("strFullName", "urn");
        strFullNameElem.addTextNode(ROOT_FULL_NAME);
        SOAPElement bSortElem = loadChildrenElem.addChildElement("bSort", "urn");
        bSortElem.addTextNode("true");

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", "xtk:folder#LoadChildren");
        soapMessage.saveChanges();

        return soapMessage;
	}
}
