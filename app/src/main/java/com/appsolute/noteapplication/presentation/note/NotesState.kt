package com.appsolute.noteapplication.presentation.note

import com.appsolute.noteapplication.domain.OrderType
import com.appsolute.noteapplication.domain.model.Note
import com.appsolute.noteapplication.domain.model.NoteOrder

/**
 * Created by Toan (Alex) Duong.
 * This project NoteApplication belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */
data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.ByDate(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
