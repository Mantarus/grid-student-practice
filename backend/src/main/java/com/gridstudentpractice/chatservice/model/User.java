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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    private String roles;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userEntities",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Chatroom> chatroomEntities;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "sender")
    private List<Message> messageEntities;

}
