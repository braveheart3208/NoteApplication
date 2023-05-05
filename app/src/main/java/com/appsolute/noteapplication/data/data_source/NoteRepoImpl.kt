package com.appsolute.noteapplication.data.data_source

import com.appsolute.noteapplication.domain.model.Note
import com.appsolute.noteapplication.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Toan (Alex) Duong.
 * This project NoteApplication belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */
class NoteRepoImpl(
    private val noteDao: NoteDAO
) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes()
    }

    override suspend fun getNoteById(noteId: Int): Note? {
        return noteDao.getNoteById(noteId)
    }

    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
}