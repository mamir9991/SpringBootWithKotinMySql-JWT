package com.example.NoteDemo.SpringBootWithKotin.dataSource

import com.example.NoteDemo.SpringBootWithKotin.model.Note
import org.springframework.stereotype.Repository



@Repository
class NoteRepositoryImpl:NoteRepository {

    val mockNote = mutableListOf<Note>(
        Note(title = "Title 1", content = "Content of First Note"),
        Note(title = "Title 2", content = "Content of Second Note"),
        Note(title = "Title 3", content = "Content of Third Note"),
        Note(title = "Title 4", content = "Content of Forth Note"),
        Note(title = "Title 5", content = "Content of Fifth Note"),
    )

    override fun getNotes(): List<Note> {
       return mockNote
    }

    override fun addNote(note: Note): List<Note> {
        if(mockNote.contains(note)){
            throw IllegalArgumentException("Note Already Exist")
        }else{
            mockNote.add(note)
            return mockNote
        }
    }

    override fun updatedNote(note: Note): List<Note> {
        val mNote = mockNote.firstOrNull { it.id == note.id }
        if (mNote!==null){
            val index = mockNote.indexOf(mNote)
            mockNote[index] = note
            return mockNote
        }else{
            throw NoSuchElementException("Update this id ${note.id} not Data Found in List")
        }
    }

    override fun deleteNote(id:String): Note {
        val note = mockNote.firstOrNull { it.id == id }
        if (note!=null) {
            val index = mockNote.indexOf(note)
            return  mockNote.removeAt(index)
        } else {
            throw NoSuchElementException("DELETE this id $id not Data Found in List")
        }
    }
}