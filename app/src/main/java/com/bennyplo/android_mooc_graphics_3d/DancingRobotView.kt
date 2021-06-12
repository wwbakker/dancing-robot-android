package com.bennyplo.android_mooc_graphics_3d

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.view.View

class DancingRobotView(context: Context?) : View(context, null)  {

    private val screenCenterX = Resources.getSystem().displayMetrics.widthPixels / 2.0

    val offsetZ = 60.0
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
    val leftArmX = screenCenterX - (bodyWidth / 2.0) - (limbWidth / 2.0)
    val rightArmX = bodyWidth + limbWidth
    val leftLegX = screenCenterX - (bodyWidth / 2.0) + (limbWidth / 2.0)
    val rightLegX = bodyWidth - limbWidth

    val head = DrawHelper.cube_vertices
        .scale(headSize, headSize, headSize)
        .translate(screenCenterX, headY, offsetZ)
    val neck = DrawHelper.cube_vertices
        .scale(neckHeight * 2.0, neckHeight, neckHeight * 2.0)
        .translate(screenCenterX, neckY, offsetZ)
    val body = DrawHelper.cube_vertices
        .scale(bodyWidth, bodyHeight, 120.0)
        .translate(screenCenterX, bodyY, offsetZ)
    val waist = DrawHelper.cube_vertices
        .scale(bodyWidth, waistHeight, 120.0)
        .translate(screenCenterX, waistY, offsetZ)

    val leftUpperArm = DrawHelper.cube_vertices
        .scale(limbWidth, upperArmHeight, 120.0)
        .translate(leftArmX, upperArmY, offsetZ)
    val leftLowerArm = DrawHelper.cube_vertices
        .scale(limbWidth, lowerArmHeight, 120.0)
        .translate(leftArmX, lowerArmY, offsetZ)
    val leftHand = DrawHelper.cube_vertices
        .scale(limbWidth, handHeight, 120.0)
        .translate(leftArmX, handY, offsetZ)
    val rightUpperArm = leftUpperArm.translate(rightArmX, 0.0, 0.0)
    val rightLowerArm = leftLowerArm.translate(rightArmX, 0.0, 0.0)
    val rightHand = leftHand.translate(rightArmX, 0.0, 0.0)
    
    val leftUpperLeg = DrawHelper.cube_vertices
        .scale(limbWidth, upperLegHeight, 120.0)
        .translate(leftLegX, upperLegY, 80.0)
    val leftLowerLeg = DrawHelper.cube_vertices
        .scale(limbWidth, lowerLegHeight, 120.0)
        .translate(leftLegX, lowerLegY, 80.0)
    val leftFoot = DrawHelper.cube_vertices
        .scale(limbWidth, footHeight, 120.0)
        .translate(leftLegX, footY, 80.0)
    val rightUpperLeg = leftUpperLeg.translate(rightLegX, 0.0, 0.0)
    val rightLowerLeg = leftLowerLeg.translate(rightLegX, 0.0, 0.0)
    val rightFoot = leftFoot.translate(rightLegX, 0.0, 0.0)
    
    

    override fun onDraw(canvas: Canvas) {

//        DrawHelper.drawHeightLine(canvas, lineY, screenCenterX * 2.0)
        DrawHelper.drawCube(canvas, head)
        DrawHelper.drawCube(canvas, neck)
        DrawHelper.drawCube(canvas, body)
        DrawHelper.drawCube(canvas, waist)

        DrawHelper.drawCube(canvas, leftUpperArm)
        DrawHelper.drawCube(canvas, leftLowerArm)
        DrawHelper.drawCube(canvas, leftHand)
        DrawHelper.drawCube(canvas, rightUpperArm)
        DrawHelper.drawCube(canvas, rightLowerArm)
        DrawHelper.drawCube(canvas, rightHand)
        
        DrawHelper.drawCube(canvas, leftUpperLeg)
        DrawHelper.drawCube(canvas, leftLowerLeg)
        DrawHelper.drawCube(canvas, leftFoot)
        DrawHelper.drawCube(canvas, rightUpperLeg)
        DrawHelper.drawCube(canvas, rightLowerLeg)
        DrawHelper.drawCube(canvas, rightFoot)

        super.onDraw(canvas)

    }
}