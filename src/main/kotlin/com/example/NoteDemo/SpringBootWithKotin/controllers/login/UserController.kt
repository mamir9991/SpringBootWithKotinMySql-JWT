package com.example.NoteDemo.SpringBootWithKotin.controllers.login

import com.example.NoteDemo.SpringBootWithKotin.model.User
import com.example.NoteDemo.SpringBootWithKotin.services.login.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getAllUsers(): List<User>? {
      //  val ownerId = SecurityContextHolder.getContext().authentication.principal as Long
        //return userService.getUsers(ownerId)
        println("getAllUsers()")
        return userService.getAllUsers()
    }

 /*   @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<User> {

        val ownerId = SecurityContextHolder.getContext().authentication.principal as Long



*//*
        val user = userService.getUsers(id)
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.notFound().build()
        }*//*
    }*/
    @PostMapping
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        val createdUser = userService.createUser(user)
       return ResponseEntity.status(HttpStatus.CREATED).body(createdUser)
    }
}
