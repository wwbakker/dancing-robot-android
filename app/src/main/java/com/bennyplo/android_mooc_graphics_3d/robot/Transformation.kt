package com.bennyplo.android_mooc_graphics_3d.robot

import com.bennyplo.android_mooc_graphics_3d.Coordinate

interface Transformation {
    fun transform(limbType: LimbType, limb: Array<Coordinate>) : Array<Coordinate>
}