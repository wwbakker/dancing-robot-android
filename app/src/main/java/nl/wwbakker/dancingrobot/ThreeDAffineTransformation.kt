package nl.wwbakker.dancingrobot

import android.graphics.Canvas

object ThreeDAffineTransformation {

    fun video(canvas: Canvas) {
        nl.wwbakker.dancingrobot.DrawHelper.drawCube(
            canvas,
            nl.wwbakker.dancingrobot.DrawHelper.cube_vertices
                .translate(2.0, 2.0, 2.0)
                .scale(40.0, 40.0, 40.0)
                .rotateY(45.0)
                .rotateX(45.0)
        )
    }

    fun assignment1(canvas: Canvas) {
        val draw_cube_vertices: Array<nl.wwbakker.dancingrobot.Coordinate> =
            nl.wwbakker.dancingrobot.DrawHelper.cube_vertices //the vertices for drawing a 3D cube
                .rotateZ(80.0)
                .rotateY(30.0)
                .translate(2.0, 2.0, 2.0)
                .scale(40.0, 40.0, 40.0)
        nl.wwbakker.dancingrobot.DrawHelper.drawCube(canvas, draw_cube_vertices)
    }

    fun assignment2(canvas: Canvas) {
        val draw_cube_vertices: Array<nl.wwbakker.dancingrobot.Coordinate> =
            nl.wwbakker.dancingrobot.DrawHelper.cube_vertices //the vertices for drawing a 3D cube
                .translate(2.0, 2.0, 2.0)
                .scale(40.0, 40.0, 40.0)
                .shear(2.0, 1.0)
        nl.wwbakker.dancingrobot.DrawHelper.drawCube(canvas, draw_cube_vertices)
    }
}