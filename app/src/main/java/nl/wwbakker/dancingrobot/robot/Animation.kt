package nl.wwbakker.dancingrobot.robot

interface Animation : Transformation {
    fun update(counter : Long)
}