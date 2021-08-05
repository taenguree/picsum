package com.knowre.android.codilitytest.extensions


internal fun <T> List<T>.swap(new: T, matcher: (T, T) -> Boolean, checkExists: Boolean = true): List<T> {
    if (checkExists) {
        check(this.any { matcher(it, new) }) { "no matching element found" }
    }

    val list = mutableListOf<T>()

    return this.mapTo(list) {
        if (matcher(it, new)) {
            new
        } else {
            it
        }
    }
}

internal fun <T> List<T>.swapOrAdd(new: List<T>, matcher: (T, T) -> Boolean): List<T>{
    var swapOrAdded = this.toMutableList()

    for (i in new.indices) {
        swapOrAdded = if (this.find { matcher(it, new[i]) } != null) {
            swapOrAdded.swap(new[i], matcher, checkExists = false).toMutableList()
        } else {
            swapOrAdded.apply { add(new[i]) }
        }
    }

    return swapOrAdded
}