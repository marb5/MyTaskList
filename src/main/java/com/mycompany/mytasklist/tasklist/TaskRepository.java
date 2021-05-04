package com.mycompany.mytasklist.tasklist;

import com.mycompany.mytasklist.HibernateUtil;

import java.util.Optional;
import java.util.List;

/**
 *
 * @author marcin
 */
public class TaskRepository {
    //szukanie elementu task indexem
    public Optional<Task> findById(Integer id) {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        
        var result = Optional.ofNullable(session.get(Task.class, id));
        
        transaction.commit();
        session.close();
        return result;
    }
    
    //szukanie wszystkich elementow z task list tabeli
    List<Task> findAll() {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        
        var result = session.createQuery("from Language", Task.class).list();
        
        transaction.commit();
        session.close();
        return result;
    }
}
