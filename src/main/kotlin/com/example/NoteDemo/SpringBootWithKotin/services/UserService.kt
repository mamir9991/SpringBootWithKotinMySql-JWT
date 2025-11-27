package com.example.NoteDemo.SpringBootWithKotin.services

import com.example.NoteDemo.SpringBootWithKotin.dataSource.UserRepository
import com.example.NoteDemo.SpringBootWithKotin.model.User
import org.springframework.stereotype.Service


@Service
class UserService(private val userRepository: UserRepository) {

    fun getAllUsers(): List<User>? = userRepository.findAll()

    fun createUser(user: User): User = userRepository.save(user)

   /// fun getUserById(id: Long): User? = userRepository.findById(id).getOrNull()

    fun getUsers(id: Long) = userRepository.findById(id)
}
