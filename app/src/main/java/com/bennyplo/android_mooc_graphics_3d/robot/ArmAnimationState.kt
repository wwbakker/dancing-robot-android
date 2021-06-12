package com.bennyplo.android_mooc_graphics_3d.robot

class ArmAnimationState(var armAngleInDegrees : Double = 0.0,
                        var leftArm: Boolean = true,
                        private var increaseArmAngle : Boolean = true) {
    fun update() {
        // ArmAngle
        when {
            armAngleInDegrees > 60.0  -> increaseArmAngle = false
            armAngleInDegrees < 0 -> {
                increaseArmAngle = true
                leftArm = !leftArm
            }
        }
        armAngleInDegrees = if (increaseArmAngle) armAngleInDegrees + 0.45 else armAngleInDegrees - 0.45
    }
}