//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.1 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package org.orienteering.datastandard._3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 *         Defines a class in an event.
 *       
 * 
 * <p>Java class for Class complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="Class">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="Id" type="{http://www.orienteering.org/datastandard/3.0}Id" minOccurs="0"/>
 *         <element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="ShortName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="ClassType" type="{http://www.orienteering.org/datastandard/3.0}ClassType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="Leg" type="{http://www.orienteering.org/datastandard/3.0}Leg" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="TeamFee" type="{http://www.orienteering.org/datastandard/3.0}Fee" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="Fee" type="{http://www.orienteering.org/datastandard/3.0}Fee" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="Status" type="{http://www.orienteering.org/datastandard/3.0}EventClassStatus" minOccurs="0"/>
 *         <element name="RaceClass" type="{http://www.orienteering.org/datastandard/3.0}RaceClass" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="TooFewEntriesSubstituteClass" type="{http://www.orienteering.org/datastandard/3.0}Class" minOccurs="0"/>
 *         <element name="TooManyEntriesSubstituteClass" type="{http://www.orienteering.org/datastandard/3.0}Class" minOccurs="0"/>
 *         <element name="Extensions" type="{http://www.orienteering.org/datastandard/3.0}Extensions" minOccurs="0"/>
 *       </sequence>
 *       <attribute name="minAge" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       <attribute name="maxAge" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       <attribute name="sex" default="B">
 *         <simpleType>
 *           <restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *             <enumeration value="B"/>
 *             <enumeration value="F"/>
 *             <enumeration value="M"/>
 *           </restriction>
 *         </simpleType>
 *       </attribute>
 *       <attribute name="minNumberOfTeamMembers" type="{http://www.w3.org/2001/XMLSchema}integer" default="1" />
 *       <attribute name="maxNumberOfTeamMembers" type="{http://www.w3.org/2001/XMLSchema}integer" default="1" />
 *       <attribute name="minTeamAge" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       <attribute name="maxTeamAge" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       <attribute name="numberOfCompetitors" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       <attribute name="maxNumberOfCompetitors" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       <attribute name="resultListMode" default="Default">
 *         <simpleType>
 *           <restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *             <enumeration value="Default"/>
 *             <enumeration value="Unordered"/>
 *             <enumeration value="UnorderedNoTimes"/>
 *           </restriction>
 *         </simpleType>
 *       </attribute>
 *       <attribute name="modifyTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Class", propOrder = {
    "id",
    "name",
    "shortName",
    "classType",
    "leg",
    "teamFee",
    "fee",
    "status",
    "raceClass",
    "tooFewEntriesSubstituteClass",
    "tooManyEntriesSubstituteClass",
    "extensions"
})
public class Class {

