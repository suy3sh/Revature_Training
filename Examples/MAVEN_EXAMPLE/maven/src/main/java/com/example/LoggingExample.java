package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingExample {

    //create logger INSTANCE
    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    public void demoLogging(){

        //Debugging Logging
        logger.debug("LOGGING DEBUGGING TESTING");

        //Paramaterized Logging
        String name = "Suyesh";
        int num = 123;

        logger.info("User's name is {} with num {}", name, num); 

        //Exception Logging
        try{
            throw new RuntimeException("Something went horribly wrong");
        }catch (Exception e){
            logger.error("A horrible error has occured", e);
        }
    }

    public static void main(String[] args) {
        LoggingExample test = new LoggingExample();
        test.demoLogging();
    }
}