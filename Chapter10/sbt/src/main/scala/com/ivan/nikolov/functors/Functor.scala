package com.ivan.nikolov.functors

import scala.language.higherKinds

trait Functor[F[_]] {
  def map[T, Y](l: F[T])(f: T => Y): F[Y]
}
