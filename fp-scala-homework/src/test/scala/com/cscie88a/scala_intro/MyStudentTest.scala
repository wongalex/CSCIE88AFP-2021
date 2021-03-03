package com.cscie88a.scala_intro

import com.cscie88a.testutils.StandardTest

class MyStudentTest extends StandardTest {

  "an empty list in MyStudent" should {
    val emptyList: List[String] = List.empty

    "have size zero" in {
      emptyList.size should be (0)
    }
  }

  "greet in MyStudent" should {
    "greet me in all uppercase" in {
      MyStudent.greet shouldBe "Hello ALEX WONG"
    }
  }


}
