package com.knowre.android.codilitytest.knowRedux


internal inline fun <T> T.applyIf(condition: Boolean, block: T.() -> T): T {
    return if (condition) {
        block()
    } else {
        this
    }
}

internal inline fun <T> T.applyIf(condition: T.() -> Boolean, block: T.() -> T): T {
    return if (condition()) {
        block()
    } else {
        this
    }
}

