package com.example.NoteDemo.SpringBootWithKotin.model

import jakarta.persistence.*
import java.time.Instant


@Entity
@Table(name = "refresh_tokens")
data class RefreshToken(
    @Id
    val id: Long? = null,
    @Column(nullable = false)
    val expires: Instant?,
    @Column(nullable = false)
    val token: String,
    @Column(nullable = false)
    val created: Instant = Instant.now()
){
    // No-arg constructor for JPA
    constructor() : this(null, Instant.now(), "", Instant.now())
}