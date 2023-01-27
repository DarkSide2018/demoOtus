package main

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule


class MutableUser(val map: Map<String, Any?>) {
    val firstName: String by map
    val lastName: String by map
    val age: Int     by map
    var fieldOne:Int = 0
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