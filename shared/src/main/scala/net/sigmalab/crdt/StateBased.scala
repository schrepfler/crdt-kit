package net.sigmalab.crdt

import cats.kernel.CommutativeMonoid
import cats.kernel.Order

/**
  * Created by schrepfler on 02/05/2016.
  */
trait StateBased[@specialized(Int, Long, Float, Double) N, Z] {

  def merge(other: StateBased[N, Z])(implicit order: Order[N], commutativeMonoid: CommutativeMonoid[N]): StateBased[N, Z]

  def value (implicit commutativeMonoid: CommutativeMonoid[N]): N

  def payload(): Z

}
