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


class ArmAnimation(private var increaseArmAngle : Boolean = true,
                   private var counter : Long = 0L) : Animation {

    enum class Phase {
        Forward,
        Side,
        Both
    }

    private val phase : Phase
        get() =
//            Phase.Forward
            when {
                counter < 2000L -> Phase.Forward
                counter < 4000L -> Phase.Side
                else -> Phase.Both
            }

    private val armAngleInDegrees : Double
        get() =
            when(phase) {
                Phase.Forward ->
                    interpolateBackAndForth(counter, 1000L, 45.0)
                Phase.Side ->
                    interpolateBackAndForth(counter, 1000L, 60.0)
                Phase.Both ->
                    interpolateBackAndForth(counter, 1000L, 45.0)
            }



    private fun interpolateBackAndForth(currentFrame: Long, totalLength: Long, maxValue : Double) : Double {
        val frame = currentFrame % totalLength
        val oneWayLength = totalLength / 2L
        val oneWayFrame = currentFrame % oneWayLength
        return if (frame < oneWayLength)
            oneWayFrame * maxValue / oneWayLength
        else
            maxValue - (oneWayFrame * maxValue / oneWayLength)
    }

    override fun update() {
        counter = System.currentTimeMillis() % 6000
    }

    private val liftedLimbs : Set<LimbType>
        get() =
            when(phase) {
                Phase.Forward, Phase.Side ->
                    if (counter % 2000L < 1000)
                        setOf(UpperLeftArm, LowerLeftArm, LeftHand)
                    else
                        setOf(UpperRightArm, LowerRightArm, RightHand)
                Phase.Both ->
                    setOf(UpperLeftArm, LowerLeftArm, LeftHand, UpperRightArm, LowerRightArm, RightHand)
            }

    override fun transform(limbType: LimbType, limb: Array<Coordinate>): Array<Coordinate> {
        if (!liftedLimbs.contains(limbType)) {
            return limb
        }

        return when(phase) {
            Phase.Forward ->
                transformForward(limbType, limb)
            Phase.Side ->
                transformSide(limbType, limb)
            Phase.Both ->
                transformSide(limbType, transformForward(limbType, limb))
        }
    }

    fun transformSide(limbType: LimbType, limb: Array<Coordinate>): Array<Coordinate> {
        // Rotating the arm the arm go up and to the sides, calculate by how much
        val upperArmRotationYOffset =
            (cos(Math.toRadians(armAngleInDegrees)) * Robot.upperArmHeight) - Robot.upperArmHeight
        val upperArmRotationXOffset = - (sin(Math.toRadians(armAngleInDegrees)) * Robot.upperArmHeight)

        val lowerArmRotationYOffset =
            (cos(Math.toRadians(armAngleInDegrees * 2.0)) * Robot.lowerArmHeight) - Robot.lowerArmHeight
        val lowerArmRotationXOffset = - (sin(Math.toRadians(armAngleInDegrees * 2.0)) * Robot.lowerArmHeight)

        return when (limbType) {
            UpperLeftArm ->
                limb.withOffset(
                    -Robot.limbWidth / 2.0,
                    Robot.upperArmHeight / 2.0,
                    -Robot.limbWidth / 2.0
                ) {
                    it.quaternionRotationFromEulerAngles(armAngleInDegrees, 0.0, 0.0, 1.0)
                }
            UpperRightArm ->
                limb.withOffset(
                    Robot.limbWidth / 2.0,
                    Robot.upperArmHeight / 2.0,
                    -Robot.limbWidth / 2.0
                ) {
                    it.quaternionRotationFromEulerAngles(armAngleInDegrees, 0.0, 0.0, -1.0)
                }
            LowerLeftArm ->
                limb.withOffset(-Robot.limbWidth / 2.0, Robot.lowerArmHeight / 2.0, -Robot.limbWidth / 2.0) {
                    it.quaternionRotationFromEulerAngles(armAngleInDegrees * 2.0, 0.0, 0.0, 1.0)
                }.translate(upperArmRotationXOffset, upperArmRotationYOffset, 0.0)

            LowerRightArm ->
                limb.withOffset(Robot.limbWidth / 2.0, Robot.lowerArmHeight / 2.0, -Robot.limbWidth / 2.0) {
                    it.quaternionRotationFromEulerAngles(armAngleInDegrees * 2.0, 0.0, 0.0, -1.0)
                }.translate(-upperArmRotationXOffset, upperArmRotationYOffset, 0.0)
            LeftHand ->
                limb.withOffset(-Robot.limbWidth / 2.0, Robot.handHeight / 2.0, -(Robot.handAndFootDepth) / 2.0) {
                    it.quaternionRotationFromEulerAngles(armAngleInDegrees * 2.0, 0.0, 0.0, 1.0)
                }.translate(upperArmRotationXOffset + lowerArmRotationXOffset, lowerArmRotationYOffset + upperArmRotationYOffset, 0.0)
            RightHand ->
                limb.withOffset(Robot.limbWidth / 2.0, Robot.handHeight / 2.0, -(Robot.handAndFootDepth) / 2.0) {
                    it.quaternionRotationFromEulerAngles(armAngleInDegrees * 2.0, 0.0, 0.0, -1.0)
                }.translate(-upperArmRotationXOffset - lowerArmRotationXOffset, lowerArmRotationYOffset + upperArmRotationYOffset, 0.0)
            else ->
                limb
        }
    }

    fun transformForward(limbType: LimbType, limb: Array<Coordinate>): Array<Coordinate> {
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
                limb.withOffset(Robot.limbWidth / 2.0, Robot.handHeight / 2.0, -(Robot.handAndFootDepth) / 2.0) {
                    it.quaternionRotationFromEulerAngles(armAngleInDegrees * 2.0, -1.0, 0.0, 0.0)
                }
                    .translate(0.0, upperArmRotationYOffset + lowerArmRotationYOffset, upperArmRotationZOffset + lowerArmRotationZOffset)
            else ->
                limb
        }
    }


}