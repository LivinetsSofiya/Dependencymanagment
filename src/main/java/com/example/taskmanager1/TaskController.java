package com.example.taskmanager1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class TaskController {

    @Autowired

    private TaskRepository taskRepository;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TaskController.class);

    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        model.addAttribute("task", new Task()); // For the form
        return "index";
    }
@PostMapping("add")
    public String addTask(@ModelAttribute Task task) {
        log.info("add Task");
        taskRepository.save(task);
        return "redirect:/";
    }

   @GetMapping("edit/{id}")
    public String editTask(@PathVariable Long id, Model model) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            model.addAttribute("task", task);
        }
        return "edit";
    }

    @PostMapping("update")
    public String updateTask(@ModelAttribute Task task) {
        taskRepository.save(task);
        return "redirect:/";
    }

    @GetMapping("delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "redirect:/";
    }
}