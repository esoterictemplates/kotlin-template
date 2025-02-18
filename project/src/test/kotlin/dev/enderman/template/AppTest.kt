package dev.enderman.template

import kotlin.test.Test
import kotlin.test.assertNotNull

class AppTest : AbstractAppTest() {
    @Test fun `app has a greeting`() {
        assertNotNull(app.greeting, "The app should have a greeting.")
    }
}
