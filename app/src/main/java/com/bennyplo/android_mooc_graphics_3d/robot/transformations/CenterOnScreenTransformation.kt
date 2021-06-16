package com.bennyplo.android_mooc_graphics_3d.robot.transformations

import android.content.res.Resources
import com.bennyplo.android_mooc_graphics_3d.Coordinate
import com.bennyplo.android_mooc_graphics_3d.robot.LimbType
import com.bennyplo.android_mooc_graphics_3d.robot.Robot.totalHeight
import com.bennyplo.android_mooc_graphics_3d.robot.Transformation
import com.bennyplo.android_mooc_graphics_3d.translate

class CenterOnScreenTransformation : Transformation {
    private val screenCenterX = Resources.getSystem().displayMetrics.widthPixels / 2.0
    private val screenCenterY = Resources.getSystem().displayMetrics.heightPixels / 2.0

    override fun transform(limbType: LimbType, limb: Array<Coordinate>): Array<Coordinate> {
        return limb.translate(screenCenterX, screenCenterY - (totalHeight / 4.0), 0.0)
    }
}