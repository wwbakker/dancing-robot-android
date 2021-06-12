package com.bennyplo.android_mooc_graphics_3d.robot

import com.bennyplo.android_mooc_graphics_3d.Coordinate
import com.bennyplo.android_mooc_graphics_3d.robot.LimbType.*
import com.bennyplo.android_mooc_graphics_3d.scale

class ScaleTransformation : Transformation {
    override fun transform(limbType: LimbType, limb: Array<Coordinate>): Array<Coordinate> {
        return when (limbType) {
            Head -> limb.scale(Robot.headSize, Robot.headSize, Robot.headSize)
            Neck -> limb.scale(Robot.neckHeight * 2.0, Robot.neckHeight, Robot.neckHeight * 2.0)
            Body -> limb
            Waist -> limb
            UpperLeftArm -> limb
            UpperRightArm -> limb
            LowerLeftArm -> limb
            LowerRightArm -> limb
            LeftHand -> limb
            RightHand -> limb
            UpperLeftLeg -> limb
            UpperRightLeg -> limb
            LowerLeftLeg -> limb
            LowerRightLeg -> limb
            LeftFoot -> limb
            RightFoot -> limb
        }
    }
}