package com.bd.post.model;

//import lombok.Data;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;

//import javax.persistence.*;
//
//@Entity
//@Table(name = "roles")
//@Data
//@EntityListeners(AuditingEntityListener.class)
//public class Role {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Enumerated(EnumType.STRING)
//    @Column(length = 20)
//    private ERole name;
//
//    public Role() {
//
//    }
//
//    public Role(ERole name) {
//        this.name = name;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public ERole getName() {
//        return name;
//    }
//
//    public void setName(ERole name) {
//        this.name = name;
//    }
//}