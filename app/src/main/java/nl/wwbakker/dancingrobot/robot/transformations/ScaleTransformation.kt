package nl.wwbakker.dancingrobot.robot.transformations

import nl.wwbakker.dancingrobot.robot.LimbType
import nl.wwbakker.dancingrobot.robot.LimbType.*
import nl.wwbakker.dancingrobot.robot.Robot
import nl.wwbakker.dancingrobot.robot.Transformation
import nl.wwbakker.dancingrobot.scale

class ScaleTransformation : Transformation {
    override fun transform(limbType: LimbType, limb: Array<nl.wwbakker.dancingrobot.Coordinate>): Array<nl.wwbakker.dancingrobot.Coordinate> {
        return when (limbType) {
            Head -> limb.scale(Robot.headSize, Robot.headSize, Robot.headSize)
            Neck -> limb.scale(Robot.neckHeight * 2.0, Robot.neckHeight, Robot.neckHeight * 2.0)
            Body -> limb.scale(Robot.bodyWidth, Robot.bodyHeight, Robot.bodyDepth)
            Waist -> limb.scale(Robot.bodyWidth, Robot.waistHeight, Robot.bodyDepth)
            UpperLeftArm -> limb.scale(Robot.limbWidth, Robot.upperArmHeight, Robot.limbWidth)
            UpperRightArm -> limb.scale(Robot.limbWidth, Robot.upperArmHeight, Robot.limbWidth)
            LowerLeftArm -> limb.scale(Robot.limbWidth, Robot.lowerArmHeight, Robot.limbWidth)
            LowerRightArm -> limb.scale(Robot.limbWidth, Robot.lowerArmHeight, Robot.limbWidth)
            LeftHand -> limb.scale(Robot.limbWidth, Robot.handHeight, Robot.handAndFootDepth)
            RightHand -> limb.scale(Robot.limbWidth, Robot.handHeight, Robot.handAndFootDepth)
            UpperLeftLeg -> limb.scale(Robot.limbWidth, Robot.upperLegHeight, Robot.limbWidth)
            UpperRightLeg -> limb.scale(Robot.limbWidth, Robot.upperLegHeight, Robot.limbWidth)
            LowerLeftLeg -> limb.scale(Robot.limbWidth, Robot.lowerLegHeight, Robot.limbWidth)
            LowerRightLeg -> limb.scale(Robot.limbWidth, Robot.lowerLegHeight, Robot.limbWidth)
            LeftFoot -> limb.scale(Robot.limbWidth, Robot.footHeight, Robot.handAndFootDepth)
            RightFoot -> limb.scale(Robot.limbWidth, Robot.footHeight, Robot.handAndFootDepth)
        }
    }
}