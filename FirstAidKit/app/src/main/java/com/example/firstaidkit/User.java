package com.example.firstaidkit;

public class User {

    public String fullName, email, password, phone; /* old */

    /* session sharedreference */
//    int id;
//    String name;
//
//    public User(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    } /* session sharedreference */

    /* old */
    public User(String fullName, String email, String password, String phone) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;


    }
}
