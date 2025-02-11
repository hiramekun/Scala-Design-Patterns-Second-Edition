package com.ivan.nikolov.practicecats

import cats.Monad
import com.ivan.nikolov.monads.Implementation

import scala.annotation.tailrec

object MyOption {
  implicit def optionMonad: Monad[MyOption] =
    new Monad[MyOption] {
      override def pure[A](x: A): MyOption[A] = MySome(x)

      override def flatMap[A, B](fa: MyOption[A])(f: A => MyOption[B]): MyOption[B] = fa match {
        case MySome(value) => f(value)
        case MyNone() => MyNone()
      }

      @tailrec
      override def tailRecM[A, B](a: A)(f: A => MyOption[Either[A, B]]): MyOption[B] = f(a) match {
        case MySome(Right(value)) => MySome(value)
        case MySome(Left(value)) => tailRecM(value)(f)
        case MyNone() => MyNone()
      }
    }
}

sealed class MyOption[A](implicit val optionMonad: Monad[MyOption]) {
  def flatMap[B](f: A => MyOption[B]): MyOption[B] = optionMonad.flatMap(this)(f)

  def map[B](f: A => B): MyOption[B] = optionMonad.map(this)(f)
}

case class MySome[A](value: A) extends MyOption[A]

case class MyNone[A]() extends MyOption[A]


object Main {
  def main(args: Array[String]): Unit = {
    val result = for {
      x <- MySome(1)
      y <- MySome(2)
    } yield {
      x + y
    }
    println(result)
  }
}

// Example
case class Doer_cats() {
  def getAlgorithm(isFail: Boolean): MyOption[Algorithm_cats] =
    if (isFail) {
      MyNone()
    } else {
      MySome(Algorithm_cats())
    }
}

case class Algorithm_cats() {
  def getImplementation(isFail: Boolean, left: Int, right: Int): MyOption[Implementation] =
    if (isFail) {
      MyNone()
    } else {
      MySome(Implementation(left, right))
    }
}

object MonadExample {
  def main(args: Array[String]): Unit = {
    System.out.println(s"The result is: ${compute(MySome(Doer_cats()), 10, 16)}")
  }

  def compute(doer: MyOption[Doer_cats], left: Int, right: Int): MyOption[Int] =
    for {
      d <- doer
      a <- d.getAlgorithm(false)
      i <- a.getImplementation(isFail = false, left, right)
    } yield i.compute
}