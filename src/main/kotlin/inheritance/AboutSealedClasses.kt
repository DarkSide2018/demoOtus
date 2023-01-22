package inheritance

fun main() {
    val shape1=Circle("circle")
    val shape2=Square("circle")
    typeChecker(shape1)
    typeChecker(shape2)
}


//class FigureTwo:Figure(
//    width = 5,
//    height = 6
//) {
//    override fun print() {
//        TODO("Not yet implemented")
//    }
//}
sealed class Shape(
    val property:String
) {
    abstract fun action()
}
class Square(val name: String) : Shape(
    property = name
){
    fun print(){

    }

    override fun action() {
        TODO("Not yet implemented")
    }
}
class Circle(val description:String):Shape(
    property = description
){
    fun print(){

    }

    override fun action() {
        TODO("Not yet implemented")
    }
}
fun typeChecker(value: Shape) {
    when (value) {
        is Circle -> println("name")
        is Square -> println("TOP")
    }
}






