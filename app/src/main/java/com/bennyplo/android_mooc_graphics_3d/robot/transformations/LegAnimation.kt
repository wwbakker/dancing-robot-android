package com.bennyplo.android_mooc_graphics_3d.robot.transformations

import com.bennyplo.android_mooc_graphics_3d.Coordinate
import com.bennyplo.android_mooc_graphics_3d.quaternionRotationFromEulerAngles
import com.bennyplo.android_mooc_graphics_3d.robot.Animation
import com.bennyplo.android_mooc_graphics_3d.robot.AnimationHelper
import com.bennyplo.android_mooc_graphics_3d.robot.LimbType
import com.bennyplo.android_mooc_graphics_3d.robot.LimbType.*
import com.bennyplo.android_mooc_graphics_3d.robot.Robot
import com.bennyplo.android_mooc_graphics_3d.translate
import com.bennyplo.android_mooc_graphics_3d.withOffset
import kotlin.math.cos
import kotlin.math.sin


class LegAnimation(private var counter : Long = 0L) : Animation {

    override fun update(counter : Long) {
        this.counter = counter % 6000L
    }

    private val enabled : Boolean
        get() = counter < 4000L


    private val legAngleInDegrees : Double
        get() = AnimationHelper.interpolateBackAndForth(counter, 1000L, 60.0)

    private val liftedLimbs : Set<LimbType>
        get() = if(counter % 2000L > 1000) setOf(UpperLeftLeg, LowerLeftLeg, LeftFoot) else setOf(UpperRightLeg, LowerRightLeg, RightFoot)

    override fun transform(limbType: LimbType, limb: Array<Coordinate>): Array<Coordinate> {
        if (!liftedLimbs.contains(limbType) || !enabled) {
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