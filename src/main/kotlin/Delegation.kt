import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

class MutableUser(val map: MutableMap<String, Any?>) {
    var firstName: String by map
    var lastName: String by map
    var age: Int     by map
}
val jackson = ObjectMapper()
    .registerKotlinModule()
    .readerFor(Map::class.java)

fun main(){

    val json = """
        {
        "firstName":"James",
        "lastName":"Bond",
        "age":35
        }
    """.trimIndent()
    val hashMap:HashMap<String, Any?> = jackson.readValue(json)
    val mutableUser = MutableUser(hashMap)

    println(mutableUser.age)
    println(mutableUser.firstName)
    println(mutableUser.lastName)
}