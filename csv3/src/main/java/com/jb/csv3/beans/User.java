//package com.jb.csv3.beans;
//
//import com.jb.csv3.enums.ClientType;
//import lombok.Builder;
//import lombok.Data;
//import lombok.ToString;
//import lombok.experimental.SuperBuilder;
//import org.hibernate.Hibernate;
//
//import javax.persistence.*;
//import java.util.List;
//import java.util.Objects;
//
///**
// * Created by kobis on 13 Jul, 2021
// */
//@Entity
//@Table(name = "users")
//@SuperBuilder
//@Builder
//@Data
//
//public class User extends Base{
//
//    public User() {
//        super();
//    }
//
//    public User(String email, String password, ClientType clientType) {
//        this.email = email;
//        this.password = password;
//        this.clientType = clientType;
//    }
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(unique = true,length = 30)
//    private String email;
//
//    private String password;
//
//    @Enumerated(EnumType.STRING)
//    private ClientType clientType;
//
//    @ToString.Exclude
//    @OneToMany (mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
////    @JoinColumn(name="todo_id",nullable = false)
//    private List<Todo> todos;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        User user = (User) o;
//        return id != 0 && Objects.equals(id, user.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
//}
