package com.gridstudentpractice.chatservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(
        name = "role-entity-graph",
        attributeNodes = @NamedAttributeNode(value = "userEntities", subgraph = "user-entity-subgraph"),
        subgraphs = @NamedSubgraph(
                name = "user-entity-subgraph",
                attributeNodes = {
                        @NamedAttributeNode("id"),
                        @NamedAttributeNode("login")
                }
        )
)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "roleEntities")
    private Set<User> userEntities;

}
