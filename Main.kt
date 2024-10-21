import kotlin.math.sqrt

data class Point(val x: Double, val y: Double)

class Triangle(val p1: Point, val p2: Point, val p3: Point) {
    fun circumcenter(): Point {
        val dA = p1.x * p1.x + p1.y * p1.y
        val dB = p2.x * p2.x + p2.y * p2.y
        val dC = p3.x * p3.x + p3.y * p3.y

        val aux1 = (dA * (p3.y - p2.y) + dB * (p1.y - p3.y) + dC * (p2.y - p1.y))
        val aux2 = -(dA * (p3.x - p2.x) + dB * (p1.x - p3.x) + dC * (p2.x - p1.x))
        val div = (2 * (p1.x * (p3.y - p2.y) + p2.x * (p1.y - p3.y) + p3.x * (p2.y - p1.y)))

        val centerX = aux1 / div
        val centerY = aux2 / div

        return Point(centerX, centerY)
    }

    fun circumradius(): Double {
        val a = p1.distanceTo(p2)
        val b = p2.distanceTo(p3)
        val c = p3.distanceTo(p1)
        val semiPerimeter = (a + b + c) / 2
        val area = sqrt(semiPerimeter * (semiPerimeter - a) * (semiPerimeter - b) * (semiPerimeter - c))

        return (a * b * c) / (4 * area)
    }
}

fun Point.distanceTo(other: Point): Double {
    return sqrt((other.x - this.x) * (other.x - this.x) + (other.y - this.y) * (other.y - this.y))
}

class Circle(val center: Point, val radius: Double) {
    override fun toString(): String {
        return "Окружность с центром в точке (${center.x}, ${center.y}) и радиусом $radius"
    }
}

fun safeInput(prompt: String): Double {
    while (true) {
        try {
            print(prompt)
            return readLine()?.toDouble() ?: throw NumberFormatException()
        } catch (e: NumberFormatException) {
            println("Ошибка: Введите корректное число.")
        }
    }
}

fun main() {
    val x1 = safeInput("x1: ")
    val y1 = safeInput("y1: ")
    val x2 = safeInput("x2: ")
    val y2 = safeInput("y2: ")
    val x3 = safeInput("x3: ")
    val y3 = safeInput("y3: ")

    val triangle = Triangle(Point(x1, y1), Point(x2, y2), Point(x3, y3))

    val center = triangle.circumcenter()
    val radius = triangle.circumradius()

    val circle = Circle(center, radius)
    println(circle)
}
