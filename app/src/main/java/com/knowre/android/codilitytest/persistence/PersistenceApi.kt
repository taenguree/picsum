package com.knowre.android.codilitytest.persistence


internal interface PersistenceApi<in K, T> {
    fun get(key: K): T?
    fun put(key: K, value: T)
}