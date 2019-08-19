package com.accenture.flowershop.be.utils.marshalling;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Маршалер/демаршалер для преобразования классов в/из XML.
 * Свойства: marshaller, unmarshaller.
 */
public class XMLConverter {
    /**
     * Маршалер.
     */
    private Marshaller marshaller;
    /**
     * Демаршалер.
     */
    private Unmarshaller unmarshaller;

    /**
     * Получить маршалер.
     * @return маршалер
     */
    private Marshaller getMarshaller() {
        return marshaller;
    }

    /**
     * Задать маршалер.
     * @param marshaller - маршалер
     */
    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    /**
     * Получить демаршалер.
     * @return демаршалер
     */
    private Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }

    /**
     * Задать демаршалер.
     * @param unmarshaller - демаршалер
     */
    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    /**
     * Преобразовать объект в XML.
     * @param object - объект
     * @param filepath - путь для маршалируемого файла
     * @throws IOException - если путь некорректен
     */
    public void convertFromObjectToXML(Object object, String filepath)
            throws IOException {
        try (FileOutputStream os = new FileOutputStream(filepath)){
            getMarshaller().marshal(object, new StreamResult(os));
        }
    }

    /**
     * Преобразование XML в объект.
     * @param xmlfile - путь для демаршалируемого файла
     * @return объект
     * @throws IOException  - если путь некорректен
     */
    public Object convertFromXMLToObject(String xmlfile) throws IOException {
        try (FileInputStream is = new FileInputStream(xmlfile)) {
            return getUnmarshaller().unmarshal(new StreamSource(is));
        }
    }
}
