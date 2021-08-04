package com.knowre.android.codilitytest.extensions

import android.view.View
import android.view.ViewTreeObserver
import androidx.core.view.ViewCompat


inline fun View.doOnPostLayout(crossinline action: (View) -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(
        object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewTreeObserver.removeOnGlobalLayoutListener(this)

                action(this@doOnPostLayout)
            }
        }
    )
}

inline fun View.doOnPostLayoutEager(crossinline action: (View) -> Unit) {
    if (ViewCompat.isLaidOut(this) && !this.isLayoutRequested) {
        action(this)
    } else {
        doOnPostLayout(action)
    }
}