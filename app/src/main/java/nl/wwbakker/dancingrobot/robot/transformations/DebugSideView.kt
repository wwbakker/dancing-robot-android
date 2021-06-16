package nl.wwbakker.dancingrobot.robot.transformations

import nl.wwbakker.dancingrobot.quaternionRotationFromEulerAngles
import nl.wwbakker.dancingrobot.robot.LimbType
import nl.wwbakker.dancingrobot.robot.Transformation


class DebugSideView : Transformation {
    override fun transform(limbType: LimbType, limb: Array<nl.wwbakker.dancingrobot.Coordinate>): Array<nl.wwbakker.dancingrobot.Coordinate> {
        return limb.quaternionRotationFromEulerAngles(90.0, 0.0, 1.0, 0.0)
    }
}