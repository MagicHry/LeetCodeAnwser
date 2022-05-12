package com.reillyhe.leetcodeanwser.datastructure
import org.junit.Test
import org.junit.Assert.*
import java.util.concurrent.CopyOnWriteArrayList

class COWListTest {

    private val testCowLst by lazy {
        CopyOnWriteArrayList<String>()
    }

    @Test
    fun random_access_loop_test() {
        //采用随机访问的方式迭代COW
        val initData = mutableListOf("reilly","aviva","peter","hank","jason","alice-shallbe-uopdated")
    }
}