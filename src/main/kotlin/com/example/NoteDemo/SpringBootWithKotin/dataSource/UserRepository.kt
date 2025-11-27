package com.example.NoteDemo.SpringBootWithKotin.dataSource

import com.example.NoteDemo.SpringBootWithKotin.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
    fun findById(id: Long?): List<User>?
    //override fun findAll() : List<User>
}