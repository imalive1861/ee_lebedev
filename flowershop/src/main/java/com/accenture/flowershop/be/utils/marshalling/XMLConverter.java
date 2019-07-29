package com.accenture.flowershop.be.utils.marshalling;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Service;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XMLConverter {
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    private Marshaller getMarshaller() {
        return marshaller;
    }
    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    private Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }
    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public void convertFromObjectToXML(Object object, String filepath)
            throws IOException {
        try (FileOutputStream os = new FileOutputStream(filepath)){
            getMarshaller().marshal(object, new StreamResult(os));
        }
    }
    public Object convertFromXMLToObject(String xmlfile) throws IOException {
        try (FileInputStream is = new FileInputStream(xmlfile)) {
            return getUnmarshaller().unmarshal(new StreamSource(is));
        }
    }
}
