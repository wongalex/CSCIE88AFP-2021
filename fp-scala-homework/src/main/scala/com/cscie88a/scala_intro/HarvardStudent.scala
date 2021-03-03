package com.cscie88a.scala_intro

// add your code below
case class HarvardStudent(firstName: String, lastName: String, subjectName: String, percentScore: Int) {
  def fullName : String =
    firstName.toUpperCase() + " " + lastName.toUpperCase()

  def failedSubject: Boolean =
    if (percentScore < 50) true else false
}

object HarvardStudent {
  def apply (studentInfo: String): HarvardStudent = {
    var fields = studentInfo.split(",")
    HarvardStudent(fields(0), fields(1), fields(2), fields(3).toInt)
  }

  def fromCSVStrings (csvStrings: List[String]): List[HarvardStudent] = {
    csvStrings.map(HarvardStudent(_))
  }

  def avgStudentScore (students: List[HarvardStudent]): Double = {
    students.map(HarvardStudentAggregator(_)).reduce(_ + _).avg
  }

  def avgPassingScore (students: List[HarvardStudent]): Double = {
    students.filter(c => c.percentScore > 50).map(HarvardStudentAggregator(_)).reduce(_ + _).avg
  }
}

case class HarvardStudentAggregator(count: Int, total: Long) {
  def avg: Double = total / count

  def + (another: HarvardStudentAggregator): HarvardStudentAggregator =
    HarvardStudentAggregator(count + another.count, total + another.total)
}

object HarvardStudentAggregator {
  def apply(harvardStudent: HarvardStudent): HarvardStudentAggregator = {
    HarvardStudentAggregator(1, harvardStudent.percentScore)
  }

  val zero = HarvardStudentAggregator(0,0)
}