package com.gridstudentpractice.chatservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sender")
    private UserEntity sender;

    @Column(name = "body")
    private String body;

    @Column(name = "time1")
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "chatroom")
    private ChatroomEntity chatroom;

}
