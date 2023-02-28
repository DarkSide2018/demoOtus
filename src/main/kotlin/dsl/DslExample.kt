package dsl

fun main() {
    val user = user {
        lastName = "Invanov"
        firstName = "Petr"
    }
    val user2 = user {
        firstName = "Vasya"
        lastName = "Sidorov"
        age = 21
        assertions {
            ageAssertion {
                ageMax = 20
                ageMin = 70
            }
            nameAssertion {
                maxNameLength = 4
                minNameLength = 2
            }
        }
    }

    println(user)

    println(user2)


}

fun user(block: User.Builder.() -> Unit): User {
    return User.Builder().apply(block).build()
}


data class User(
    val firstName: String,
    val lastName: String,
    val gender: String,
    val age: Int,
    val assertions: List<UserAssertion>,
) {


    class Builder(
        var firstName: String = "default name",
        var lastName: String = "default last name",
        var gender: String = "male",
        var age: Int = 20,
        var assertions: MutableList<UserAssertion> = mutableListOf(),
    ) {
        fun build(): User {
            assertions.forEach { it.execute(this) }
            return User(firstName, lastName, gender, age, assertions)
        }

        fun assertions(block: UserAssertionContainer.() -> Unit) {
            val userAssertionContainer = UserAssertionContainer().apply(block)
            assertions.addAll(userAssertionContainer.assertionsList)
        }
    }

}

abstract class UserAssertion(
    val name: String,
) {
    abstract fun execute(builder: User.Builder)
    fun print() {
        println("name -> $name")
    }
}

class UserAssertionContainer(

) {
    val assertionsList: MutableList<UserAssertion> = mutableListOf()
    inline fun nameAssertion(block: NameAssertion.Builder.() -> Unit) =
        NameAssertion.Builder().apply(block).build().also {
            assertionsList.add(it)
        }

    inline fun ageAssertion(block: AgeAssertion.Builder.() -> Unit) =
        AgeAssertion.Builder().apply(block).build().also {
            assertionsList.add(it)
        }
}

data class AgeAssertion(
    val ageMax: Int,
    val ageMin: Int,
    val assertionName: String,
) : UserAssertion(
    assertionName
) {
    override fun execute(builder: User.Builder) {
        if (builder.age > ageMax) {
            println(" too old")
        }
    }

    class Builder(
        var ageMax: Int = 30,
        var ageMin: Int = 10,
        var assertionName: String = "Age assertion",
    ) {


        fun build(): AgeAssertion {

            return AgeAssertion(ageMax, ageMin, assertionName)
        }
    }
}

data class NameAssertion(
    val maxNameLength: Int,
    val minNameLength: Int,
    val assertionName: String,
) : UserAssertion(
    assertionName
) {
    override fun execute(builder: User.Builder) {
        if (builder.lastName.length > maxNameLength) {
            println("lastName too long")
        }
    }

    class Builder(
        var maxNameLength: Int = 20,
        var minNameLength: Int = 3,
        var assertionName: String = "Name Assertion",
    ) {
        fun build(): NameAssertion {
            return NameAssertion(maxNameLength, minNameLength, assertionName)
        }
    }
}

@DslMarker
annotation class DslExample