package com.reillyhe.leetcodeanwser.thread

import java.util.concurrent.ThreadFactory

class EzThreadFactory: ThreadFactory {
    @Volatile
    var created = 0
    override fun newThread(r: Runnable?): Thread {
        val index = created
        created++
        return Thread(r, "Reilly-Thread-$index")
    }
}