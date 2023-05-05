package com.appsolute.noteapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.appsolute.noteapplication.presentation.components.NoteDetailScreen
import com.appsolute.noteapplication.presentation.components.NotesScreen
import com.appsolute.noteapplication.presentation.viewmodel.NoteDetailViewModel
import com.appsolute.noteapplication.presentation.viewmodel.NotesViewModel
import com.appsolute.noteapplication.ui.theme.CleanArchitectureNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureNoteAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.NoteListScreen.route
                    ) {
                        composable(route = Screen.NoteListScreen.route) {
                            val noteListViewModel = hiltViewModel<NotesViewModel>()
                            NotesScreen(
                                navigationController = navController,
                                state = noteListViewModel.state.value,
                                onEvent = { event ->
                                    noteListViewModel.onEventCalled(event)
                                }
                            )
                        }

                        composable(route = Screen.NoteDetailScreen.route + "?noteId={noteId}&noteColor={noteColor}",
                            arguments = listOf(
                                navArgument(name = "noteId") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(name = "noteColor") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            val noteDetailViewModel = hiltViewModel<NoteDetailViewModel>()
                            NoteDetailScreen(
                                navController = navController,
                                noteColor = it.arguments?.getInt("noteColor") ?: -1,
                                titleState = noteDetailViewModel.noteTitle.value,
                                contentState = noteDetailViewModel.noteDescription.value,
                                colorState = noteDetailViewModel.colorSelection.value,
                                uiEventFlow = noteDetailViewModel.eventFlow,
                                onEvent = { event ->
                                    noteDetailViewModel.onEvent(event)
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}