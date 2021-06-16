package com.bennyplo.android_mooc_graphics_3d.robot.transformations

import com.bennyplo.android_mooc_graphics_3d.Coordinate
import com.bennyplo.android_mooc_graphics_3d.quaternionRotationFromEulerAngles
import com.bennyplo.android_mooc_graphics_3d.robot.Animation
import com.bennyplo.android_mooc_graphics_3d.robot.LimbType

class ViewAngleAnimation : Animation {
    var viewAngleInDegrees : Double = 0.0
    private var increaseViewAngle : Boolean = true

    override fun update(counter : Long) {
        // ViewAngle
        when {
            viewAngleInDegrees > 45.0  -> increaseViewAngle = false
            viewAngleInDegrees < -45.0 -> increaseViewAngle = true
        }
        viewAngleInDegrees = if (increaseViewAngle) viewAngleInDegrees + 0.1 else viewAngleInDegrees - 0.1
    }

    override fun transform(limbType: LimbType, limb: Array<Coordinate>): Array<Coordinate> {
        return limb.quaternionRotationFromEulerAngles(viewAngleInDegrees, 0.0, 1.0, 0.0)
    }
}

