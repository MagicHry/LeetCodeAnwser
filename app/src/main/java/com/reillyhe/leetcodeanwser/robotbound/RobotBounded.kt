package com.reillyhe.leetcodeanwser.robotbound

object RobotBounded {

    /**
     * 核心思路是利用指令集合进行迭代看看能否回到原点
     */
    fun isRobotBounded(instructions: String): Boolean {
        //初始向量
        val originVec = RobotVector(0,0)
        val instructionsAsChar = instructions.toCharArray()
        var curDirection = Direction.NORTH
        val tmpVec = RobotVector(0,0)
        var iter = 0
        while (iter in 0..3) {
            //4次如果还找不到循环，即可退出
            for (curInstruction in instructionsAsChar) {
                if (curInstruction == 'G') {
                    //创建带符号，标明方向的单位向量
                    genUnitVec(curDirection, tmpVec)
                    //向量运算生成新的位置
                    originVec.add(tmpVec)
                } else {
                    //调整方向
                    curDirection = adjustDirection(curInstruction, curDirection)
                }
            }
            //检查是否满足情况
            println("after iteration : $iter now vector looks like -> $originVec and direction -> $curDirection")
            if (originVec.returnToOrigin() && curDirection == Direction.NORTH) return true
            iter++
        }
        return false
    }

    /**
     * 根据方向产生正确的向量
     */
    private fun genUnitVec(direction: Direction, vector: RobotVector) {
         when (direction) {
            Direction.NORTH -> {
                vector.x = 0
                vector.y = 1
            }
            Direction.SOUTH -> {
                vector.x = 0
                vector.y = -1
            }
            Direction.EAST -> {
                vector.x = 1
                vector.y = 0
            }
            Direction.WEST -> {
                vector.x = -1
                vector.y = 0
            }
        }
    }

    private fun adjustDirection(instruction: Char, direction: Direction): Direction {
        return when (direction) {
            Direction.NORTH -> {
                if (instruction == 'L') Direction.WEST else Direction.EAST
            }
            Direction.SOUTH -> {
                if (instruction == 'L') Direction.EAST else Direction.WEST
            }
            Direction.EAST -> {
                if (instruction == 'L') Direction.NORTH else Direction.SOUTH
            }
            Direction.WEST -> {
                if (instruction == 'L') Direction.SOUTH else Direction.NORTH
            }
        }
    }

    /**
     * 抽象当前机器人位置的向量
     */
    data class RobotVector(var x:Int, var y:Int) {

        fun add(otherVec: RobotVector) {
            x += otherVec.x
            y += otherVec.y
        }

        fun returnToOrigin(): Boolean {
            if (x == 0 && y == 0) return true
            return false
        }

        override fun toString(): String {
            return "RobotVector(x=$x, y=$y)"
        }


    }

    /**
     * 方位
     */
    enum class Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }
}