package com.appsolute.noteapplication.domain.repository

import com.appsolute.noteapplication.domain.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * Created by Toan (Alex) Duong.
 * This project NoteApplication belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */
interface NoteRepository {
    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(noteId: Int): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)
}