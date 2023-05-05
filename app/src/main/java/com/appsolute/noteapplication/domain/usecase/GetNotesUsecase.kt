package com.appsolute.noteapplication.domain.usecase

import com.appsolute.noteapplication.domain.OrderType
import com.appsolute.noteapplication.domain.model.Note
import com.appsolute.noteapplication.domain.model.NoteOrder
import com.appsolute.noteapplication.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Toan (Alex) Duong.
 * This project NoteApplication belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */
class GetNotesUsecase(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(noteOrder: NoteOrder = NoteOrder.ByTitle(OrderType.Descending)): Flow<List<Note>> {
        return noteRepository.getNotes().map { notes ->
            when (noteOrder.orderType) {
                is OrderType.Ascending -> {
                    when (noteOrder) {
                        is NoteOrder.ByTitle -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.ByDate -> notes.sortedBy { it.timestamp }
                    }
                }

                is OrderType.Descending -> {
                    when (noteOrder) {
                        is NoteOrder.ByTitle -> notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.ByDate -> notes.sortedByDescending { it.timestamp }
                    }
                }
            }
        }
    }
}