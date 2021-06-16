package nl.wwbakker.dancingrobot.robot.transformations

import nl.wwbakker.dancingrobot.quaternionRotationFromEulerAngles
import nl.wwbakker.dancingrobot.robot.Animation
import nl.wwbakker.dancingrobot.robot.AnimationHelper
import nl.wwbakker.dancingrobot.robot.LimbType

class ViewAngleAnimation : Animation {
    var counter = 0L

    override fun update(counter : Long) {
        this.counter = counter % 6000L
    }

    private val viewAngleInDegrees : Double
        get() = AnimationHelper.interpolateBackAndForth(counter, 6000L, 90.0) + 180.0 - 45.0


    override fun transform(limbType: LimbType, limb: Array<nl.wwbakker.dancingrobot.Coordinate>): Array<nl.wwbakker.dancingrobot.Coordinate> {
        return limb.quaternionRotationFromEulerAngles(viewAngleInDegrees, 0.0, 1.0, 0.0)
    }
}

