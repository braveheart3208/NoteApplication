package com.appsolute.noteapplication.domain.usecase

import com.appsolute.noteapplication.domain.model.Note
import com.appsolute.noteapplication.domain.repository.NoteRepository

/**
 * Created by Toan (Alex) Duong.
 * This project NoteApplication belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */
class InsertNoteUsecase(
    private val noteRepository: NoteRepository
) {

    @Throws(Exception::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw Exception("Title can not be empty")
        }
        noteRepository.insertNote(note)
    }
}