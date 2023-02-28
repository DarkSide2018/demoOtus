import inheritance.Shape

fun main() {
    val triangle = Triangle(4, 5, 6)
    val quadrAngle = QuadrAngle(4, 5)

    listOf<Figure>(triangle, quadrAngle).forEach {
        it.print()
    }

    val transPort = Transport(9,8)
    // Деструктуризация
    val (a,b) = transPort
    println(a)
    println(b)
}

data class Transport(
    val length:Int,
    val weight:Int
)
abstract class Figure(
    protected val height: Int,
    protected val width: Int
){
    init {
        println("default constructor was invoked")
    }
    var mutableProperty:Int=0
        set(value) {field=value}
        get() {return field}


    abstract fun print()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Figure

        if (height != other.height) return false
        if (width != other.width) return false

        return true
    }

    override fun hashCode(): Int {
        var result = height
        result = 31 * result + width
        return result
    }
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
    private val diagonal: Int,
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

//class Other: Shape {
//    override fun action() {
//        TODO("Not yet implemented")
//    }
//
//}

 data class DataMain(
    val number:Int,
    val triangle: Triangle
){
     init {
         triangle.mutableProperty = number
     }
 }


