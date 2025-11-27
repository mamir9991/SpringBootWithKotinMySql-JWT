package com.example.NoteDemo.SpringBootWithKotin.services

import com.example.NoteDemo.SpringBootWithKotin.dataSource.NoteRepository
import com.example.NoteDemo.SpringBootWithKotin.model.Note
import org.springframework.stereotype.Service

@Service
class NoteService(
    private val repository: NoteRepository
) {
    fun getNotes():List<Note> = repository.getNotes()
    fun addNote(note: Note) = repository.addNote(note)

    fun updatedNote( note: Note) = repository.updatedNote(note)
    fun deleteNote(id:String) = repository.deleteNote(id)
}