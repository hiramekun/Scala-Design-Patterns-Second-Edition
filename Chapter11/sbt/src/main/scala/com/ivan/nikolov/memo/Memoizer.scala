package com.ivan.nikolov.memo

import scala.collection.mutable

trait Memoizer {

  // Note: this is not thread safe.
  def memo[X, Y](f: X => Y): X => Y = {
    val cache = mutable.Map[X, Y]()
    (x: X) => cache.getOrElseUpdate(x, f(x))
  }
}
