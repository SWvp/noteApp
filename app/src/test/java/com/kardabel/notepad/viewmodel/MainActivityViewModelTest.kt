package com.kardabel.notepad.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kardabel.notepad.TestCoroutineRule
import com.kardabel.notepad.getApplicationDispatchersTest
import com.kardabel.notepad.model.NoteEntity
import com.kardabel.notepad.model.NoteViewState
import com.kardabel.notepad.repository.NoteRepository
import com.kardabel.notepad.ui.notes.MainActivityViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainActivityViewModelTest {

    @get: Rule
    val testCoroutineRule = TestCoroutineRule()

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `map viewState`() = runTest {

        // given
        val noteRepository: NoteRepository = mockk {

            every { getAllNotes() } returns flowOf(
                listOf(
                    NoteEntity(
                        "title",
                        "text",
                        1
                    )
                )
            )
        }

        val viewModel = MainActivityViewModel(
            noteRepository,
            getApplicationDispatchersTest(testCoroutineRule)
        )

        // WHEN
        viewModel.allNotes.observeForever { }
        val result = viewModel.allNotes.value

        // THEN
        assertEquals(
            listOf(
                NoteViewState(
                    "title",
                    "text",
                    1
                )
            ),
            result
        )
    }
}