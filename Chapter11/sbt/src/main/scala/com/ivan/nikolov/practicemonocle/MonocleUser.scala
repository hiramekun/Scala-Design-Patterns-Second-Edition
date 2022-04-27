package com.ivan.nikolov.practicemonocle

import monocle.Monocle.toAppliedFocusOps

case class Country(name: String, code: String)

case class City(name: String, country: Country)

case class Address(number: Int, street: String, city: City)

case class Company(name: String, address: Address)

case class User(name: String, company: Company, address: Address)

object MonocleUserExample extends App {
  val uk = Country("United Kingdom", "uk")
  val london = City("London", uk)
  val buckinghamPalace = Address(1, "Buckingham Palace Road", london)
  val castleBuilders = Company("Castle Builders", buckinghamPalace)

  val switzerland = Country("Switzerland", "CH")
  val geneva = City("geneva", switzerland)
  val genevaAddress = Address(1, "Geneva Lake", geneva)

  val ivan = User("Ivan", castleBuilders, genevaAddress)
  println(ivan)
  val ivanFixed = ivan.focus(_.company.address.city.country.code).modify(_.toUpperCase)
  println(ivanFixed)
}
