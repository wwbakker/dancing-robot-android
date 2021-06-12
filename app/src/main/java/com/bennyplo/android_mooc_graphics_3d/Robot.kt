package com.bennyplo.android_mooc_graphics_3d

import android.graphics.Canvas
import android.graphics.Color
import kotlin.math.cos
import kotlin.math.sin

object Robot {


    val offsetZ = 0.0
    val offsetY = 150.0
    val headSize = 240.0
    val headY = offsetY + (headSize / 2.0)
    val neckHeight = 60.0
    val neckY = headY + (headSize / 2.0) + (neckHeight / 2.0)
    val bodyHeight = 600.0
    val bodyY = neckY + (neckHeight / 2.0) + (bodyHeight / 2.0)
    val waistHeight =  160.0
    val waistY = bodyY + (bodyHeight / 2.0) + (waistHeight / 2.0)

    val upperArmHeight = 240.0
    val upperArmY = bodyY - (bodyHeight / 2.0) + (upperArmHeight / 2.0)
    val lowerArmHeight = 320.0
    val lowerArmY = upperArmY + (upperArmHeight / 2.0) + (lowerArmHeight / 2.0)
    val handHeight = 80.0
    val handY = lowerArmY + (lowerArmHeight / 2.0) + (handHeight / 2.0)

    val upperLegHeight = 240.0
    val upperLegY = waistY + (waistHeight / 2.0) + (upperLegHeight / 2.0)
    val lowerLegHeight = 320.0
    val lowerLegY = upperLegY + (upperLegHeight / 2.0) + (lowerLegHeight / 2.0)
    val footHeight = 80.0
    val footY = lowerLegY + (lowerLegHeight / 2.0) + (footHeight / 2.0)

    val bodyWidth = 480.0
    val limbWidth = 160.0
    val leftArmX = - (bodyWidth / 2.0) - (limbWidth / 2.0)
    val rightArmX = + (bodyWidth / 2.0) + (limbWidth / 2.0)
    val leftLegX = - (bodyWidth / 2.0) + (limbWidth / 2.0)
    val rightLegX = + (bodyWidth / 2.0) - (limbWidth / 2.0)


