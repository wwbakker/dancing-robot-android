package com.bennyplo.android_mooc_graphics_3d.robot.transformations

import com.bennyplo.android_mooc_graphics_3d.Coordinate
import com.bennyplo.android_mooc_graphics_3d.quaternionRotationFromEulerAngles
import com.bennyplo.android_mooc_graphics_3d.robot.Animation
import com.bennyplo.android_mooc_graphics_3d.robot.AnimationHelper
import com.bennyplo.android_mooc_graphics_3d.robot.LimbType

class ViewAngleAnimation : Animation {
    var counter = 0L

    override fun update(counter : Long) {
        this.counter = counter % 6000L
    }

    private val viewAngleInDegrees : Double
        get() = AnimationHelper.interpolateBackAndForth(counter, 6000L, 90.0) + 180.0 - 45.0


    override fun transform(limbType: LimbType, limb: Array<Coordinate>): Array<Coordinate> {
        return limb.quaternionRotationFromEulerAngles(viewAngleInDegrees, 0.0, 1.0, 0.0)
    }
}

