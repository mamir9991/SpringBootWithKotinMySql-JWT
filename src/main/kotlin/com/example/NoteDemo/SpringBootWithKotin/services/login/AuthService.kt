package com.example.NoteDemo.SpringBootWithKotin.services.login

import com.example.NoteDemo.SpringBootWithKotin.dataSource.login.RefreshTokenRepository
import com.example.NoteDemo.SpringBootWithKotin.dataSource.login.UserRepository
import com.example.NoteDemo.SpringBootWithKotin.model.RefreshToken
import com.example.NoteDemo.SpringBootWithKotin.model.User
import com.example.NoteDemo.SpringBootWithKotin.security.HashEncoder
import com.example.NoteDemo.SpringBootWithKotin.security.JwtService

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import java.security.MessageDigest
import java.time.Instant
import java.util.*

@Service
class AuthService(
    private val jwtService: JwtService,
    private val userRepository: UserRepository,
    private val hashEncoder: HashEncoder,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    data class TokenPair(
        val accessToken: String,
        val refreshToken: String
    )

    fun register(email: String, password: String, name: String): ResponseEntity<Any> {
        val user = userRepository.findByEmail(email.trim())
        if (user != null) {
            throw ResponseStatusException(HttpStatus.CONFLICT, "A user with that email already exists.")
        }
        val savedUser = userRepository.save(
            User(
                name = name,
                email = email,
                password = hashEncoder.encode(password)
            )
        )
        return ResponseEntity.ok(
            mapOf("message" to "User registered successfully", "user" to savedUser)
        )
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun login(email: String, password: String): TokenPair {
        val user = userRepository.findByEmail(email)
            ?: throw BadCredentialsException("Invalid credentials.")
        println("email address is valid $email")

        if(!hashEncoder.matches(password, user.password)) {
            throw BadCredentialsException("Invalid credentials.")
        }
        println("password is valid is valid $password")

        val newAccessToken = jwtService.generateAccessToken(user.id?.toHexString() ?: "")
        val newRefreshToken = jwtService.generateRefreshToken(user.id?.toHexString() ?: "")

        storeRefreshToken(user.id?.toLong()?:0, newRefreshToken)

        return TokenPair(
            accessToken = newAccessToken,
            refreshToken = newRefreshToken
        )
    }

    @Transactional
    fun refresh(refreshToken: String): TokenPair {
        if(!jwtService.validateRefreshToken(refreshToken)) {
            throw ResponseStatusException(HttpStatusCode.valueOf(401), "Invalid refresh token.")
        }

        val userId: String = jwtService.getUserIdFromToken(refreshToken)
        val user = userRepository.findById(userId.toLong()).orElseThrow {
            ResponseStatusException(HttpStatusCode.valueOf(401), "Invalid refresh token.")
        }


        val hashed = hashToken(refreshToken)
        refreshTokenRepository.findByIdAndToken(user.id?.toLong()?:0, hashed)
            ?: throw ResponseStatusException(
                HttpStatusCode.valueOf(401),
                "Refresh token not recognized (maybe used or expired?)"
            )

        refreshTokenRepository.deleteByIdAndToken(user.id?.toLong()?:0, hashed)

        val newAccessToken = jwtService.generateAccessToken(userId)
        val newRefreshToken = jwtService.generateRefreshToken(userId)

        storeRefreshToken(user.id?.toLong()?:0, newRefreshToken)

        return TokenPair(
            accessToken = newAccessToken,
            refreshToken = newRefreshToken
        )
    }

    private fun storeRefreshToken(userId: Long, rawRefreshToken: String) {
        val hashed = hashToken(rawRefreshToken)
        val expiryMs = jwtService.refreshTokenValidityMs
        val expiresAt = Instant.now().plusMillis(expiryMs)

        refreshTokenRepository.save(
            RefreshToken(
                id = userId,
                expires = expiresAt,
                token = hashed
            )
        )
    }

    private fun hashToken(token: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(token.encodeToByteArray())
        return Base64.getEncoder().encodeToString(hashBytes)
    }
}