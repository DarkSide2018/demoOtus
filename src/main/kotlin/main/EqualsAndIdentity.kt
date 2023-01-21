package main

import QuadrAngle

fun main(){
    var quadrAngle0 = QuadrAngle(4, 5)
    val quadrAngle = QuadrAngle(4, 5)
    val quadrAngle2 = QuadrAngle(4, 5)
    quadrAngle0 = quadrAngle2
    println(quadrAngle==quadrAngle2)
    println(quadrAngle0===quadrAngle2)
}