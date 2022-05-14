package com.reillyhe.leetcodeanwser.thread

import java.util.concurrent.Executors

object EZPool {
    val executors by lazy {
        Executors.newFixedThreadPool(8, EzThreadFactory())
    }
}