package com.cscie88a.scala_intro

import com.cscie88a.testutils.StandardTest

class HarvardStudentTest extends StandardTest {

  "an empty list in HarvardStudent" should {
    val emptyList: List[String] = List.empty

    "have size zero" in {
      emptyList.size should be(0)
    }
  }
  var student: HarvardStudent = HarvardStudent("Alex", "Wong", "Math", 40)
  "fullName in HarvardStudent" should {

    "show my name in all uppercase" in {
       student.fullName shouldBe "ALEX WONG"
    }
  }

  "failedSubject in HarvardStudent" should {
    "show that I failed my subject" in {
      student.failedSubject shouldBe true
    }
  }

  "smart constructor in HarvardStudent" should {
    var smartStudent: HarvardStudent = HarvardStudent("Aaron,Wong,Math,100")

    "match with passing in fields" in {
      smartStudent shouldBe HarvardStudent("Aaron", "Wong", "Math", 100)
    }
  }

  val harvardStudents: List[HarvardStudent] = List(HarvardStudent("Alex,Wong,Math,90"),
    HarvardStudent("Alan,Wong,Math,94"), HarvardStudent("Alicia,Wong,Math,78"), HarvardStudent("Allie,Wong,Math,30"))

  "fromCSVStrings in HarvardStudent" should {
    val studentList: List[String] = List("Alex,Wong,Math,90", "Alan,Wong,Math,94", "Alicia,Wong,Math,78","Allie,Wong,Math,30")

    "match the list of Harvard students" in {
      HarvardStudent.fromCSVStrings(studentList) shouldBe harvardStudents
    }
  }

  "avgStudentScore in HarvardStudent" should {

    "return the average score of the harvardStudents list" in  {
      HarvardStudent.avgStudentScore(harvardStudents) shouldBe 73.0
    }
  }
  val harvardOtherStudents: List[HarvardStudent] = List(HarvardStudent("Alex,Wong,Math,80"),
    HarvardStudent("Alan,Wong,Math,71"), HarvardStudent("Alicia,Wong,Math,77"), HarvardStudent("Allie,Wong,Math,30"))
  "avgPassingScore in HarvardStudent" should {

    "return the average score of the harvardOtherStudents list that passed the class" in  {
      HarvardStudent.avgPassingScore(harvardOtherStudents) shouldBe 76.0
    }
  }
}
