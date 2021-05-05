package com.mycompany.mytasklist.tasklist;

import com.mycompany.mytasklist.HibernateUtil;
import org.hibernate.Query;

import java.util.Optional;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author marcin
 */
public class TaskRepository {
    private final Logger logger = LoggerFactory.getLogger(TaskServlet.class);
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
        
        var result = session.createQuery("from Task", Task.class).list();
        
        transaction.commit();
        session.close();
        return result;
    }
    
    public Task toggleTask(Integer id) {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        
        Task task = (Task)session.get(Task.class, id);
        task.setDone(!task.getDone());
        logger.info("Found task with id: " + task.getId());
        String hql = "UPDATE Task set done = :taskDone " + 
                     "WHERE id = :taskId";
        Query query = session.createQuery(hql);
        query.setParameter("taskDone", task.getDone());
        query.setParameter("taskId", id);
        var result = query.executeUpdate();
        
        if (result != 1 || task.getId() != id)
            transaction.rollback();
        transaction.commit();
        
        session.close();
        return task;
    } 
    
}
