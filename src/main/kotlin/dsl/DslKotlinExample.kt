package dsl


import java.math.BigDecimal


fun main() {
    val order = order {
        number = "123"
        specialist = "Ivanov"
        routes {
                route {
                    driver = "Sidorov"
                }
                route {
                    driver = "Petrov"
                }
                route {
                    driver = "Petrov45"
                }
        }
    }

    println(order)
}

data class Order(
    val number: String,
    val specialist: String,
    val routes: List<Route>
) {
    class Builder {
        var number: String = "DEFAULT"
        var specialist: String = "DEFAULT"
        var routes: MutableList<Route> = mutableListOf()
        fun build(): Order {
            return Order(number, specialist, routes)
        }
        fun routes(block: RouteContainer.() -> Unit) {
            val container = RouteContainer().apply(block)
            routes.addAll(container.content)
        }
    }
}

fun order(block: Order.Builder.() -> Unit): Order = Order.Builder().apply(block).build()



class RouteContainer{
    val content:MutableList<Route> = mutableListOf()
    fun route(block: Route.Builder.() -> Unit): Route {
        val build = Route.Builder().apply(block).build()
        content.add(build)
        return build
    }
}


data class Route(
    val driver: String,
    val vehicle: String,
    val trailer: String,
    val originCity: String,
    val destinationCity: String,
    val cargo: String,
    val weight: BigDecimal
) {
    class Builder {
        var driver: String = "DEFAULT_DRIVER"
        var vehicle: String = "DEFAULT_VEHICLE"
        var trailer: String = "DEFAULT_TRAILER"
        var originCity: String = "DEFAULT_CITY"
        var destinationCity: String = "DEFAULT_DESTINATION"
        var cargo: String = "DEFAULT_CARGO"
        var weight: BigDecimal = BigDecimal("0")
        fun build(): Route {
            return Route(
                driver,
                vehicle,
                trailer,
                originCity,
                destinationCity,
                cargo,
                weight
            )
        }
    }
}