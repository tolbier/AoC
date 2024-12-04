package com.xing.aoc24.day4

typealias Puzzle = List<Line>

fun Puzzle.countXMAS() = sumOf(Line::countXMAS)
fun Puzzle.countSAMX() = sumOf(Line::countSAMX)

fun Puzzle.width() = size
fun Puzzle.cell(x: Int, y: Int): Char = this.getOrNull(y)?.getOrNull(x)?:'.'


fun Puzzle.isXMas(x: Int, y: Int):Boolean {
    val c0 =cell(x, y)
    val c1 =cell(x-1, y-1)
    val c2 =cell(x+1, y-1)
    val c3 =cell(x-1, y+1)
    val c4 =cell(x+1, y+1)

    val result = (c0=='A') &&
            (
                    ( c1=='M' && c2=='M' && c3=='S' && c4=='S'  ) ||
                    ( c1=='M' && c2=='S' && c3=='M' && c4=='S'  ) ||
                    ( c1=='S' && c2=='S' && c3=='M' && c4=='M'  ) ||
                    ( c1=='S' && c2=='M' && c3=='S' && c4=='M'  )
            )
    return result
}

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
