package com.muteam.task_for_svyaznoy.sender;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import java.util.Random;

public class SmsSender { //Well, to be honest, it's better to make it through interface, initialize as a spring bean and set as a property for service

    //final Logger log = LoggerFactory.getLogger(getClass());
    final Random random = new Random();
    public boolean sendSms(String phone, String message){
        boolean success = random.nextBoolean();
        //log.info("message sent to {} with status {}:\n{}", new Object[]{phone, success, message});
        return success;
    }
}
