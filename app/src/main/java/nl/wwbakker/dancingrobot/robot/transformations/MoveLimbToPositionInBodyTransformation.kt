package nl.wwbakker.dancingrobot.robot.transformations

import nl.wwbakker.dancingrobot.robot.LimbType
import nl.wwbakker.dancingrobot.robot.LimbType.*
import nl.wwbakker.dancingrobot.robot.Robot
import nl.wwbakker.dancingrobot.robot.Transformation
import nl.wwbakker.dancingrobot.translate

class MoveLimbToPositionInBodyTransformation : Transformation {
    override fun transform(limbType: LimbType, limb: Array<nl.wwbakker.dancingrobot.Coordinate>): Array<nl.wwbakker.dancingrobot.Coordinate> {
        return when(limbType) {
            Head -> limb.translate(0.0, Robot.headY, Robot.offsetZ)
            Neck -> limb.translate(0.0, Robot.neckY, Robot.offsetZ)
            Body -> limb.translate(0.0, Robot.bodyY, Robot.offsetZ)
            Waist -> limb.translate(0.0, Robot.waistY, Robot.offsetZ)
            UpperLeftArm -> limb.translate(Robot.leftArmX, Robot.upperArmY, Robot.offsetZ)
            UpperRightArm -> limb.translate(Robot.rightArmX, Robot.upperArmY, Robot.offsetZ)
            LowerLeftArm -> limb.translate(Robot.leftArmX, Robot.lowerArmY, Robot.offsetZ)
            LowerRightArm -> limb.translate(Robot.rightArmX, Robot.lowerArmY, Robot.offsetZ)
            LeftHand -> limb.translate(Robot.leftArmX, Robot.handY, Robot.offsetZ - 50.0)
            RightHand -> limb.translate(Robot.rightArmX, Robot.handY, Robot.offsetZ - 50.0)
            UpperLeftLeg -> limb.translate(Robot.leftLegX, Robot.upperLegY, Robot.offsetZ)
            UpperRightLeg -> limb.translate(Robot.rightLegX, Robot.upperLegY, Robot.offsetZ)
            LowerLeftLeg -> limb.translate(Robot.leftLegX, Robot.lowerLegY, Robot.offsetZ)
            LowerRightLeg -> limb.translate(Robot.rightLegX, Robot.lowerLegY, Robot.offsetZ)
            LeftFoot -> limb.translate(Robot.leftLegX, Robot.footY, Robot.offsetZ - 50.0)
            RightFoot -> limb.translate(Robot.rightLegX, Robot.footY, Robot.offsetZ - 50.0)
        }
    }
}