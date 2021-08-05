package com.knowre.android.codilitytest

import kotlinx.coroutines.*
import org.junit.Test

import org.junit.Assert.*
import java.lang.Exception

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun a() {
        runBlocking {
            launch {
                var job: Job? = null
                suspend fun execute(id: Int) {
                    if (job?.isCompleted == false) {
                        println("still working $id")
                    }

                    job?.cancel()
                    job = SupervisorJob()

                    withContext(job!!) {
                        try {
                            withContext(Dispatchers.IO) {
                                println("execution start $id")
                                delay(1000)
                                println("execution completed $id")
                            }
                        } catch(e: Exception) {
                            println("exception: e $id")
                        }
                    }
                }

                launch { execute(id = 1) }

                delay(2000)

                launch { execute(id = 2) }

                delay(2000)
                println("parent completed")
            }


        }
    }
}