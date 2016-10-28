/**
 * AcceptedFileRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mx.com.kubo.kubows;

public class AcceptedFileRequest  implements java.io.Serializable {
    private java.lang.Boolean accepted;

    private java.lang.String accepted_prospectus_id;

    private java.lang.String client_prospectus_id;

    private java.lang.String company_id;

    private java.lang.String file_type_id;

    private java.lang.String message;

    private java.lang.String proyect_loan_id;

    public AcceptedFileRequest() {
    }

    public AcceptedFileRequest(
           java.lang.Boolean accepted,
           java.lang.String accepted_prospectus_id,
           java.lang.String client_prospectus_id,
           java.lang.String company_id,
           java.lang.String file_type_id,
           java.lang.String message,
           java.lang.String proyect_loan_id) {
           this.accepted = accepted;
           this.accepted_prospectus_id = accepted_prospectus_id;
           this.client_prospectus_id = client_prospectus_id;
           this.company_id = company_id;
           this.file_type_id = file_type_id;
           this.message = message;
           this.proyect_loan_id = proyect_loan_id;
    }


    /**
     * Gets the accepted value for this AcceptedFileRequest.
     * 
     * @return accepted
     */
    public java.lang.Boolean getAccepted() {
        return accepted;
    }


    /**
     * Sets the accepted value for this AcceptedFileRequest.
     * 
     * @param accepted
     */
    public void setAccepted(java.lang.Boolean accepted) {
        this.accepted = accepted;
    }


    /**
     * Gets the accepted_prospectus_id value for this AcceptedFileRequest.
     * 
     * @return accepted_prospectus_id
     */
    public java.lang.String getAccepted_prospectus_id() {
        return accepted_prospectus_id;
    }


    /**
     * Sets the accepted_prospectus_id value for this AcceptedFileRequest.
     * 
     * @param accepted_prospectus_id
     */
    public void setAccepted_prospectus_id(java.lang.String accepted_prospectus_id) {
        this.accepted_prospectus_id = accepted_prospectus_id;
    }


    /**
     * Gets the client_prospectus_id value for this AcceptedFileRequest.
     * 
     * @return client_prospectus_id
     */
    public java.lang.String getClient_prospectus_id() {
        return client_prospectus_id;
    }


    /**
     * Sets the client_prospectus_id value for this AcceptedFileRequest.
     * 
     * @param client_prospectus_id
     */
    public void setClient_prospectus_id(java.lang.String client_prospectus_id) {
        this.client_prospectus_id = client_prospectus_id;
    }


    /**
     * Gets the company_id value for this AcceptedFileRequest.
     * 
     * @return company_id
     */
    public java.lang.String getCompany_id() {
        return company_id;
    }


    /**
     * Sets the company_id value for this AcceptedFileRequest.
     * 
     * @param company_id
     */
    public void setCompany_id(java.lang.String company_id) {
        this.company_id = company_id;
    }


    /**
     * Gets the file_type_id value for this AcceptedFileRequest.
     * 
     * @return file_type_id
     */
    public java.lang.String getFile_type_id() {
        return file_type_id;
    }


    /**
     * Sets the file_type_id value for this AcceptedFileRequest.
     * 
     * @param file_type_id
     */
    public void setFile_type_id(java.lang.String file_type_id) {
        this.file_type_id = file_type_id;
    }


    /**
     * Gets the message value for this AcceptedFileRequest.
     * 
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this AcceptedFileRequest.
     * 
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }


    /**
     * Gets the proyect_loan_id value for this AcceptedFileRequest.
     * 
     * @return proyect_loan_id
     */
    public java.lang.String getProyect_loan_id() {
        return proyect_loan_id;
    }


    /**
     * Sets the proyect_loan_id value for this AcceptedFileRequest.
     * 
     * @param proyect_loan_id
     */
    public void setProyect_loan_id(java.lang.String proyect_loan_id) {
        this.proyect_loan_id = proyect_loan_id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AcceptedFileRequest)) return false;
        AcceptedFileRequest other = (AcceptedFileRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.accepted==null && other.getAccepted()==null) || 
             (this.accepted!=null &&
              this.accepted.equals(other.getAccepted()))) &&
            ((this.accepted_prospectus_id==null && other.getAccepted_prospectus_id()==null) || 
             (this.accepted_prospectus_id!=null &&
              this.accepted_prospectus_id.equals(other.getAccepted_prospectus_id()))) &&
            ((this.client_prospectus_id==null && other.getClient_prospectus_id()==null) || 
             (this.client_prospectus_id!=null &&
              this.client_prospectus_id.equals(other.getClient_prospectus_id()))) &&
            ((this.company_id==null && other.getCompany_id()==null) || 
             (this.company_id!=null &&
              this.company_id.equals(other.getCompany_id()))) &&
            ((this.file_type_id==null && other.getFile_type_id()==null) || 
             (this.file_type_id!=null &&
              this.file_type_id.equals(other.getFile_type_id()))) &&
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage()))) &&
            ((this.proyect_loan_id==null && other.getProyect_loan_id()==null) || 
             (this.proyect_loan_id!=null &&
              this.proyect_loan_id.equals(other.getProyect_loan_id())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAccepted() != null) {
            _hashCode += getAccepted().hashCode();
        }
        if (getAccepted_prospectus_id() != null) {
            _hashCode += getAccepted_prospectus_id().hashCode();
        }
        if (getClient_prospectus_id() != null) {
            _hashCode += getClient_prospectus_id().hashCode();
        }
        if (getCompany_id() != null) {
            _hashCode += getCompany_id().hashCode();
        }
        if (getFile_type_id() != null) {
            _hashCode += getFile_type_id().hashCode();
        }
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        if (getProyect_loan_id() != null) {
            _hashCode += getProyect_loan_id().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AcceptedFileRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "AcceptedFileRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accepted");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "accepted"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accepted_prospectus_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "accepted_prospectus_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("client_prospectus_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "client_prospectus_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("company_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "company_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("file_type_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "file_type_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("proyect_loan_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "proyect_loan_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
