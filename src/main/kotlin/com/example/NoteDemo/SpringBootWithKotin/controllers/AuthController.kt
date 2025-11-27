package com.example.NoteDemo.SpringBootWithKotin.controllers

import com.example.NoteDemo.SpringBootWithKotin.services.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {
    data class AuthRequest(
      //  @field:Email(message = "Invalid email format.")
        val email: String,
//        @field:Pattern(
//            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{9,}\$",
//            message = "Password must be at least 9 characters long and contain at least one digit, uppercase and lowercase character."
//        )
        val password: String,
        val name: String?=null
    )

    data class RefreshRequest(
        val refreshToken: String
    )

    @PostMapping("/register")
    fun register(
       // @Valid @RequestBody body: AuthRequest
        @RequestBody body: AuthRequest
    ) {
        authService.register(body.email, body.password,body.name?:"")
    }

    @PostMapping("/login")
    fun login(
        @RequestBody body: AuthRequest
    ): AuthService.TokenPair {
        return authService.login(body.email, body.password)
    }

    @PostMapping("/refresh")
    fun refresh(
        @RequestBody body: RefreshRequest
    ): AuthService.TokenPair {
        return authService.refresh(body.refreshToken)
    }
}