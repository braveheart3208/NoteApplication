package com.appsolute.noteapplication.domain.usecase

import com.appsolute.noteapplication.domain.model.Note
import com.appsolute.noteapplication.domain.repository.NoteRepository

/**
 * Created by Toan (Alex) Duong.
 * This project NoteApplication belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */
class GetNoteByIdUsecase(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(noteId: Int): Note? {
        return noteRepository.getNoteById(noteId)
    }
}