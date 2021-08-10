package com.knowre.android.codilitytest.store


internal interface StoreApi<in K, T> {
    fun getAll(): List<T>
    fun get(key: K): T?
    fun put(key: K, value: T)
}