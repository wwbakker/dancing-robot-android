package com.bennyplo.android_mooc_graphics_3d

import android.graphics.Canvas

object ThreeDAffineTransformation {

    fun video(canvas: Canvas) {
        DrawHelper.drawCube(
            canvas,
            DrawHelper.cube_vertices
                .translate(2.0, 2.0, 2.0)
                .scale(40.0, 40.0, 40.0)
                .rotateY(45.0)
                .rotateX(45.0)
        )
    }

    fun assignment1(canvas: Canvas) {
        val draw_cube_vertices: Array<Coordinate> =
            DrawHelper.cube_vertices //the vertices for drawing a 3D cube
                .rotateZ(80.0)
                .rotateY(30.0)
                .translate(2.0, 2.0, 2.0)
                .scale(40.0, 40.0, 40.0)
        DrawHelper.drawCube(canvas, draw_cube_vertices)
    }

    fun assignment2(canvas: Canvas) {
        val draw_cube_vertices: Array<Coordinate> =
            DrawHelper.cube_vertices //the vertices for drawing a 3D cube
                .translate(2.0, 2.0, 2.0)
                .scale(40.0, 40.0, 40.0)
                .shear(2.0, 1.0)
        DrawHelper.drawCube(canvas, draw_cube_vertices)
    }
}