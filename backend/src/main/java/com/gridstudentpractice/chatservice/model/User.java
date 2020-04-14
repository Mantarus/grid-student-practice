package com.gridstudentpractice.chatservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToMany(mappedBy = "userEntities")
    private List<Chatroom> chatroomEntities;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Message> messageEntities;

}
