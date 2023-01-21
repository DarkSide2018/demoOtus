fun main() {
    val triangle = Triangle(4, 5, 6)
    val quadrAngle = QuadrAngle(4, 5)
    listOf<Figure>(triangle, quadrAngle).forEach {
        it.print()
    }

}
abstract class Figure(
    val height: Int,
    val width: Int
){
    abstract fun print()
}
class QuadrAngle(
     height: Int,
     width: Int,
) : Figure(
    height,
    width
) {
    override fun print() {
        println("width=$width height=$height")
    }
}

class Triangle(
    val diagonal: Int,
     height: Int,
     width: Int,
) : Figure(
    height,
    width
) {
    override fun print() {
        println("diagonal=$diagonal width=$width height=$height")
    }

}