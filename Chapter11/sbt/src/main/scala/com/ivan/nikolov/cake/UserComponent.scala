package com.ivan.nikolov.cake

import scalaz.Reader

trait UserComponent {
  this: DaoComponent =>

  def getAverageAgeOfUsersInClass(className: String): Reader[UserService, Double] =
    Reader((userService: UserService) => userService.getAverageAgeOfUsersInClass(className))
  
  val userService: UserService
  class UserService {
    def getAverageAgeOfUsersInClass(className: String): Double = {
      val (ageSum, peopleCount) = dao.getPeopleInClass(className).foldLeft((0, 0)) {
        case ((sum, count), person) =>
          (sum + person.age, count + 1)
      }
      if (peopleCount != 0) {
        ageSum.toDouble / peopleCount.toDouble
      } else {
        0.0
      }
    }
  }
}
