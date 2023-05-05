package com.appsolute.noteapplication.domain.usecase

/**
 * Created by Toan (Alex) Duong.
 * This project NoteApplication belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */
data class NoteUsecases(
    val getNotes: GetNotesUsecase,
    val deleteNote: DeleteNoteUsecase,
    val insertNote : InsertNoteUsecase,
    val getNoteById : GetNoteByIdUsecase
)