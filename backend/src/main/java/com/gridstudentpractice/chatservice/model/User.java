package com.gridstudentpractice.chatservice.model;

import lombok.*;
import org.hibernate.annotations.NaturalId;

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
    @Column(name = "id")
    private Integer id;

    @NaturalId
    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToMany(mappedBy = "userEntities",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Chatroom> chatroomEntities;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "sender")
    private List<Message> messageEntities;

}
