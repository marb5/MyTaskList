package com.mycompany.mytasklist.tasklist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 *
 * @author marcin
 */
public class TaskService {
    public static final Task defaultTask = new Task();
    private TaskRepository repository;
    private ObjectMapper mapper;
    
    private final Logger logger = LoggerFactory.getLogger(TaskService.class);
    
    public TaskService() {
        this(new TaskRepository(), new ObjectMapper());
    }
    
    public TaskService(TaskRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    
    public String getTasks() throws JsonProcessingException {
        String data;
        try {
            data = mapper.writeValueAsString(repository.findAll());
        }
        catch (JsonProcessingException e){
            logger.warn("Problem during processing tasks data from database");
            data = mapper.writeValueAsString(defaultTask);
        }
        return data;
    }
}
