package com.bennyplo.android_mooc_graphics_3d.robot.transformations

import com.bennyplo.android_mooc_graphics_3d.Coordinate
import com.bennyplo.android_mooc_graphics_3d.robot.LimbType
import com.bennyplo.android_mooc_graphics_3d.robot.LimbType.*
import com.bennyplo.android_mooc_graphics_3d.robot.Robot
import com.bennyplo.android_mooc_graphics_3d.robot.Transformation
import com.bennyplo.android_mooc_graphics_3d.scale

class ScaleTransformation : Transformation {
    override fun transform(limbType: LimbType, limb: Array<Coordinate>): Array<Coordinate> {
        return when (limbType) {
            Head -> limb.scale(Robot.headSize, Robot.headSize, Robot.headSize)
            Neck -> limb.scale(Robot.neckHeight * 2.0, Robot.neckHeight, Robot.neckHeight * 2.0)
            Body -> limb.scale(Robot.bodyWidth, Robot.bodyHeight, 120.0)
            Waist -> limb.scale(Robot.bodyWidth, Robot.waistHeight, 120.0)
            UpperLeftArm -> limb.scale(Robot.limbWidth, Robot.upperArmHeight, Robot.limbWidth)
            UpperRightArm -> limb.scale(Robot.limbWidth, Robot.upperArmHeight, Robot.limbWidth)
            LowerLeftArm -> limb.scale(Robot.limbWidth, Robot.lowerArmHeight, Robot.limbWidth)
            LowerRightArm -> limb.scale(Robot.limbWidth, Robot.lowerArmHeight, Robot.limbWidth)
            LeftHand -> limb.scale(Robot.limbWidth, Robot.handHeight, Robot.limbWidth + 100.0)
            RightHand -> limb.scale(Robot.limbWidth, Robot.handHeight, Robot.limbWidth + 100.0)
            UpperLeftLeg -> limb.scale(Robot.limbWidth, Robot.upperLegHeight, Robot.limbWidth)
            UpperRightLeg -> limb.scale(Robot.limbWidth, Robot.upperLegHeight, Robot.limbWidth)
            LowerLeftLeg -> limb.scale(Robot.limbWidth, Robot.lowerLegHeight, Robot.limbWidth)
            LowerRightLeg -> limb.scale(Robot.limbWidth, Robot.lowerLegHeight, Robot.limbWidth)
            LeftFoot -> limb.scale(Robot.limbWidth, Robot.footHeight, Robot.limbWidth + 100.0)
            RightFoot -> limb.scale(Robot.limbWidth, Robot.footHeight, Robot.limbWidth + 100.0)
        }
    }
}