package com.appsolute.noteapplication.presentation.components

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.appsolute.noteapplication.application.core.UiEvent
import com.appsolute.noteapplication.domain.model.Note
import com.appsolute.noteapplication.presentation.addnote.NoteDetailEvent
import com.appsolute.noteapplication.presentation.addnote.NoteTextFieldState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by Toan (Alex) Duong.
 * This project NoteApplication belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailScreen(
    navController: NavController,
    noteColor: Int,
    titleState: NoteTextFieldState,
    contentState: NoteTextFieldState,
    colorState: Int,
    uiEventFlow: Flow<UiEvent>,
    onEvent: (NoteDetailEvent) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true, block = {
        uiEventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.NoteSaved -> {
                    //Go back to previous screen
                    navController.navigateUp()
                }

                is UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    })

    val noteBackgroundAnimatable = remember {
        Animatable(
            Color(if (noteColor != -1) noteColor else colorState)
        )
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(NoteDetailEvent.SaveNote)
                },
                containerColor = Color.LightGray
            ) {
                Icon(imageVector = Icons.Default.Send, contentDescription = "Save Note")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(noteBackgroundAnimatable.value)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Note.noteColors.forEach { color ->
                    val colorInt = color.toArgb()
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .shadow(15.dp, CircleShape)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = 3.dp,
                                color = if (noteColor == colorInt) {
                                    Color.Black
                                } else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                scope.launch {
                                    noteBackgroundAnimatable.animateTo(
                                        targetValue = Color(colorInt),
                                        animationSpec = tween(durationMillis = 500)
                                    )
                                }
                                onEvent(NoteDetailEvent.ChangeColor(colorInt))
                            }
                    )
                }
            }
            //Title
            Spacer(modifier = Modifier.height(16.dp))
            NoteItemTextInput(
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = {
                    onEvent(NoteDetailEvent.TitleEntered(it))
                },
                onFocusChange = {
                    onEvent(NoteDetailEvent.ChangeTitleFocus(it))
                },
                singleLine = true,
                hintVisible = titleState.isHintVisible,
                textStyle = MaterialTheme.typography.headlineMedium
            )
            //Content
            Spacer(modifier = Modifier.height(16.dp))
            NoteItemTextInput(
                text = contentState.text,
                hint = contentState.hint,
                onValueChange = {
                    onEvent(NoteDetailEvent.ContentEntered(it))
                },
                onFocusChange = {
                    onEvent(NoteDetailEvent.ChangeContentFocus(it))
                },
                singleLine = false,
                hintVisible = contentState.isHintVisible,
                textStyle = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}