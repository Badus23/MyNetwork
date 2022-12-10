package com.mamchura.socialNetwork.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "text")
    @NotBlank(message = "Please fill the message")
    @Length(max = 2048, message = "Message too long")
    private String text;

    @Column(name = "tag")
    @Length(max = 255, message = "Message too long")
    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @Column(name = "filename")
    private String filename;

    public Message() {}

    public Message(String text, String tag, User user) {
        this.text = text;
        this.tag = tag;
        author = user;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
