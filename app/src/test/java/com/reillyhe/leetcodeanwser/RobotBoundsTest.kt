package com.reillyhe.leetcodeanwser
import com.reillyhe.leetcodeanwser.robotbound.RobotBounded
import org.junit.Test
import org.junit.Assert.*
class RobotBoundsTest {

    @Test
    fun robot_bound_ez_test1() {
        val should = true
        var ret = RobotBounded.isRobotBounded("GGLLGG")
        assertEquals(should, ret)
    }

    @Test
    fun robot_bound_ez_test2() {
        val should = true
        var ret = RobotBounded.isRobotBounded("LLLG")
        assertEquals(should, ret)
    }

    @Test
    fun robot_bound_mid_test1() {
        val should = false
        var ret = RobotBounded.isRobotBounded("GLGLGRGRR")
        assertEquals(should, ret)
    }

    @Test
    fun robot_bound_mid_test2() {
        val should = true
        var ret = RobotBounded.isRobotBounded("GL")
        assertEquals(should, ret)
    }
}