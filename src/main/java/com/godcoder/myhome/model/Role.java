package com.godcoder.myhome.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
