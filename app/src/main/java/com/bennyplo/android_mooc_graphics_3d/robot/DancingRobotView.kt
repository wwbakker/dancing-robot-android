package com.bennyplo.android_mooc_graphics_3d.robot

import android.content.Context
import android.graphics.Canvas
import android.view.View
import com.bennyplo.android_mooc_graphics_3d.DrawHelper
import java.util.*
import kotlin.concurrent.timerTask
import kotlin.math.roundToInt

class DancingRobotView(context: Context?) : View(context, null)  {

    val view = this
    val timer = Timer()
    val viewAngleAnimationState = ViewAngleAnimationState()
    val legAngleAnimationState = LegAnimationState()
    var armAnimationState = ArmAnimationState()

    val task = timerTask {
        viewAngleAnimationState.update()
        legAngleAnimationState.update()
        armAnimationState.update()

        view.invalidate()
    }

    init {
        timer.scheduleAtFixedRate(task, 100L, 2L)
    }
    

    override fun onDraw(canvas: Canvas) {
//        DrawHelper.drawHeightLine(canvas, lineY, screenCenterX * 2.0)
        super.onDraw(canvas)
        DrawHelper.drawText(canvas, "angle: ${viewAngleAnimationState.viewAngleInDegrees.roundToInt()} degrees")
        Robot.draw(canvas, viewAngleAnimationState, legAngleAnimationState, armAnimationState)
    }
}