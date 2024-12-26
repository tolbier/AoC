package com.xing.aoc24.day16

data class ReindeerScore(val reindeer: Reindeer = Reindeer(), val score: Score = 0L) : Comparable<ReindeerScore> {
    override fun compareTo(other: ReindeerScore): Int =
        this.score.compareTo(other.score)


}

