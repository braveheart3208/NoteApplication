package com.appsolute.noteapplication.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appsolute.noteapplication.domain.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * Created by Alex Toan Duong on 4/05/2023.
 * This project belongs to Alex Toan Duong.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */
@Dao
interface NoteDAO {
    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = :noteId")
    suspend fun getNoteById(noteId: Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}