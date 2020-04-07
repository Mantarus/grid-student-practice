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
    @JoinTable(
            name = "user_chatroom",
            joinColumns = @JoinColumn(name = "chatroom_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> userEntities;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MessageEntity> messageEntities;

}
