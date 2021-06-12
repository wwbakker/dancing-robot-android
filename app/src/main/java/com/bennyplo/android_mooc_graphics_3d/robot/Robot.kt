package com.bennyplo.android_mooc_graphics_3d.robot

import android.graphics.Canvas
import android.graphics.Color
import com.bennyplo.android_mooc_graphics_3d.*
import com.bennyplo.android_mooc_graphics_3d.robot.LimbType.*
import com.bennyplo.android_mooc_graphics_3d.robot.transformations.*

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

    val transformations = listOf(
        ScaleTransformation(),
        LegAnimation(),
        MoveLimbToPositionInBodyTransformation(),
        ViewAngleAnimation(),
        CenterOnScreenTransformation()
    )

    fun update() {
        transformations.forEach { when(it) {
            is Animation ->
                it.update()
        } }
    }

    fun draw(canvas: Canvas) {
        val cube = DrawHelper.cube_vertices
        values().map { limbType ->
            Pair(transformations.fold(cube) { limb, transformation -> transformation.transform(limbType, limb) }, limbType)
        }.forEach { limbAndLimbType ->
            DrawHelper.drawCube(canvas, limbAndLimbType.first, limbToColor(limbAndLimbType.second))
        }
    }

    private fun limbToColor(limbType: LimbType) : Int {
        return when(limbType) {
            Head -> Color.BLUE
            Neck -> Color.MAGENTA
            Body -> Color.RED
            Waist -> Color.MAGENTA
            UpperLeftArm, UpperRightArm -> Color.BLUE
            LowerLeftArm, LowerRightArm -> Color.GREEN
            LeftHand, RightHand -> Color.CYAN
            UpperLeftLeg, UpperRightLeg ->  Color.BLUE
            LowerLeftLeg, LowerRightLeg -> Color.GREEN
            LeftFoot, RightFoot -> Color.RED
        }
    }

}