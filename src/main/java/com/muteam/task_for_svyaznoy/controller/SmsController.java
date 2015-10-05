package com.muteam.task_for_svyaznoy.controller;

import com.muteam.task_for_svyaznoy.dao.SmsRepository;
import com.muteam.task_for_svyaznoy.model.Sms;
import com.muteam.task_for_svyaznoy.sender.SmsSender;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class SmsController {

    SmsRepository smsRepository;

    public void setSmsRepository(SmsRepository smsRepository) {
        this.smsRepository = smsRepository;
    }

    @RequestMapping(value = "api/sms", method = RequestMethod.GET)
    public List<Sms> list() {
        List<Sms> list = this.smsRepository.list();
        return list;
    }

    @RequestMapping(value = "api/sms", method = RequestMethod.POST)
    public Sms add(@RequestBody Sms sms) {
        sms.setSentDate(new Date());
        SmsSender smsSender = new SmsSender();
        Boolean status = smsSender.sendSms(sms.getPhone(), sms.getMessage());
        sms.setSentStatus(status ? "Отправлено" : "Не отправлено");
        this.smsRepository.save(sms);
        return sms;
    }
}
