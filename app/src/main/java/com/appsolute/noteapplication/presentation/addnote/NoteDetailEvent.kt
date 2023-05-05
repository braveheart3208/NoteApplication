package com.appsolute.noteapplication.presentation.addnote

import androidx.compose.ui.focus.FocusState

/**
 * Created by Toan (Alex) Duong.
 * This project NoteApplication belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */
sealed class NoteDetailEvent {
    data class TitleEntered(val value: String) : NoteDetailEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : NoteDetailEvent()
    data class ContentEntered(val value: String) : NoteDetailEvent()
    data class ChangeContentFocus(val focusState: FocusState) : NoteDetailEvent()
    data class ChangeColor(val color: Int) : NoteDetailEvent()
    object SaveNote : NoteDetailEvent()
}