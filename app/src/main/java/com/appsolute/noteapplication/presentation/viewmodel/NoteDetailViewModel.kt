package com.appsolute.noteapplication.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsolute.noteapplication.application.core.UiEvent
import com.appsolute.noteapplication.domain.model.Note
import com.appsolute.noteapplication.domain.usecase.NoteUsecases
import com.appsolute.noteapplication.presentation.addnote.NoteDetailEvent
import com.appsolute.noteapplication.presentation.addnote.NoteTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Toan (Alex) Duong.
 * This project NoteApplication belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */
@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val noteUsecases: NoteUsecases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _noteTitle = mutableStateOf(NoteTextFieldState(hint = "Enter note title here..."))
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteDescription =
        mutableStateOf(NoteTextFieldState(hint = "Enter some note content..."))
    val noteDescription: State<NoteTextFieldState> = _noteDescription

    private val _colorSelection = mutableStateOf(Note.noteColors.random().toArgb())
    val colorSelection: State<Int> = _colorSelection

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val eventFlow = _uiEvent.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    noteUsecases.getNoteById(noteId)?.also { note ->
                        currentNoteId = note.id

                        _noteTitle.value = noteTitle.value.copy(text = note.title)
                        _noteDescription.value = _noteDescription.value.copy(text = note.content)
                        _colorSelection.value = note.color
                    }
                }
            }
        }
    }

    fun onEvent(event: NoteDetailEvent) {
        when (event) {
            is NoteDetailEvent.TitleEntered -> {
                _noteTitle.value = noteTitle.value.copy(text = event.value)
            }

            is NoteDetailEvent.ChangeColor -> {
                _colorSelection.value = event.color
            }

            is NoteDetailEvent.ChangeContentFocus -> {
                _noteDescription.value =
                    _noteDescription.value.copy(isHintVisible = !event.focusState.isFocused && noteDescription.value.text.isBlank())
            }

            is NoteDetailEvent.ChangeTitleFocus -> {
                _noteTitle.value =
                    noteTitle.value.copy(isHintVisible = !event.focusState.isFocused && noteTitle.value.text.isBlank())
            }

            is NoteDetailEvent.ContentEntered -> {
                _noteDescription.value = _noteDescription.value.copy(text = event.value)
            }

            is NoteDetailEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUsecases.insertNote(
                            Note(
                                title = noteTitle.value.text,
                                content = noteDescription.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = colorSelection.value,
                                id = currentNoteId
                            )
                        )

                        _uiEvent.emit(UiEvent.SaveNote)
                    } catch (exception: Exception) {
                        _uiEvent.emit(UiEvent.ShowSnackBar(exception.message ?: "Try again."))
                    }
                }
            }
        }
    }
}