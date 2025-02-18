package dev.enderman.template

import kotlin.test.BeforeTest

abstract class AbstractAppTest {
    protected lateinit var app: App

    @BeforeTest fun setUp() {
        app = App()
    }
}
