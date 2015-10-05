package com.muteam.task_for_svyaznoy.dao.impl;

import com.muteam.task_for_svyaznoy.dao.SmsRepository;
import com.muteam.task_for_svyaznoy.model.Sms;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;
import java.util.UUID;

public class SmsRepositoryImpl implements SmsRepository {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Sms> list() {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Sms> musicianList = (List<Sms>) session.createCriteria(Sms.class).list();
        transaction.commit();
        session.close();
        return musicianList;
   }

    @Override
    public void save(Sms sms) {
        sms.setId(UUID.randomUUID().toString());
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(sms);
        transaction.commit();
        session.close();
    }
}
