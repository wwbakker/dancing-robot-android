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


class ArmAnimation(var armAngleInDegrees : Double = 0.0,
                   var leftArm: Boolean = true,
                   private var increaseArmAngle : Boolean = true) : Animation {
    override fun update() {
        // ArmAngle
        when {
            armAngleInDegrees > 60.0  -> increaseArmAngle = false
            armAngleInDegrees < 0 -> {
                increaseArmAngle = true
                leftArm = !leftArm
            }
        }
        armAngleInDegrees = if (increaseArmAngle) armAngleInDegrees + 0.45 else armAngleInDegrees - 0.45
    }

    private val liftedLimbs : Set<LimbType>
        get() = if(leftArm) setOf(UpperLeftArm, LowerLeftArm, LeftHand)
                else setOf(UpperRightArm, LowerRightArm, RightHand)

    override fun transform(limbType: LimbType, limb: Array<Coordinate>): Array<Coordinate> {
        if (!liftedLimbs.contains(limbType)) {
            return limb
        }

        // Rotating the arm the arm go up and forward, calculate by how much
        val upperArmRotationYOffset =
            (cos(Math.toRadians(armAngleInDegrees)) * Robot.upperArmHeight) - Robot.upperArmHeight
        val upperArmRotationZOffset = - (sin(Math.toRadians(armAngleInDegrees)) * Robot.upperArmHeight)

        val lowerArmRotationYOffset =
            (cos(Math.toRadians(armAngleInDegrees * 2.0)) * Robot.lowerArmHeight) - Robot.lowerArmHeight
        val lowerArmRotationZOffset = - (sin(Math.toRadians(armAngleInDegrees * 2.0)) * Robot.lowerArmHeight)



        return when(limbType) {
            UpperLeftArm, UpperRightArm ->
                limb.withOffset(Robot.limbWidth / 2.0, Robot.upperArmHeight / 2.0, -Robot.limbWidth / 2.0) {
                    it.quaternionRotationFromEulerAngles(armAngleInDegrees, -1.0, 0.0, 0.0)
                }
            LowerLeftArm, LowerRightArm ->
                limb.withOffset(Robot.limbWidth / 2.0, Robot.lowerArmHeight / 2.0, -Robot.limbWidth / 2.0) {
                    it.quaternionRotationFromEulerAngles(armAngleInDegrees * 2.0, -1.0, 0.0, 0.0)
                }.translate(0.0, upperArmRotationYOffset, upperArmRotationZOffset)

            LeftHand, RightHand ->
                limb.withOffset(Robot.limbWidth / 2.0, Robot.handHeight / 2.0, -(Robot.limbWidth + 100.0) / 2.0) {
                    it.quaternionRotationFromEulerAngles(armAngleInDegrees * 2.0, -1.0, 0.0, 0.0)
                }
                    .translate(0.0, upperArmRotationYOffset + lowerArmRotationYOffset, upperArmRotationZOffset + lowerArmRotationZOffset)
            else ->
                limb
        }
    }


}