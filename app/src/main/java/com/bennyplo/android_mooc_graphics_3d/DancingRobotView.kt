package com.bennyplo.android_mooc_graphics_3d

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.view.View
import java.util.*
import kotlin.concurrent.timerTask
import kotlin.math.roundToInt

class DancingRobotView(context: Context?) : View(context, null)  {

    val view = this
    val timer = Timer()
    var angle = 0.0
    val task = timerTask {
        angle = (angle + 0.1) % 360
        view.invalidate()
    }

    init {
        timer.scheduleAtFixedRate(task, 100L, 4L)
    }
    

    override fun onDraw(canvas: Canvas) {
//        DrawHelper.drawHeightLine(canvas, lineY, screenCenterX * 2.0)
        super.onDraw(canvas)
        DrawHelper.drawText(canvas, "angle: ${angle.roundToInt()} degrees")
        Robot.draw(canvas, angle)
    }
}