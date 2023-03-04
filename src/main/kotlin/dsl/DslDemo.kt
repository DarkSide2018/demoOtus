package dsl


fun main() {
    val participant = Participant.Builder(
        age = 60
    ).build()
    println(participant)
    val participant2= participant.copy(
        name = "Vasya"
    )
    println(participant2)
}

data class Participant(
    val name: String,
    val age: Int,
) {
    class Builder(
        var name: String="Default Name",
        var age: Int = 10,
    ) {
        fun build(): Participant {
            return Participant(name, age)
        }
    }
}

