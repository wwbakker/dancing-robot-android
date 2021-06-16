package nl.wwbakker.dancingrobot.robot

interface Transformation {
    fun transform(limbType: LimbType, limb: Array<nl.wwbakker.dancingrobot.Coordinate>) : Array<nl.wwbakker.dancingrobot.Coordinate>
}