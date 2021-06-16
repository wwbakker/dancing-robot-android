package nl.wwbakker.dancingrobot

import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.tan

object Matrix {

    //*********************************
    //matrix and transformation functions
    fun identityMatrix(): DoubleArray { //return an 4x4 identity matrix
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
        vertex: nl.wwbakker.dancingrobot.Coordinate,
        matrix: DoubleArray
    ): nl.wwbakker.dancingrobot.Coordinate { //affine transformation with homogeneous coordinates
        //i.e. a vector (vertex) multiply with the transformation matrix
        // vertex - vector in 3D
        // matrix - transformation matrix
        val result = nl.wwbakker.dancingrobot.Coordinate()
        result.x = matrix[0] * vertex.x + matrix[1] * vertex.y + matrix[2] * vertex.z + matrix[3]
        result.y = matrix[4] * vertex.x + matrix[5] * vertex.y + matrix[6] * vertex.z + matrix[7]
        result.z = matrix[8] * vertex.x + matrix[9] * vertex.y + matrix[10] * vertex.z + matrix[11]
        result.w =
            matrix[12] * vertex.x + matrix[13] * vertex.y + matrix[14] * vertex.z + matrix[15]
        return result
    }

    fun transformation(
        vertices: Array<nl.wwbakker.dancingrobot.Coordinate>,
        matrix: DoubleArray
    ): Array<nl.wwbakker.dancingrobot.Coordinate> {   //Affine transform a 3D object with vertices
        // vertices - vertices of the 3D object.
        // matrix - transformation matrix

        return vertices.map {
            transformation(it, matrix).normalise()
        }.toTypedArray()
    }
}

//***********************************************************
//Affine transformation
fun Array<nl.wwbakker.dancingrobot.Coordinate>.translate(
    tx: Double,
    ty: Double,
    tz: Double
): Array<nl.wwbakker.dancingrobot.Coordinate> {
    val matrix = Matrix.identityMatrix()
    matrix[3] = tx
    matrix[7] = ty
    matrix[11] = tz
    return Matrix.transformation(this, matrix)
}

fun Array<nl.wwbakker.dancingrobot.Coordinate>.scale(
    sx: Double,
    sy: Double,
    sz: Double
): Array<nl.wwbakker.dancingrobot.Coordinate> {
    val matrix = Matrix.identityMatrix()
    matrix[0] = sx
    matrix[5] = sy
    matrix[10] = sz
    return Matrix.transformation(this, matrix)
}

fun Array<nl.wwbakker.dancingrobot.Coordinate>.rotateX(
    degrees: Double
): Array<nl.wwbakker.dancingrobot.Coordinate> {
    val matrix = Matrix.identityMatrix()
    val θ = Math.toRadians(degrees)
    matrix[5] = cos(θ)
    matrix[6] = -sin(θ)
    matrix[9] = sin(θ)
    matrix[10] = cos(θ)
    return Matrix.transformation(this, matrix)
}

fun Array<nl.wwbakker.dancingrobot.Coordinate>.rotateY(
    degrees: Double
): Array<nl.wwbakker.dancingrobot.Coordinate> {
    val matrix = Matrix.identityMatrix()
    val θ = Math.toRadians(degrees)
    matrix[0] = cos(θ)
    matrix[2] = sin(θ)
    matrix[8] = -sin(θ)
    matrix[10] = cos(θ)
    return Matrix.transformation(this, matrix)
}

fun Array<nl.wwbakker.dancingrobot.Coordinate>.rotateZ(
    degrees: Double
): Array<nl.wwbakker.dancingrobot.Coordinate> {
    val matrix = Matrix.identityMatrix()
    val θ = Math.toRadians(degrees)
    matrix[0] = cos(θ)
    matrix[1] = -sin(θ)
    matrix[4] = sin(θ)
    matrix[5] = cos(θ)
    return Matrix.transformation(this, matrix)
}

fun Array<nl.wwbakker.dancingrobot.Coordinate>.shear(
    hx: Double,
    hy: Double,
): Array<nl.wwbakker.dancingrobot.Coordinate> {
    val matrix = Matrix.identityMatrix()
    matrix[2] = hx
    matrix[6] = hy
    return Matrix.transformation(this, matrix)
}

