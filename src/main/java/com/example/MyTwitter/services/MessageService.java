package com.example.MyTwitter.services;

import com.example.MyTwitter.entities.Message;
import com.example.MyTwitter.entities.User;
import com.example.MyTwitter.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void save(Message message, User user) {
        message.setAuthor(user);
        message.setDate(new Date());
        messageRepository.save(message);
    }

    public List<Message> findAllByTag(String filter) {
        return messageRepository.findAllByTagLike("%" + filter + "%");
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public List<Message> findAllByAuthor(User user) {
        return messageRepository.findAllByAuthor(user);
    }

    public void deleteById(long id) {
        messageRepository.deleteById(id);
    }
}
