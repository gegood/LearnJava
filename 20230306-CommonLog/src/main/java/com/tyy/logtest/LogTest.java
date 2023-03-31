package com.tyy.logtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(LogTest.class);
        logger.info("start...");
        logger.warn("end.");
        logger.info("INFO 成功了");
        logger.error("ERROR 成功了");
        logger.debug("DEBUG 成功了");
    }
}
