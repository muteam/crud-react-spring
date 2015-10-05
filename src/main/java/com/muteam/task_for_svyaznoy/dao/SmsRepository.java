package com.muteam.task_for_svyaznoy.dao;

import com.muteam.task_for_svyaznoy.model.Sms;
import java.util.List;

public interface SmsRepository {

    public abstract List<Sms> list();

    public abstract void save(Sms sms);
}
