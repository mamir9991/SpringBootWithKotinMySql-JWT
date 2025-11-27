package com.example.NoteDemo.SpringBootWithKotin.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val jwtService: JwtService
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        println("doFilterInternal ${request.toString()}")
        val authHeader = request.getHeader("Authorization")
        println("doFilterInternal  authHeader ${authHeader}")
        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            println("doFilterInternal 1")
            if(jwtService.validateAccessToken(authHeader)) {
                println("doFilterInternal 2")
                val userId = jwtService.getUserIdFromToken(authHeader)
                val auth = UsernamePasswordAuthenticationToken(userId, null, emptyList())
                SecurityContextHolder.getContext().authentication = auth
                println("doFilterInternal 3")
            }
        }

        filterChain.doFilter(request, response)
    }
}