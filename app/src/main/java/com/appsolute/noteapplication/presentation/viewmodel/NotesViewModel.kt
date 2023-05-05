package com.appsolute.noteapplication.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsolute.noteapplication.domain.OrderType
import com.appsolute.noteapplication.domain.model.Note
import com.appsolute.noteapplication.domain.model.NoteOrder
import com.appsolute.noteapplication.domain.usecase.NoteUsecases
import com.appsolute.noteapplication.presentation.note.NoteEvent
import com.appsolute.noteapplication.presentation.note.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Toan (Alex) Duong.
 * This project NoteApplication belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */
@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUsecases: NoteUsecases
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var getNotesJob: Job? = null

    private var recentlyDeletedNote: Note? = null

    init {
        getNotes(NoteOrder.ByDate(OrderType.Descending))
    }

    fun onEventCalled(event: NoteEvent) {
        when (event) {
            is NoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUsecases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }

            is NoteEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUsecases.insertNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }

            is NoteEvent.SortNote -> {
                if (state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }
                _state.value = state.value.copy(noteOrder = event.noteOrder)
            }

            is NoteEvent.ToggleSortNote -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        //Cancel previous job if available for get Notes
        getNotesJob?.cancel()
        //Get Notes
        getNotesJob = noteUsecases.getNotes(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(notes = notes, noteOrder = noteOrder)
            }
            .launchIn(viewModelScope)
    }
}