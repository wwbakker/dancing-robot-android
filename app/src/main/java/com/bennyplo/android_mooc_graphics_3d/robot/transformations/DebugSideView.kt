package com.bennyplo.android_mooc_graphics_3d.robot.transformations

import com.bennyplo.android_mooc_graphics_3d.Coordinate
import com.bennyplo.android_mooc_graphics_3d.quaternionRotationFromEulerAngles
import com.bennyplo.android_mooc_graphics_3d.robot.LimbType
import com.bennyplo.android_mooc_graphics_3d.robot.Transformation


class DebugSideView : Transformation {
    override fun transform(limbType: LimbType, limb: Array<Coordinate>): Array<Coordinate> {
        return limb.quaternionRotationFromEulerAngles(90.0, 0.0, 1.0, 0.0)
    }
}