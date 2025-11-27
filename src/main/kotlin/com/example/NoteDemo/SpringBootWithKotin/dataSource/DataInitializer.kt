package com.example.NoteDemo.SpringBootWithKotin.dataSource

import com.example.NoteDemo.SpringBootWithKotin.model.User
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/*
@Configuration
class DataInitializer {

    @Bean
    fun initDatabase(userRepository: UserRepository) = CommandLineRunner {
        // Check if data already exists
        if (userRepository.count() == 0L) {
            val users = listOf(
                User(name = "John Doe", email = "john@example.com",password = "202cb962ac59075b964b07152d234b70", age = 30),
                User(name = "Jane Smith", email = "jane@example.com", password = "202cb962ac59075b964b07152d234b70", age = 25),
                User(name = "Bob Johnson", email = "bob@example.com", password = "202cb962ac59075b964b07152d234b70", age = 35)
            )

            userRepository.saveAll(users)
            println("Initial data inserted: ${users.size} users")
        } else {
            println("Database already contains data. Skipping initialization.")
        }
    }
}*/
