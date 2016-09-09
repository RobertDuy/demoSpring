package com.example.services;

import javax.xml.namespace.QName;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapMessage;

@Component
public class MyClientInterceptor implements org.springframework.ws.client.support.interceptor.ClientInterceptor {

    @Override
    public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
    	((SoapMessage)messageContext.getRequest()).getSoapHeader().addNamespaceDeclaration("urn", "urn:xtk:session");
    	((SoapMessage)messageContext.getRequest()).getSoapHeader().addAttribute(new QName("SOAPAction"), "xtk:session#Logon");
        return true;
    }
	
    @Override
    public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
        return false;
    }
}
