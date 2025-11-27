package com.example.NoteDemo.SpringBootWithKotin.model

import jakarta.persistence.*


@Entity
@Table(name = "client")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(nullable = true)
    val name: String?=null,
    @Column(nullable = false, unique = true)
    val email: String,
    @Column(nullable = false)
    val password: String,
    val age: Int? = null
){
    // No-arg constructor for JPA (alternative to kotlin-jpa plugin)
    constructor() : this(null, "", "", "", null)
}