fun Array<nl.wwbakker.dancingrobot.Coordinate>.quarternionRotation(w : Double, x: Double, y: Double, z: Double) : Array<nl.wwbakker.dancingrobot.Coordinate> {
    val matrix = Matrix.identityMatrix()
    matrix[0] = w.pow(2) + x.pow(2) - y.pow(2) - z.pow(2)
    matrix[1] = (2 * x * y) - (2 * w * z)
    matrix[2] = (2 * x * z) + (2 * w * y)
    matrix[4] = (2 * x * y) + (2 * w * z)
    matrix[5] = w.pow(2) + y.pow(2) - x.pow(2) - z.pow(2)
    matrix[6] = (2 * y * z) - (2 * w * x)
    matrix[8] = (2 * x * z) - (2 * w * y)
    matrix[9] = (2 * y * z) + (2 * w * x)
    matrix[10] = w.pow(2) + z.pow(2) - x.pow(2) - y.pow(2)
    return Matrix.transformation(this, matrix)
}

fun Array<nl.wwbakker.dancingrobot.Coordinate>.withOffset(tx: Double, ty: Double, tz: Double, f : (Array<nl.wwbakker.dancingrobot.Coordinate>) -> Array<nl.wwbakker.dancingrobot.Coordinate>) : Array<nl.wwbakker.dancingrobot.Coordinate> {
    return f(this.translate(tx, ty, tz)).translate(-tx, -ty, -tz)
}
fun Array<nl.wwbakker.dancingrobot.Coordinate>.conditional(condition : Boolean, f : (Array<nl.wwbakker.dancingrobot.Coordinate>) -> Array<nl.wwbakker.dancingrobot.Coordinate>) : Array<nl.wwbakker.dancingrobot.Coordinate> {
    return if (condition) f(this) else this
}

fun Array<nl.wwbakker.dancingrobot.Coordinate>.quaternionRotationFromEulerAngles(angleInDegrees : Double, x: Double, y: Double, z: Double) : Array<nl.wwbakker.dancingrobot.Coordinate> {
    val angleInRadians = Math.toRadians(angleInDegrees)
    val sra2 = sin(angleInRadians / 2.0)
    return quarternionRotation(
        cos(angleInRadians / 2.0),
        x * sra2,
        y * sra2,
        z * sra2)
}

fun Array<nl.wwbakker.dancingrobot.Coordinate>.perspectiveProjectionDirect(near : Double, far : Double, fovInDegrees : Double, left: Double, right: Double, top: Double, bottom: Double) : Array<nl.wwbakker.dancingrobot.Coordinate> {
    val aspectRatio = (right - left) / (top - bottom)
    val fovInRadians = Math.toRadians(fovInDegrees)
    val matrix = Matrix.identityMatrix()
    matrix[0] = (1 / (aspectRatio * tan(fovInRadians / 2.0)))
    matrix[5] = (1 / tan(fovInRadians / 2.0))
    matrix[10] = -((far + near) / (far - near))
    matrix[11] = -(2 * far * near) / (far - near)
    matrix[14] = -1.0
    matrix[15] = 0.0
    return Matrix.transformation(this, matrix)
}


fun Array<nl.wwbakker.dancingrobot.Coordinate>.perspectiveProjection(near : Double, far : Double, left: Double, right: Double, top: Double, bottom: Double) : Array<nl.wwbakker.dancingrobot.Coordinate> {
    return perspectiveProjectionScale(near, left, right, top, bottom)
        .perspectiveProjectionPerspective(near)
        .perspectiveProjectionDepthScale(near, far)
}

fun Array<nl.wwbakker.dancingrobot.Coordinate>.perspectiveProjectionScale(near : Double, left: Double, right: Double, top: Double, bottom: Double) : Array<nl.wwbakker.dancingrobot.Coordinate>  {
    val sx = 2 / (right - left)
    val sy = 2 / (top - bottom)
    return scale(sx, sy, 1.0)
}

fun Array<nl.wwbakker.dancingrobot.Coordinate>.perspectiveProjectionPerspective(near : Double) : Array<nl.wwbakker.dancingrobot.Coordinate> {
    val matrix = Matrix.identityMatrix()
    matrix[0] = near
    matrix[5] = near
    matrix[14] = -1.0
    matrix[15] = 0.0
    return Matrix.transformation(this, matrix)
}

fun Array<nl.wwbakker.dancingrobot.Coordinate>.perspectiveProjectionDepthScale(near : Double, far : Double) : Array<nl.wwbakker.dancingrobot.Coordinate> {
    val c1 = - ((2 * far * near) / (far - near))
    val c2 = (far + near) / (far - near)
    val matrix = Matrix.identityMatrix()
    matrix[10] = -c2
    matrix[11] = c1
    matrix[14] = -1.0
    matrix[15] = 0.0
    return Matrix.transformation(this, matrix)
}

