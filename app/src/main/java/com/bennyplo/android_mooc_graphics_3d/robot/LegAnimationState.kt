package com.bennyplo.android_mooc_graphics_3d.robot

import com.bennyplo.android_mooc_graphics_3d.Coordinate


class LegAnimationState(var legAngleInDegrees : Double = 0.0,
                        var leftLeg: Boolean = true,
                        private var increaseLegAngle : Boolean = true) : Animation {
    override fun update() {
        // LegAngle
        when {
            legAngleInDegrees > 60.0  -> increaseLegAngle = false
            legAngleInDegrees < 0 -> {
                increaseLegAngle = true
                leftLeg = !leftLeg
            }
        }
        legAngleInDegrees = if (increaseLegAngle) legAngleInDegrees + 0.45 else legAngleInDegrees - 0.45
    }

    override fun transform(limbType: LimbType, limb: Array<Coordinate>): Array<Coordinate> {
        TODO("Not yet implemented")
    }
}