package com.cscie88a.scala_intro

// add your code below
object MyStudent {
  val firstName : String = "Alex"
  val lastName : String = "Wong"

  def greet: String = "Hello" + " " + firstName.toUpperCase() + " " + lastName.toUpperCase()
}