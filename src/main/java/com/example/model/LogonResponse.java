//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.22 at 11:06:52 AM ICT 
//


package com.example.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="pstrSessionToken" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="pSessionInfo" type="{urn:xtk:session}Element"/&gt;
 *         &lt;element name="pstrSecurityToken" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pstrSessionToken",
    "pSessionInfo",
    "pstrSecurityToken"
})
@XmlRootElement(name = "LogonResponse", namespace="urn:xtk:session")
public class LogonResponse {

    @XmlElement(required = true, namespace="urn:xtk:session")
    protected String pstrSessionToken;
    @XmlElement(required = true, namespace="urn:xtk:session")
    protected Element pSessionInfo;
    @XmlElement(required = true, namespace="urn:xtk:session")
    protected String pstrSecurityToken;

    /**
     * Gets the value of the pstrSessionToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPstrSessionToken() {
        return pstrSessionToken;
    }

    /**
     * Sets the value of the pstrSessionToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPstrSessionToken(String value) {
        this.pstrSessionToken = value;
    }

    /**
     * Gets the value of the pSessionInfo property.
     * 
     * @return
     *     possible object is
     *     {@link Element }
     *     
     */
    public Element getPSessionInfo() {
        return pSessionInfo;
    }

    /**
     * Sets the value of the pSessionInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Element }
     *     
     */
    public void setPSessionInfo(Element value) {
        this.pSessionInfo = value;
    }

    /**
     * Gets the value of the pstrSecurityToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPstrSecurityToken() {
        return pstrSecurityToken;
    }

    /**
     * Sets the value of the pstrSecurityToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPstrSecurityToken(String value) {
        this.pstrSecurityToken = value;
    }

}