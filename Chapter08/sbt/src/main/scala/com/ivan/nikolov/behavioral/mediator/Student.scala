package com.ivan.nikolov.behavioral.mediator

import scala.collection.mutable

trait Notifiable {
  def notify(message: String)
}

case class Student(name: String, age: Int) extends Notifiable {
  override def notify(message: String): Unit = {
    System.out.println(s"Student $name was notified with message: '$message'.")
  }
}

case class Group(name: String)

trait Mediator {
  def addStudentToGroup(student: Student, group: Group)

  def isStudentInGroup(student: Student, group: Group): Boolean

  def removeStudentFromGroup(student: Student, group: Group)

  def getStudentsInGroup(group: Group): List[Student]

  def getGroupsForStudent(student: Student): List[Group]

  def notifyStudentsInGroup(group: Group, message: String)
}

class School extends Mediator {
  val studentsToGroups: mutable.Map[Student, mutable.Set[Group]] = mutable.Map()
  val groupsToStudents: mutable.Map[Group, mutable.Set[Student]] = mutable.Map()

  override def addStudentToGroup(student: Student, group: Group): Unit = {
    studentsToGroups.getOrElseUpdate(student, mutable.Set()) += group
    groupsToStudents.getOrElseUpdate(group, mutable.Set()) += student
  }

  override def isStudentInGroup(student: Student, group: Group): Boolean =
    groupsToStudents.getOrElse(group, mutable.Set()).contains(student) && studentsToGroups.getOrElse(student, mutable.Set()).contains(group)

  override def getStudentsInGroup(group: Group): List[Student] =
    groupsToStudents.getOrElse(group, mutable.Set()).toList

  override def getGroupsForStudent(student: Student): List[Group] =
    studentsToGroups.getOrElse(student, mutable.Set()).toList

  override def notifyStudentsInGroup(group: Group, message: String): Unit = {
    groupsToStudents.getOrElse(group, mutable.Set()).foreach(_.notify(message))
  }

  override def removeStudentFromGroup(student: Student, group: Group): Unit = {
    studentsToGroups.getOrElse(student, mutable.Set()) -= group
    groupsToStudents.getOrElse(group, mutable.Set()) -= student
  }
}

object SchoolExample {
  def main(args: Array[String]): Unit = {
    val school = new School
    // create students
    val student1 = Student("Ivan", 26)
    val student2 = Student("Maria", 26)
    val student3 = Student("John", 25)
    // create groups
    val group1 = Group("Scala design patterns")
    val group2 = Group("Databases")
    val group3 = Group("Cloud computing")

    school.addStudentToGroup(student1, group1)
    school.addStudentToGroup(student1, group2)
    school.addStudentToGroup(student1, group3)

    school.addStudentToGroup(student2, group1)
    school.addStudentToGroup(student2, group3)

    school.addStudentToGroup(student3, group1)
    school.addStudentToGroup(student3, group2)

    // notify
    school.notifyStudentsInGroup(group1, "Design patterns in Scala are amazing!")

    // see groups
    System.out.println(s"$student3 is in groups: ${school.getGroupsForStudent(student3)}")
    // remove from group
    school.removeStudentFromGroup(student3, group2)
    System.out.println(s"$student3 is in groups: ${school.getGroupsForStudent(student3)}")

    // see students in group
    System.out.println(s"Students in $group1 are ${school.getStudentsInGroup(group1)}")
  }
}