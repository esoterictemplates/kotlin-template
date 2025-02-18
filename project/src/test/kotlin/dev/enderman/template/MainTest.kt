package dev.enderman.template

import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.Test

class MainTest {
    @Test fun `main method works`() {
        assertDoesNotThrow("The main method should work without throwing an exception.") {
            main()
        }
    }
}
