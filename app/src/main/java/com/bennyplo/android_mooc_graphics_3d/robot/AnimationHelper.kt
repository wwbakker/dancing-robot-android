package com.bennyplo.android_mooc_graphics_3d.robot

object AnimationHelper {
    fun interpolateBackAndForth(currentFrame: Long, totalLength: Long, maxValue : Double) : Double {
        val frame = currentFrame % totalLength
        val oneWayLength = totalLength / 2L
        val oneWayFrame = currentFrame % oneWayLength
        return if (frame < oneWayLength)
            oneWayFrame * maxValue / oneWayLength
        else
            maxValue - (oneWayFrame * maxValue / oneWayLength)
    }
}