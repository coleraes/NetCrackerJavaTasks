package com.example.inputpage;

import com.example.inputpage.domain.Message;
import com.example.inputpage.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@org.springframework.stereotype.Controller
public class InputController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping()
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
        return "main";
    }

    @GetMapping("/add")
    public String add(Map<String, Object> model) {
        Iterable<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
        return "add";
    }

    @PostMapping("/add")
    public String add(@RequestParam String text, Map<String, Object> model) {
        Message message = new Message(text);
        messageRepository.save(message);
        Iterable<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
        return "add";
    }

    @GetMapping("/del")
    public String del(Map<String, Object> model) {
        Iterable<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
        return "del";
    }

    @PostMapping("/del")
    public String del(@RequestParam Long id, Map<String, Object> model) {
        if (messageRepository.findById(id).isPresent()) {
            Message message = messageRepository.findById(id).get();
            messageRepository.delete(message);
        }
        Iterable<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
        return "del";
    }

    @GetMapping("/mod")
    public String mod(Map<String, Object> model) {
        Iterable<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
        return "mod";
    }

    @PostMapping("/mod")
    public String mod(@RequestParam Long id, @RequestParam String text, Map<String, Object> model) {
        if (messageRepository.findById(id).isPresent()) {
            Message newMessage = new Message(id, text);
            messageRepository.save(newMessage);
        }
        Iterable<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
        return "mod";
    }
}