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
@NamedEntityGraph (
        name = "chatroom-entity-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "userEntities", subgraph = "user-entity-subgraph")
        },
        subgraphs = @NamedSubgraph(
                name = "user-entity-subgraph",
                attributeNodes = @NamedAttributeNode(
                        value = "id"
                )
        )
)
public class Chatroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_chatroom",
            joinColumns = {
                    @JoinColumn(name = "chatroom_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")
            }
    )
    private List<User> userEntities;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            mappedBy = "chatroom")
    private List<Message> messageEntities;

}
