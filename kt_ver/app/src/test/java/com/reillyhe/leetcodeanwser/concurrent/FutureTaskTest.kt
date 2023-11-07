package com.reillyhe.leetcodeanwser.concurrent
import com.reillyhe.leetcodeanwser.concurrentdemo.WaitTest
import org.junit.Test
import org.junit.Assert.*
class FutureTaskTest {

    @Test
    fun wait_different_obj_test() {
        val tester = WaitTest()
        val t = Thread(object : Runnable{
            override fun run() {
                tester.doWaitViaMethodSync()
            }
        }, "reilly-test-thread")
        t.start()
        Thread.sleep(7000L)
        assertEquals(0,0)
    }

    @Test
    fun wait_single_obj_test() {
        val tester = WaitTest()
        val t = Thread(object : Runnable{
            override fun run() {
                tester.doWaitViaSingleLock()
            }
        }, "reilly-test-thread")
        t.start()
        Thread.sleep(7000L)
        assertEquals(0,0)
    }
}