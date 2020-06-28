package com.example.demo.service.task;

import com.example.demo.model.AppUser;
import com.example.demo.model.Task;
import com.example.demo.service.IService;

public interface ITaskService extends IService<Task> {
    Iterable<Task> findAllByUser(AppUser user);
}
