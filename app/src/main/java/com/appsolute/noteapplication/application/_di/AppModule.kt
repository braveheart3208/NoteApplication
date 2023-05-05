package com.appsolute.noteapplication.application._di

import android.app.Application
import androidx.room.Room
import com.appsolute.noteapplication.data.data_source.NoteDatabase
import com.appsolute.noteapplication.data.data_source.NoteRepoImpl
import com.appsolute.noteapplication.domain.repository.NoteRepository
import com.appsolute.noteapplication.domain.usecase.DeleteNoteUsecase
import com.appsolute.noteapplication.domain.usecase.GetNoteByIdUsecase
import com.appsolute.noteapplication.domain.usecase.GetNotesUsecase
import com.appsolute.noteapplication.domain.usecase.InsertNoteUsecase
import com.appsolute.noteapplication.domain.usecase.NoteUsecases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Toan (Alex) Duong.
 * This project NoteApplication belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        )
            .build()
    }

    @Provides
    @Singleton
    fun providesNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepoImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun providesNoteUsecases(repository: NoteRepository): NoteUsecases {
        return NoteUsecases(
            GetNotesUsecase(repository),
            DeleteNoteUsecase(repository),
            InsertNoteUsecase(repository),
            GetNoteByIdUsecase(repository)
        )
    }
}