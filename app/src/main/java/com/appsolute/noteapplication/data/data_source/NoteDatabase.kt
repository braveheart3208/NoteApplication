package com.appsolute.noteapplication.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.appsolute.noteapplication.domain.model.Note

/**
 * Created by Alex Toan Duong on 4/05/2023.
 * This project belongs to Alex Toan Duong.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */
@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDAO

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}