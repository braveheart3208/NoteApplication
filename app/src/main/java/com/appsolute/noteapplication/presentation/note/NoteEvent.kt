package com.appsolute.noteapplication.presentation.note

import com.appsolute.noteapplication.domain.model.Note
import com.appsolute.noteapplication.domain.model.NoteOrder

/**
 * Created by Toan (Alex) Duong.
 * This project NoteApplication belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */
sealed class NoteEvent {
    data class SortNote(val noteOrder: NoteOrder) : NoteEvent()
    data class DeleteNote(val note: Note) : NoteEvent()
    object RestoreNote : NoteEvent()
    object ToggleSortNote : NoteEvent()
}
