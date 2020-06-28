package com.example.demo.repository;

import com.example.demo.model.AppUser;
import com.example.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskRepository extends JpaRepository<Task, Long> {
    Iterable<Task> findAllByUser(AppUser user);
}
