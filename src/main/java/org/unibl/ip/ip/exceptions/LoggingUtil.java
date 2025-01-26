package org.unibl.ip.ip.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingUtil {
    private static final Logger logger = LogManager.getLogger(LoggingUtil.class);

    public static void logException(Exception e, Class<?> clazz) {
        Logger classLogger = LogManager.getLogger(clazz);
        classLogger.error("An error occurred: ", e);
    }
}