    @XmlElement(name = "Id")
    protected Id id;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "ShortName")
    protected String shortName;
    @XmlElement(name = "ClassType")
    protected List<ClassType> classType;
    @XmlElement(name = "Leg")
    protected List<Leg> leg;
    @XmlElement(name = "TeamFee")
    protected List<Fee> teamFee;
    @XmlElement(name = "Fee")
    protected List<Fee> fee;
    @XmlElement(name = "Status", defaultValue = "Normal")
    @XmlSchemaType(name = "NMTOKEN")
    protected EventClassStatus status;
    @XmlElement(name = "RaceClass")
    protected List<RaceClass> raceClass;
    @XmlElement(name = "TooFewEntriesSubstituteClass")
    protected Class tooFewEntriesSubstituteClass;
    @XmlElement(name = "TooManyEntriesSubstituteClass")
    protected Class tooManyEntriesSubstituteClass;
    @XmlElement(name = "Extensions")
    protected Extensions extensions;
    @XmlAttribute(name = "minAge")
    protected BigInteger minAge;
    @XmlAttribute(name = "maxAge")
    protected BigInteger maxAge;
    @XmlAttribute(name = "sex")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String sex;
    @XmlAttribute(name = "minNumberOfTeamMembers")
    protected BigInteger minNumberOfTeamMembers;
    @XmlAttribute(name = "maxNumberOfTeamMembers")
    protected BigInteger maxNumberOfTeamMembers;
    @XmlAttribute(name = "minTeamAge")
    protected BigInteger minTeamAge;
    @XmlAttribute(name = "maxTeamAge")
    protected BigInteger maxTeamAge;
    @XmlAttribute(name = "numberOfCompetitors")
    protected BigInteger numberOfCompetitors;
    @XmlAttribute(name = "maxNumberOfCompetitors")
    protected BigInteger maxNumberOfCompetitors;
    @XmlAttribute(name = "resultListMode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String resultListMode;
    @XmlAttribute(name = "modifyTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar modifyTime;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Id }
     *     
     */
    public Id getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Id }
     *     
     */
    public void setId(Id value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the shortName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Sets the value of the shortName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortName(String value) {
        this.shortName = value;
    }

    /**
     * Gets the value of the classType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the classType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClassType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClassType }
     * 
     * 
     * @return
     *     The value of the classType property.
     */
    public List<ClassType> getClassType() {
        if (classType == null) {
            classType = new ArrayList<>();
        }
        return this.classType;
    }

    /**
     * Gets the value of the leg property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the leg property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLeg().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Leg }
     * 
     * 
     * @return
     *     The value of the leg property.
     */
    public List<Leg> getLeg() {
        if (leg == null) {
            leg = new ArrayList<>();
        }
        return this.leg;
    }

    /**
     * Gets the value of the teamFee property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the teamFee property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTeamFee().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Fee }
     * 
     * 
     * @return
     *     The value of the teamFee property.
     */
    public List<Fee> getTeamFee() {
        if (teamFee == null) {
            teamFee = new ArrayList<>();
        }
        return this.teamFee;
    }

    /**
     * Gets the value of the fee property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the fee property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFee().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Fee }
     * 
     * 
     * @return
     *     The value of the fee property.
     */
    public List<Fee> getFee() {
        if (fee == null) {
            fee = new ArrayList<>();
        }
        return this.fee;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link EventClassStatus }
     *     
     */
    public EventClassStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventClassStatus }
     *     
     */
    public void setStatus(EventClassStatus value) {
        this.status = value;
    }

    /**
     * Gets the value of the raceClass property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the raceClass property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRaceClass().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RaceClass }
     * 
     * 
     * @return
     *     The value of the raceClass property.
     */
    public List<RaceClass> getRaceClass() {
        if (raceClass == null) {
            raceClass = new ArrayList<>();
        }
        return this.raceClass;
    }

    /**
     * Gets the value of the tooFewEntriesSubstituteClass property.
     * 
     * @return
     *     possible object is
     *     {@link Class }
     *     
     */
    public Class getTooFewEntriesSubstituteClass() {
        return tooFewEntriesSubstituteClass;
    }

    /**
     * Sets the value of the tooFewEntriesSubstituteClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link Class }
     *     
     */
    public void setTooFewEntriesSubstituteClass(Class value) {
        this.tooFewEntriesSubstituteClass = value;
    }

    /**
     * Gets the value of the tooManyEntriesSubstituteClass property.
     * 
     * @return
     *     possible object is
     *     {@link Class }
     *     
     */
    public Class getTooManyEntriesSubstituteClass() {
        return tooManyEntriesSubstituteClass;
    }

    /**
     * Sets the value of the tooManyEntriesSubstituteClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link Class }
     *     
     */
    public void setTooManyEntriesSubstituteClass(Class value) {
        this.tooManyEntriesSubstituteClass = value;
    }

    /**
     * Gets the value of the extensions property.
     * 
     * @return
     *     possible object is
     *     {@link Extensions }
     *     
     */
    public Extensions getExtensions() {
        return extensions;
    }

    /**
     * Sets the value of the extensions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Extensions }
     *     
     */
    public void setExtensions(Extensions value) {
        this.extensions = value;
    }

    /**
     * Gets the value of the minAge property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinAge() {
        return minAge;
    }

    /**
     * Sets the value of the minAge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinAge(BigInteger value) {
        this.minAge = value;
    }

    /**
     * Gets the value of the maxAge property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxAge() {
        return maxAge;
    }

    /**
     * Sets the value of the maxAge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxAge(BigInteger value) {
        this.maxAge = value;
    }

    /**
     * Gets the value of the sex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSex() {
        if (sex == null) {
            return "B";
        } else {
            return sex;
        }
    }

    /**
     * Sets the value of the sex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSex(String value) {
        this.sex = value;
    }

    /**
     * Gets the value of the minNumberOfTeamMembers property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinNumberOfTeamMembers() {
        if (minNumberOfTeamMembers == null) {
            return new BigInteger("1");
        } else {
            return minNumberOfTeamMembers;
        }
    }

    /**
     * Sets the value of the minNumberOfTeamMembers property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinNumberOfTeamMembers(BigInteger value) {
        this.minNumberOfTeamMembers = value;
    }

    /**
     * Gets the value of the maxNumberOfTeamMembers property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxNumberOfTeamMembers() {
        if (maxNumberOfTeamMembers == null) {
            return new BigInteger("1");
        } else {
            return maxNumberOfTeamMembers;
        }
    }

    /**
     * Sets the value of the maxNumberOfTeamMembers property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxNumberOfTeamMembers(BigInteger value) {
        this.maxNumberOfTeamMembers = value;
    }

    /**
     * Gets the value of the minTeamAge property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinTeamAge() {
        return minTeamAge;
    }

    /**
     * Sets the value of the minTeamAge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinTeamAge(BigInteger value) {
        this.minTeamAge = value;
    }

    /**
     * Gets the value of the maxTeamAge property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxTeamAge() {
        return maxTeamAge;
    }

    /**
     * Sets the value of the maxTeamAge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxTeamAge(BigInteger value) {
        this.maxTeamAge = value;
    }

    /**
     * Gets the value of the numberOfCompetitors property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumberOfCompetitors() {
        return numberOfCompetitors;
    }

    /**
     * Sets the value of the numberOfCompetitors property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumberOfCompetitors(BigInteger value) {
        this.numberOfCompetitors = value;
    }

    /**
     * Gets the value of the maxNumberOfCompetitors property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxNumberOfCompetitors() {
        return maxNumberOfCompetitors;
    }

    /**
     * Sets the value of the maxNumberOfCompetitors property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxNumberOfCompetitors(BigInteger value) {
        this.maxNumberOfCompetitors = value;
    }

    /**
     * Gets the value of the resultListMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultListMode() {
        if (resultListMode == null) {
            return "Default";
        } else {
            return resultListMode;
        }
    }

    /**
     * Sets the value of the resultListMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultListMode(String value) {
        this.resultListMode = value;
    }

    /**
     * Gets the value of the modifyTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getModifyTime() {
        return modifyTime;
    }

    /**
     * Sets the value of the modifyTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setModifyTime(XMLGregorianCalendar value) {
        this.modifyTime = value;
    }

}
