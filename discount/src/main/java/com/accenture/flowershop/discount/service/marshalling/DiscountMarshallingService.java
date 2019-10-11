package com.accenture.flowershop.discount.service.marshalling;

import com.accenture.flowershop.discount.entity.Discount;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class DiscountMarshallingService {

    public static String marshaller(Discount object) {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("discount", Discount.class);
        xStream.processAnnotations(Discount.class);
        return xStream.toXML(object);
    }
}
