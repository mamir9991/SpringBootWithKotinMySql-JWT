package com.example.NoteDemo.SpringBootWithKotin.controllers.login

import com.example.NoteDemo.SpringBootWithKotin.services.login.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

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


    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(ex: ResponseStatusException): ResponseEntity<Map<String, String>> {
        return ResponseEntity(
            mapOf("status" to "${ex.reason}", "statusCode" to "${ex.statusCode}"),
            ex.statusCode
        )
    }

    @PostMapping("/register")
    fun register(
        // @Valid @RequestBody body: AuthRequest
        @RequestBody body: AuthRequest
    ): ResponseEntity<Any> {
        return authService.register(body.email, body.password, body.name ?: "")
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