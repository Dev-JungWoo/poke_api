package com.vincent.usecases

import com.vincent.entities.Pokemon
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
open class BaseUnitTest {
    @Before
    @Throws
    open fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    companion object {
        val SUCCESS_SEARCH_RESULT = listOf(
            Pokemon("poke1", "imageurl1", 10.0, 10.0),
            Pokemon("poke2", "imageurl2", 20.0, 20.0),
            Pokemon("poke3", "imageurl3", 30.0, 30.0)
        )
    }
}