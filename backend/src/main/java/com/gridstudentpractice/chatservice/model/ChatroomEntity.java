package com.gridstudentpractice.chatservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chatrooms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatroomEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany
    private List<UserEntity> userEntities;

    @OneToMany
    private List<MessageEntity> messageEntities;

}
