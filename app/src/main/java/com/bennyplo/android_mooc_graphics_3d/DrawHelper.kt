package com.bennyplo.android_mooc_graphics_3d

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

object DrawHelper {

    private val redPaint : Paint = Paint(Paint.ANTI_ALIAS_FLAG) //paint object for drawing the lines
    val cube_vertices : Array<Coordinate> //the vertices of a 3D cube


    init {
        //create the paint object
        redPaint.style = Paint.Style.STROKE //Stroke
        redPaint.color = Color.RED
//        redPaint.color = Color.HSVToColor(arrayOf(60f, 0f, 1f).toFloatArray())
        redPaint.strokeWidth = 2f
        //create a 3D cube
        cube_vertices = arrayOf(
            Coordinate(-1.0, -1.0, -1.0, 1.0),
            Coordinate(-1.0, -1.0, 1.0, 1.0),
            Coordinate(-1.0, 1.0, -1.0, 1.0),
            Coordinate(-1.0, 1.0, 1.0, 1.0),
            Coordinate(1.0, -1.0, -1.0, 1.0),
            Coordinate(1.0, -1.0, 1.0, 1.0),
            Coordinate(1.0, 1.0, -1.0, 1.0),
            Coordinate(1.0, 1.0, 1.0, 1.0),
        )

    }

    private fun drawLinePairs(
        canvas: Canvas,
        vertices: Array<Coordinate>,
        start: Int,
        end: Int,
        paint: Paint
    ) { //draw a line connecting 2 points
        //canvas - canvas of the view
        //points - array of points
        //start - index of the starting point
        //end - index of the ending point
        //paint - the paint of the line
        canvas.drawLine(
            vertices[start].x.toFloat(),
            vertices[start].y.toFloat(),
            vertices[end].x.toFloat(),
            vertices[end].y.toFloat(),
            paint
        )
    }

    fun drawCube(canvas: Canvas, vertices: Array<Coordinate>) { //draw a cube on the screen
        drawLinePairs(canvas, vertices, 0, 1, redPaint)
        drawLinePairs(canvas, vertices, 1, 3, redPaint)
        drawLinePairs(canvas, vertices, 3, 2, redPaint)
        drawLinePairs(canvas, vertices, 2, 0, redPaint)
        drawLinePairs(canvas, vertices, 4, 5, redPaint)
        drawLinePairs(canvas, vertices, 5, 7, redPaint)
        drawLinePairs(canvas, vertices, 7, 6, redPaint)
        drawLinePairs(canvas, vertices, 6, 4, redPaint)
        drawLinePairs(canvas, vertices, 0, 4, redPaint)
        drawLinePairs(canvas, vertices, 1, 5, redPaint)
        drawLinePairs(canvas, vertices, 2, 6, redPaint)
        drawLinePairs(canvas, vertices, 3, 7, redPaint)
    }
}