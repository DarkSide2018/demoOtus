package main

import DataMain
import QuadrAngle
import Triangle

fun main(){
    var quadrAngle0 = QuadrAngle(4, 5)
    val quadrAngle = QuadrAngle(4, 5)
    val quadrAngle2 = QuadrAngle(4, 5)
    quadrAngle0 = quadrAngle2
    println(quadrAngle==quadrAngle2)
    println(quadrAngle0===quadrAngle2)

    val dataMain1=DataMain(5,Triangle(7,8,9))
    val dataMain2=DataMain(5,Triangle(7,8,9))
    println(dataMain1==dataMain2)
}