    fun draw(canvas: Canvas, yRotationAngleInDegrees : Double,
             legStretchAngleInDegrees: Double, moveLeftLeg : Boolean) {

        val cube = DrawHelper.cube_vertices
        
        val head = cube
            .scale(headSize, headSize, headSize)
            .translate(0.0, headY, offsetZ)
            .quaternionRotationFromEulerAngles(yRotationAngleInDegrees, 0.0, 1.0, 0.0)
        val neck = cube
            .scale(neckHeight * 2.0, neckHeight, neckHeight * 2.0)
            .translate(0.0, neckY, offsetZ)
            .quaternionRotationFromEulerAngles(yRotationAngleInDegrees, 0.0, 1.0, 0.0)

        val body = cube
            .scale(bodyWidth, bodyHeight, 120.0)
            .translate(0.0, bodyY, offsetZ)
            .quaternionRotationFromEulerAngles(yRotationAngleInDegrees, 0.0, 1.0, 0.0)

        val waist = cube
            .scale(bodyWidth, waistHeight, 120.0)
            .translate(0.0, waistY, offsetZ)
            .quaternionRotationFromEulerAngles(yRotationAngleInDegrees, 0.0, 1.0, 0.0)

        val leftUpperArm = cube
            .scale(limbWidth, upperArmHeight, limbWidth)
            .translate(leftArmX, upperArmY, offsetZ)
            .quaternionRotationFromEulerAngles(yRotationAngleInDegrees, 0.0, 1.0, 0.0)

        val leftLowerArm = cube
            .scale(limbWidth, lowerArmHeight, limbWidth)
            .translate(leftArmX, lowerArmY, offsetZ)
            .quaternionRotationFromEulerAngles(yRotationAngleInDegrees, 0.0, 1.0, 0.0)

        val leftHand = cube
            .scale(limbWidth, handHeight, limbWidth + 100.0)
            .translate(leftArmX, handY, offsetZ - 50.0)
            .quaternionRotationFromEulerAngles(yRotationAngleInDegrees, 0.0, 1.0, 0.0)

        val rightUpperArm = cube
            .scale(limbWidth, upperArmHeight, limbWidth)
            .translate(rightArmX, upperArmY, offsetZ)
            .quaternionRotationFromEulerAngles(yRotationAngleInDegrees, 0.0, 1.0, 0.0)

        val rightLowerArm = cube
            .scale(limbWidth, lowerArmHeight, limbWidth)
            .translate(rightArmX, lowerArmY, offsetZ)
            .quaternionRotationFromEulerAngles(yRotationAngleInDegrees, 0.0, 1.0, 0.0)

        val rightHand = cube
            .scale(limbWidth, handHeight, limbWidth + 100.0)
            .translate(rightArmX, handY, offsetZ - 50.0)
            .quaternionRotationFromEulerAngles(yRotationAngleInDegrees, 0.0, 1.0, 0.0)

        // Rotating the leg the leg go up and forward, calculate by how much
        val upperLegRotationYOffset =
            (cos(Math.toRadians(legStretchAngleInDegrees)) * upperLegHeight) - upperLegHeight
        val upperLegRotationZOffset = - (sin(Math.toRadians(legStretchAngleInDegrees)) * upperLegHeight)


        val leftUpperLeg = cube
            .scale(limbWidth, upperLegHeight, limbWidth)
            .conditional(moveLeftLeg) {
                it.withOffset(limbWidth / 2.0, upperLegHeight / 2.0, -limbWidth / 2.0) {
                    it.quaternionRotationFromEulerAngles(legStretchAngleInDegrees, -1.0, 0.0, 0.0)
                }
            }
            .translate(leftLegX, upperLegY, offsetZ)
            .quaternionRotationFromEulerAngles(yRotationAngleInDegrees, 0.0, 1.0, 0.0)


        val leftLowerLeg = cube
            .scale(limbWidth, lowerLegHeight, limbWidth)
            .translate(leftLegX, lowerLegY, offsetZ)
            .conditional(moveLeftLeg) {
                it.translate(0.0, upperLegRotationYOffset, upperLegRotationZOffset)
            }
            .quaternionRotationFromEulerAngles(yRotationAngleInDegrees, 0.0, 1.0, 0.0)

        val leftFoot = cube
            .scale(limbWidth, footHeight, limbWidth + 100.0)
            .translate(leftLegX, footY, offsetZ - 50.0)
            .conditional(moveLeftLeg) {
                it.translate(0.0, upperLegRotationYOffset, upperLegRotationZOffset)
            }
            .quaternionRotationFromEulerAngles(yRotationAngleInDegrees, 0.0, 1.0, 0.0)

        val rightUpperLeg = cube
            .scale(limbWidth, upperLegHeight, limbWidth)
            .conditional(!moveLeftLeg) {
                it.withOffset(limbWidth / 2.0, upperLegHeight / 2.0, -limbWidth / 2.0) {
                    it.quaternionRotationFromEulerAngles(legStretchAngleInDegrees, -1.0, 0.0, 0.0)
                }
            }
            .translate(rightLegX, upperLegY, offsetZ)
            .quaternionRotationFromEulerAngles(yRotationAngleInDegrees, 0.0, 1.0, 0.0)

        val rightLowerLeg = cube
            .scale(limbWidth, lowerLegHeight, limbWidth)
            .conditional(!moveLeftLeg) {
                it.translate(0.0, upperLegRotationYOffset, upperLegRotationZOffset)
            }
            .translate(rightLegX, lowerLegY, offsetZ)
            .quaternionRotationFromEulerAngles(yRotationAngleInDegrees, 0.0, 1.0, 0.0)

        val rightFoot = cube
            .scale(limbWidth, footHeight, limbWidth + 100.0)
            .conditional(!moveLeftLeg) {
                it.translate(0.0, upperLegRotationYOffset, upperLegRotationZOffset)
            }
            .translate(rightLegX, footY, offsetZ - 50.0)
            .quaternionRotationFromEulerAngles(yRotationAngleInDegrees, 0.0, 1.0, 0.0)

        DrawHelper.drawCube(canvas, head.centerXOnScreen(), Color.BLUE)
        DrawHelper.drawCube(canvas, neck.centerXOnScreen(), Color.MAGENTA)
        DrawHelper.drawCube(canvas, body.centerXOnScreen(), Color.RED)
        DrawHelper.drawCube(canvas, waist.centerXOnScreen(), Color.MAGENTA)

        DrawHelper.drawCube(canvas, leftUpperArm.centerXOnScreen(), Color.BLUE)
        DrawHelper.drawCube(canvas, leftLowerArm.centerXOnScreen(), Color.GREEN)
        DrawHelper.drawCube(canvas, leftHand.centerXOnScreen(), Color.CYAN)
        DrawHelper.drawCube(canvas, rightUpperArm.centerXOnScreen(), Color.BLUE)
        DrawHelper.drawCube(canvas, rightLowerArm.centerXOnScreen(), Color.GREEN)
        DrawHelper.drawCube(canvas, rightHand.centerXOnScreen(), Color.CYAN)

        DrawHelper.drawCube(canvas, leftUpperLeg.centerXOnScreen(), Color.BLUE)
        DrawHelper.drawCube(canvas, leftLowerLeg.centerXOnScreen(), Color.GREEN)
        DrawHelper.drawCube(canvas, leftFoot.centerXOnScreen(), Color.RED)
        DrawHelper.drawCube(canvas, rightUpperLeg.centerXOnScreen(), Color.BLUE)
        DrawHelper.drawCube(canvas, rightLowerLeg.centerXOnScreen(), Color.GREEN)
        DrawHelper.drawCube(canvas, rightFoot.centerXOnScreen(), Color.RED)
    }

}