package nl.wwbakker.dancingrobot.robot.transformations

import android.content.res.Resources
import nl.wwbakker.dancingrobot.robot.LimbType
import nl.wwbakker.dancingrobot.robot.Robot.totalHeight
import nl.wwbakker.dancingrobot.robot.Transformation
import nl.wwbakker.dancingrobot.translate

class CenterOnScreenTransformation : Transformation {
    private val screenCenterX = Resources.getSystem().displayMetrics.widthPixels / 2.0
    private val screenCenterY = Resources.getSystem().displayMetrics.heightPixels / 2.0

    override fun transform(limbType: LimbType, limb: Array<nl.wwbakker.dancingrobot.Coordinate>): Array<nl.wwbakker.dancingrobot.Coordinate> {
        return limb.translate(screenCenterX, screenCenterY - (totalHeight / 4.0), 0.0)
    }
}