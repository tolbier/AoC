package com.xing.aoc24.day4

typealias Puzzle = List<Line>

fun Puzzle.countXMAS() = sumOf(Line::countXMAS)
fun Puzzle.countSAMX() = sumOf(Line::countSAMX)

fun Puzzle.width() = size
fun Puzzle.rotate90ClockWise():Puzzle{
    var result = MutableList(size){""}
    (0..<width()).forEach { x ->
        for(line in this){
            val resultLine = result[x]
            val c = line.getOrNull(x)
            result[x] = resultLine + c
        }
    }

   return result
}
fun Puzzle.rotate45ClockWise():Puzzle{
    var result = MutableList(size*2 -1){""}
    (0..<size).forEach { y->
        var resultLine=""
        (0 ..y ).forEach { x->
            resultLine += this[y-x][x]
        }
        result[y]= resultLine
    }
    (size..<result.size).forEach { y->
        var resultLine=""
        (y-size+1..<size).forEach { x->
            resultLine += this[y-x][x]
        }
        result[y]= resultLine
    }

    return result
}
fun Puzzle.rotate45AnticlockWise():Puzzle{
    var result = MutableList(size*2 -1){""}
    (0..<size).forEach { resultY->
        var resultLine=""
        (size -resultY -1..<size).forEach { x->
            val y = x -(size -resultY -1)
            resultLine += this[y][x]
        }
        result[resultY]= resultLine
    }
    (size..<result.size).forEach { resultY->
        var resultLine=""
        (0.. result.size -1 - resultY).forEach { x->
            val y = x -(size -resultY -1)
            resultLine += this[y][x]
        }
        result[resultY]= resultLine
    }


    return result
}
