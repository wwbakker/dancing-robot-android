package com.bennyplo.android_mooc_graphics_3d

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class MyView(context: Context?) : View(context, null) {
    private val redPaint : Paint //paint object for drawing the lines
    private val cube_vertices : Array<Coordinate> //the vertices of a 3D cube
    private var draw_cube_vertices : Array<Coordinate>//the vertices for drawing a 3D cube

    init {
        val thisview = this
        //create the paint object
        redPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        redPaint.style = Paint.Style.STROKE //Stroke
        redPaint.color = Color.RED
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
        draw_cube_vertices = translate(cube_vertices, 2.0, 2.0, 2.0)
        draw_cube_vertices = scale(draw_cube_vertices, 40.0, 40.0, 40.0)
        thisview.invalidate() //update the view
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

    private fun drawCube(canvas: Canvas) { //draw a cube on the screen
        drawLinePairs(canvas, draw_cube_vertices, 0, 1, redPaint)
        drawLinePairs(canvas, draw_cube_vertices, 1, 3, redPaint)
        drawLinePairs(canvas, draw_cube_vertices, 3, 2, redPaint)
        drawLinePairs(canvas, draw_cube_vertices, 2, 0, redPaint)
        drawLinePairs(canvas, draw_cube_vertices, 4, 5, redPaint)
        drawLinePairs(canvas, draw_cube_vertices, 5, 7, redPaint)
        drawLinePairs(canvas, draw_cube_vertices, 7, 6, redPaint)
        drawLinePairs(canvas, draw_cube_vertices, 6, 4, redPaint)
        drawLinePairs(canvas, draw_cube_vertices, 0, 4, redPaint)
        drawLinePairs(canvas, draw_cube_vertices, 1, 5, redPaint)
        drawLinePairs(canvas, draw_cube_vertices, 2, 6, redPaint)
        drawLinePairs(canvas, draw_cube_vertices, 3, 7, redPaint)
    }

    override fun onDraw(canvas: Canvas) {
        //draw objects on the screen
        super.onDraw(canvas)
        drawCube(canvas) //draw a cube onto the screen
    }

    //*********************************
    //matrix and transformation functions
    fun GetIdentityMatrix(): DoubleArray { //return an 4x4 identity matrix
        val matrix = DoubleArray(16)
        matrix[0] = 1.0
        matrix[1] = 0.0
        matrix[2] = 0.0
        matrix[3] = 0.0
        matrix[4] = 0.0
        matrix[5] = 1.0
        matrix[6] = 0.0
        matrix[7] = 0.0
        matrix[8] = 0.0
        matrix[9] = 0.0
        matrix[10] = 1.0
        matrix[11] = 0.0
        matrix[12] = 0.0
        matrix[13] = 0.0
        matrix[14] = 0.0
        matrix[15] = 1.0
        return matrix
    }

    fun transformation(
        vertex: Coordinate?,
        matrix: DoubleArray
    ): Coordinate { //affine transformation with homogeneous coordinates
        //i.e. a vector (vertex) multiply with the transformation matrix
        // vertex - vector in 3D
        // matrix - transformation matrix
        val result = Coordinate()
        result.x = matrix[0] * vertex!!.x + matrix[1] * vertex.y + matrix[2] * vertex.z + matrix[3]
        result.y = matrix[4] * vertex.x + matrix[5] * vertex.y + matrix[6] * vertex.z + matrix[7]
        result.z = matrix[8] * vertex.x + matrix[9] * vertex.y + matrix[10] * vertex.z + matrix[11]
        result.w =
            matrix[12] * vertex.x + matrix[13] * vertex.y + matrix[14] * vertex.z + matrix[15]
        return result
    }

    fun transformation(
        vertices: Array<Coordinate>,
        matrix: DoubleArray
    ): Array<Coordinate> {   //Affine transform a 3D object with vertices
        // vertices - vertices of the 3D object.
        // matrix - transformation matrix

        return vertices.map {
            transformation(it, matrix).normalise()
        }.toTypedArray()
    }

    //***********************************************************
    //Affine transformation
    fun translate(
        vertices: Array<Coordinate>,
        tx: Double,
        ty: Double,
        tz: Double
    ): Array<Coordinate> {
        val matrix = GetIdentityMatrix()
        matrix[3] = tx
        matrix[7] = ty
        matrix[11] = tz
        return transformation(vertices, matrix)
    }

    private fun scale(
        vertices: Array<Coordinate>,
        sx: Double,
        sy: Double,
        sz: Double
    ): Array<Coordinate> {
        val matrix = GetIdentityMatrix()
        matrix[0] = sx
        matrix[5] = sy
        matrix[10] = sz
        return transformation(vertices, matrix)
    }


}