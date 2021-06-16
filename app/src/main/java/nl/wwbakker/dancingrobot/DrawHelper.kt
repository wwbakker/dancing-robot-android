package nl.wwbakker.dancingrobot

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path

object DrawHelper {

    private val redPaint : Paint = Paint(Paint.ANTI_ALIAS_FLAG) //paint object for drawing the lines
    private val bluePaint : Paint = Paint(Paint.ANTI_ALIAS_FLAG) //paint object for drawing the lines
    val cube_vertices : Array<nl.wwbakker.dancingrobot.Coordinate> =
        arrayOf(
            nl.wwbakker.dancingrobot.Coordinate(-1.0, -1.0, -1.0, 1.0),
            nl.wwbakker.dancingrobot.Coordinate(-1.0, -1.0, 1.0, 1.0),
            nl.wwbakker.dancingrobot.Coordinate(-1.0, 1.0, -1.0, 1.0),
            nl.wwbakker.dancingrobot.Coordinate(-1.0, 1.0, 1.0, 1.0),
            nl.wwbakker.dancingrobot.Coordinate(1.0, -1.0, -1.0, 1.0),
            nl.wwbakker.dancingrobot.Coordinate(1.0, -1.0, 1.0, 1.0),
            nl.wwbakker.dancingrobot.Coordinate(1.0, 1.0, -1.0, 1.0),
            nl.wwbakker.dancingrobot.Coordinate(1.0, 1.0, 1.0, 1.0),
        ).scale(0.5, 0.5, 0.5) // scale to 1 by 1 by 1 cube


    init {
        //create the paint object
        nl.wwbakker.dancingrobot.DrawHelper.redPaint.style = Paint.Style.STROKE //Stroke
        nl.wwbakker.dancingrobot.DrawHelper.redPaint.color = Color.RED
        nl.wwbakker.dancingrobot.DrawHelper.redPaint.strokeWidth = 2f
        nl.wwbakker.dancingrobot.DrawHelper.bluePaint.style = Paint.Style.STROKE //Stroke
        nl.wwbakker.dancingrobot.DrawHelper.bluePaint.color = Color.BLUE
        nl.wwbakker.dancingrobot.DrawHelper.bluePaint.strokeWidth = 2f
    }



    fun drawHeightLine(canvas: Canvas, y : Double, screenWidth: Double) {
        canvas.drawLine(
            0f,
            y.toFloat(),
            screenWidth.toFloat(),
            y.toFloat(),
            nl.wwbakker.dancingrobot.DrawHelper.bluePaint
        )
    }

    fun drawCube(canvas: Canvas, vertices: Array<nl.wwbakker.dancingrobot.Coordinate>, color : Int)  {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.STROKE //Stroke
        paint.color = color
        paint.strokeWidth = 2f
        nl.wwbakker.dancingrobot.DrawHelper.drawCube(canvas, vertices, paint)
    }

    fun drawCube(canvas: Canvas, vertices: Array<nl.wwbakker.dancingrobot.Coordinate>, paint : Paint = nl.wwbakker.dancingrobot.DrawHelper.redPaint) { //draw a cube on the screen
        nl.wwbakker.dancingrobot.DrawHelper.drawLinePairs(canvas, vertices, 0, 1, paint)
        nl.wwbakker.dancingrobot.DrawHelper.drawLinePairs(canvas, vertices, 1, 3, paint)
        nl.wwbakker.dancingrobot.DrawHelper.drawLinePairs(canvas, vertices, 3, 2, paint)
        nl.wwbakker.dancingrobot.DrawHelper.drawLinePairs(canvas, vertices, 2, 0, paint)
        nl.wwbakker.dancingrobot.DrawHelper.drawLinePairs(canvas, vertices, 4, 5, paint)
        nl.wwbakker.dancingrobot.DrawHelper.drawLinePairs(canvas, vertices, 5, 7, paint)
        nl.wwbakker.dancingrobot.DrawHelper.drawLinePairs(canvas, vertices, 7, 6, paint)
        nl.wwbakker.dancingrobot.DrawHelper.drawLinePairs(canvas, vertices, 6, 4, paint)
        nl.wwbakker.dancingrobot.DrawHelper.drawLinePairs(canvas, vertices, 0, 4, paint)
        nl.wwbakker.dancingrobot.DrawHelper.drawLinePairs(canvas, vertices, 1, 5, paint)
        nl.wwbakker.dancingrobot.DrawHelper.drawLinePairs(canvas, vertices, 2, 6, paint)
        nl.wwbakker.dancingrobot.DrawHelper.drawLinePairs(canvas, vertices, 3, 7, paint)
    }

    private fun drawLinePairs(
        canvas: Canvas,
        vertices: Array<nl.wwbakker.dancingrobot.Coordinate>,
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

    fun drawAndFillCube(canvas: Canvas, vertices: Array<nl.wwbakker.dancingrobot.Coordinate>, color: Int) { //draw a cube on the screen
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.FILL_AND_STROKE //Stroke
        paint.color = color
        paint.strokeWidth = 2f

        nl.wwbakker.dancingrobot.DrawHelper.drawFace(
            canvas,
            vertices,
            paint,
            0,
            1,
            3,
            2
        )
        nl.wwbakker.dancingrobot.DrawHelper.drawFace(
            canvas,
            vertices,
            paint,
            1,
            5,
            7,
            3
        )
        nl.wwbakker.dancingrobot.DrawHelper.drawFace(
            canvas,
            vertices,
            paint,
            4,
            5,
            6,
            7
        )
        nl.wwbakker.dancingrobot.DrawHelper.drawFace(
            canvas,
            vertices,
            paint,
            0,
            4,
            6,
            2
        )
        nl.wwbakker.dancingrobot.DrawHelper.drawFace(
            canvas,
            vertices,
            paint,
            2,
            3,
            7,
            6
        )
        nl.wwbakker.dancingrobot.DrawHelper.drawFace(
            canvas,
            vertices,
            paint,
            0,
            4,
            5,
            1
        )
    }

    fun drawFace(canvas: Canvas,
                 vertices: Array<nl.wwbakker.dancingrobot.Coordinate>,
                 paint: Paint,
                 v1: Int,
                 v2: Int,
                 v3: Int,
                 v4: Int,
    ) {
        val p = Path()
        p.moveTo(vertices[v1].x.toFloat(), vertices[v1].y.toFloat())
        p.lineTo(vertices[v2].x.toFloat(), vertices[v2].y.toFloat())
        p.lineTo(vertices[v3].x.toFloat(), vertices[v3].y.toFloat())
        p.lineTo(vertices[v4].x.toFloat(), vertices[v4].y.toFloat())
        canvas.drawPath(p, paint)
    }

    fun drawText(canvas: Canvas, text: String) {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        nl.wwbakker.dancingrobot.DrawHelper.redPaint.style = Paint.Style.FILL //Stroke
        nl.wwbakker.dancingrobot.DrawHelper.redPaint.color = Color.RED
        nl.wwbakker.dancingrobot.DrawHelper.redPaint.strokeWidth = 1f
        nl.wwbakker.dancingrobot.DrawHelper.redPaint.textSize = 50f

        canvas.drawText(text, 50f, 50f, nl.wwbakker.dancingrobot.DrawHelper.redPaint)
    }
}