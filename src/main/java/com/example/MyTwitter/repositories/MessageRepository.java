package com.example.MyTwitter.repositories;

import com.example.MyTwitter.entities.Message;
import com.example.MyTwitter.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByTag(String tag);

    List<Message> findAllByAuthor(User user);

    void deleteById (Long id);

    List<Message> findAllByTagLike(String filter);
}
