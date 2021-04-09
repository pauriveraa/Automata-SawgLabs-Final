package co.com.swaglabs.log;

import org.apache.log4j.xml.DOMConfigurator;
import org.apache.log4j.Logger;

public class Log4j {

    private final Logger logger;

    public Log4j(String className) {
        this.logger = Logger.getLogger(className);
        DOMConfigurator.configure("log4j.xml");
    }

    public Logger getLogger() {
        return logger;
    }
}
