package com.example.NoteDemo.SpringBootWithKotin.dataSource

import com.example.NoteDemo.SpringBootWithKotin.model.Note
import org.springframework.stereotype.Repository


@Repository
interface NoteRepository {
    fun getNotes(): List<Note>
    fun addNote(note: Note): List<Note>
    fun updatedNote(note: Note): List<Note>
    fun deleteNote(id:String): Note
}