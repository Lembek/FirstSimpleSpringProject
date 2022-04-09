package com.example.MyTwitter.entities;





import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(max=2048, message = "Сообщение слишком большое")
    @NotBlank(message = "Тело сообщения не может быть пустым")
    private String text;

    @NotBlank(message = "Тег сообщения не может быть пустым")
    private String tag;


    private Date date;

    @ManyToOne(targetEntity = User.class)
    private User author;

    public Message() {
    }

    public String getSimpleDate() {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-M-dd");
        return format.format(this.getDate());
    }


    public Message(String text, String tag) {
        this.text = text;
        this.tag = tag;
        this.date = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
