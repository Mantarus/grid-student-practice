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
@NamedEntityGraph(
        name = "user-entity-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "roleEntities", subgraph = "role-entity-subgraph")
        },
        subgraphs = @NamedSubgraph(
                name = "role-entity-subgraph",
                attributeNodes = {
                        @NamedAttributeNode("id"),
                }
        )
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = {
                   @JoinColumn(name = "user_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id")
            }
    )
    private List<Role> roleEntities;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userEntities",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Chatroom> chatroomEntities;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "sender")
    private List<Message> messageEntities;

}
