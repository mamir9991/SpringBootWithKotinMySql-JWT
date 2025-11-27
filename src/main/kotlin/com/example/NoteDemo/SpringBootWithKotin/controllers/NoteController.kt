package com.example.NoteDemo.SpringBootWithKotin.controllers

import com.example.NoteDemo.SpringBootWithKotin.model.Note
import com.example.NoteDemo.SpringBootWithKotin.services.NoteService
import jakarta.websocket.server.PathParam
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.random.RandomGenerator.StreamableGenerator
import java.util.random.RandomGenerator as RandomGenerator1

@RestController
@RequestMapping("/notes")
class NoteController(
    private val noteService :NoteService
) {


    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(e: NoSuchElementException): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.NOT_FOUND)
    }

    @GetMapping
    fun getNotes() : List<Note> = noteService.getNotes()

    @PostMapping
    fun saveNote(@RequestBody note: Note):List<Note> = noteService.addNote(note)

    @PutMapping
    fun updateNote(@RequestBody note: Note):List<Note> = noteService.updatedNote(note)

    @DeleteMapping("/{id}")
    fun deleteNote(@PathVariable("id") id: String):Note = noteService.deleteNote(id)

}