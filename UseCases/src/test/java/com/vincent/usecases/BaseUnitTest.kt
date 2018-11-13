package com.vincent.usecases

import org.junit.Before
import org.mockito.MockitoAnnotations

open class BaseUnitTest {
    @Before
    @Throws
    open fun setUp() {
        MockitoAnnotations.initMocks(this)
    }
}