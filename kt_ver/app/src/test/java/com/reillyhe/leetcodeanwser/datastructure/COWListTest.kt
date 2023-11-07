package com.reillyhe.leetcodeanwser.datastructure
import com.reillyhe.leetcodeanwser.thread.EZPool
import org.junit.Test
import org.junit.Assert.*
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.Executors
import java.util.concurrent.Future

class COWListTest {

    private val testCowLst by lazy {
        CopyOnWriteArrayList<String>()
    }

    @Test
    fun random_access_loop_test() {
        testCowLst.clear()
        //采用随机访问的方式迭代COW
        val initData = mutableListOf<TestPojo>(
            TestPojo("a"),
            TestPojo("b"),
            TestPojo("c"),
            TestPojo("d"),
            TestPojo("e"),
            TestPojo("f"),
            TestPojo("g"),
            TestPojo("h")
        )
        val writeJobs = mutableListOf<Future<Boolean>>()
        //循环改动
        for (threadIndex in 0.until(6)) {
            val ret = EZPool.executors.submit(object : Runnable{
                override fun run() {
                    Thread.sleep(1000L)
                    val tName = Thread.currentThread().name
                    println(" -------- Thead:$tName --------WRITE START")
                    println("change element at $threadIndex")
                    val pojo = initData.get(threadIndex)
                    pojo.value = pojo.value + "touched by $tName"
                    initData.set(threadIndex, pojo)
                    println(" -------- Thead:$tName --------WRITE END")
                }

            }, true)
            writeJobs.add(ret)
        }
        //读取
        val readJob = EZPool.executors.submit(object : Runnable{
            override fun run() {
                Thread.sleep(1000L)
                val tName = Thread.currentThread().name
                println(" -------- Thead:$tName --------READ START")
                for (element in initData) {
                    println("Element -> ${element.value}")
                }
                println(" -------- Thead:$tName --------READ END")
            }

        }, true)
        for (job in writeJobs) {
            job.get()
        }
        readJob.get()
        assertEquals(0,0)
    }


    @Test
    fun cow_remove_test() {
        testCowLst.clear()
        //采用随机访问的方式迭代COW
        val initData = mutableListOf<TestPojo>(
            TestPojo("a"),
            TestPojo("b"),
            TestPojo("c"),
            TestPojo("d"),
            TestPojo("e"),
            TestPojo("f"),
            TestPojo("g"),
            TestPojo("h")
        )
        val writeJobs = mutableListOf<Future<Boolean>>()
        //循环改动
        for (threadIndex in 0.until(6)) {
            val ret = EZPool.executors.submit(object : Runnable{
                override fun run() {
                    val tName = Thread.currentThread().name
//                    println(" -------- Thead:$tName --------WRITE START")
                    println("remove element by $tName")
                    initData.removeAt(0)
//                    println(" -------- Thead:$tName --------WRITE END")
                }

            }, true)
            writeJobs.add(ret)
        }
        //读取
        val readJob = EZPool.executors.submit(object : Runnable{
            override fun run() {
                val tName = Thread.currentThread().name
                println(" -------- Thead:$tName --------READ START")
                for (element in initData) {
                    println("Element -> ${element.value}")
                }
                println(" -------- Thead:$tName --------READ END")
            }

        }, true)
        for (job in writeJobs) {
            job.get()
        }
        readJob.get()
        assertEquals(0,0)
    }

    class TestPojo(var value: String)
}