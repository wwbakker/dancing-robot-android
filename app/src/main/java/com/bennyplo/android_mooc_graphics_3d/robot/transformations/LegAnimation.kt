package com.bennyplo.android_mooc_graphics_3d.robot.transformations

import com.bennyplo.android_mooc_graphics_3d.Coordinate
import com.bennyplo.android_mooc_graphics_3d.quaternionRotationFromEulerAngles
import com.bennyplo.android_mooc_graphics_3d.robot.Animation
import com.bennyplo.android_mooc_graphics_3d.robot.LimbType
import com.bennyplo.android_mooc_graphics_3d.robot.LimbType.*
import com.bennyplo.android_mooc_graphics_3d.robot.Robot
import com.bennyplo.android_mooc_graphics_3d.translate
import com.bennyplo.android_mooc_graphics_3d.withOffset
import kotlin.math.cos
import kotlin.math.sin


class LegAnimation(var legAngleInDegrees : Double = 0.0,
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

    private val liftedLimbs : Set<LimbType>
        get() = if(leftLeg) setOf(UpperLeftLeg, LowerLeftLeg, LeftFoot) else setOf(UpperRightLeg, LowerRightLeg, RightFoot)


    override fun transform(limbType: LimbType, limb: Array<Coordinate>): Array<Coordinate> {
        if (!liftedLimbs.contains(limbType)) {
            return limb
        }

        // Rotating the leg the leg go up and forward, calculate by how much
        val upperLegRotationYOffset =
            (cos(Math.toRadians(legAngleInDegrees)) * Robot.upperLegHeight) - Robot.upperLegHeight
        val upperLegRotationZOffset = - (sin(Math.toRadians(legAngleInDegrees)) * Robot.upperLegHeight)

        return when(limbType) {
            UpperLeftLeg, UpperRightLeg ->
                limb.withOffset(Robot.limbWidth / 2.0, Robot.upperLegHeight / 2.0, -Robot.limbWidth / 2.0) {
                    it.quaternionRotationFromEulerAngles(legAngleInDegrees, -1.0, 0.0, 0.0)
                }
            LowerLeftLeg, LowerRightLeg ->
                limb.translate(0.0, upperLegRotationYOffset, upperLegRotationZOffset)
            LeftFoot, RightFoot ->
                limb.translate(0.0, upperLegRotationYOffset, upperLegRotationZOffset)
            else ->
                limb
        }
    }
}