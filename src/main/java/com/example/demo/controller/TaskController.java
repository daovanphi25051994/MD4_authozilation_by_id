package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.Task;
import com.example.demo.service.task.ITaskService;
import com.example.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

import static com.example.demo.config.SecurityConfig.CHECKED_USER_ID;

@Controller
public class TaskController {

    @Autowired
    private ITaskService taskService;

    @Autowired
    private IUserService userService;



    @ModelAttribute("user")
    public AppUser user() {
        return userService.getCurrentUser();
    }

    @GetMapping("/tasks")
    public ModelAndView listTask(){
        ModelAndView modelAndView = new ModelAndView("/task/list");
        List<Task> tasks = (List<Task>) taskService.findAllByUser(userService.getCurrentUser());
        modelAndView.addObject("tasks", tasks);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/task/create");
        modelAndView.addObject("task", new Task());
        modelAndView.addObject("user", userService.getCurrentUser());
        return modelAndView;
    }

    @PostMapping("/create")
    public RedirectView creatTask(@ModelAttribute Task task){
        taskService.save(task);
        return new RedirectView("");
    }
}