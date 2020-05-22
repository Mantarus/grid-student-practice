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
@NamedEntityGraph(
        name = "message-entity-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "sender", subgraph = "user-entity-subgraph"),
                @NamedAttributeNode(value = "chatroom", subgraph = "chatroom-entity-subgraph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "user-entity-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("login")
                        }
                ),
                @NamedSubgraph(
                        name = "chatroom-entity-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("name")
                        }
                )
        }
)
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sender")
    private User sender;

    @Column(name = "body")
    private String body;

    @Column(name = "time1")
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "chatroom")
    private Chatroom chatroom;

}
