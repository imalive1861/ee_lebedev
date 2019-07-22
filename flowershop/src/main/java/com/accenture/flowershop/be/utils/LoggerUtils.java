package com.accenture.flowershop.be.utils;

import com.accenture.flowershop.be.service.business.flower.FlowerBusinessServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {
    private static final Logger LOG = LoggerFactory.getLogger(FlowerBusinessServiceImpl.class);

    public static Logger getLOG() {
        return LOG;
    }
}
