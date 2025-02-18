package dev.enderman.template

import kotlin.test.Test
import kotlin.test.assertNotNull

class AppTest {
    @Test fun `app has a greeting`() {
        val app = App()
        assertNotNull(app.greeting, "The app should have a greeting.")
    }
}
