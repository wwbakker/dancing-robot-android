package com.bennyplo.android_mooc_graphics_3d

import android.content.Context
import android.graphics.Canvas
import android.view.View
import java.util.*
import kotlin.concurrent.timerTask
import kotlin.math.roundToInt

class DancingRobotView(context: Context?) : View(context, null)  {

    val view = this
    val timer = Timer()
    var viewAngle = 0.0
    var increaseViewAngle = true

    var legAngle = 0.0
    var increaseLegAngle = true
    var leftLeg = true

    val task = timerTask {
        // ViewAngle
        when {
            viewAngle > 45.0  -> increaseViewAngle = false
            viewAngle < -45.0 -> increaseViewAngle = true
        }
        viewAngle = if (increaseViewAngle) viewAngle + 0.1 else viewAngle - 0.1

        // LegAngle
        when {
            legAngle > 60.0  -> increaseLegAngle = false
            legAngle < 0 -> {
                increaseLegAngle = true
                leftLeg = !leftLeg
            }
        }
        legAngle = if (increaseLegAngle) legAngle + 0.45 else legAngle - 0.45

        view.invalidate()
    }

    init {
        timer.scheduleAtFixedRate(task, 100L, 2L)
    }
    

    override fun onDraw(canvas: Canvas) {
//        DrawHelper.drawHeightLine(canvas, lineY, screenCenterX * 2.0)
        super.onDraw(canvas)
        DrawHelper.drawText(canvas, "angle: ${viewAngle.roundToInt()} degrees")
        Robot.draw(canvas, viewAngle, legAngle, leftLeg)
    }
}