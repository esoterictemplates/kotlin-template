package dev.enderman.template

import kotlin.test.Test
import kotlin.test.assertNotNull

class AppTest {
    @Test fun `app has a greeting`() {
        val classUnderTest = App()
        assertNotNull(classUnderTest.greeting, "The app should have a greeting.")
    }
}
