package com.example.NoteDemo.SpringBootWithKotin.dataSource.login

import com.example.NoteDemo.SpringBootWithKotin.model.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository



@Repository
interface RefreshTokenRepository: JpaRepository<RefreshToken, Long> {
    fun findByIdAndToken(id: Long, token: String): RefreshToken?
    fun deleteByIdAndToken(id: Long, token: String)
}