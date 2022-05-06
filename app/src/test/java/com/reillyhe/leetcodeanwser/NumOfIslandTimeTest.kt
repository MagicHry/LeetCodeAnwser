package com.reillyhe.leetcodeanwser
import com.reillyhe.leetcodeanwser.island.NumOfIsland
import com.reillyhe.leetcodeanwser.island.NumOfIslandTimeConsumingSolu
import org.junit.Test
import org.junit.Assert.*
class NumOfIslandTimeTest {

    @Test
    fun str_test() {
        val str = "12-25"
        val first = str.split('-')[0].toInt()
        val sec = str.split('-')[1].toInt()
        assertEquals(12,first)
        assertEquals(25,sec)
    }

    @Test
    fun num_of_island_simple_test() {
        val row1 = mutableListOf('1','1','1').toCharArray()
        val row2 = mutableListOf('1','0','0').toCharArray()
        val row3 = mutableListOf('1','1','1').toCharArray()
        val grid = mutableListOf(row1, row2, row3).toTypedArray()
        assertEquals(1, NumOfIsland.numIslands(grid))
    }

    @Test
    fun num_of_island_mid_test() {
        val row1 = mutableListOf('1','1','1','1','1').toCharArray()
        val row2 = mutableListOf('1','0','1','0','1').toCharArray()
        val row3 = mutableListOf('1','1','1','1','0').toCharArray()
        val row4 = mutableListOf('1','0','1','0','1').toCharArray()
        val row5 = mutableListOf('1','1','1','1','0').toCharArray()
        val grid = mutableListOf(row1, row2, row3, row4, row5).toTypedArray()
        assertEquals(2, NumOfIsland.numIslands(grid))
    }

    @Test
    fun num_of_island_hard_test() {
        val row1 = mutableListOf('1','1','1','1','0').toCharArray()
        val row2 = mutableListOf('1','1','0','0','1').toCharArray()
        val row3 = mutableListOf('0','1','1','1','0').toCharArray()
        val row4 = mutableListOf('1','1','0','0','1').toCharArray()
        val row5 = mutableListOf('0','1','0','1','0').toCharArray()
        val row6 = mutableListOf('0','1','0','1','0').toCharArray()
        val row7 = mutableListOf('0','1','0','1','0').toCharArray()
        val row8 = mutableListOf('0','1','0','1','0').toCharArray()
        val grid = mutableListOf(row1, row2, row3, row4, row5, row6, row7, row8).toTypedArray()
        assertEquals(4, NumOfIsland.numIslands(grid))
    }

    @Test
    fun num_of_island_extreme_test() {
        val row1 = mutableListOf('1','1','0','0').toCharArray()
        val row2 = mutableListOf('1','0','1','0').toCharArray()
        val row3 = mutableListOf('1','1','0','1').toCharArray()
        val row4 = mutableListOf('1','1','1','1').toCharArray()
        val grid = mutableListOf(row1, row2, row3, row4).toTypedArray()
        assertEquals(2, NumOfIsland.numIslands(grid))
    }
}