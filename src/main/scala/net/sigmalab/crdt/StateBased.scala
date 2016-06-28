package net.sigmalab.crdt

import algebra.{CommutativeMonoid, Order}

/**
  * Created by schrepfler on 02/05/2016.
  */
trait StateBased[@specialized(Int, Long, Float, Double) N, Z] {

  def merge(other: StateBased[N, Z]): StateBased[N, Z]

  def value: N

  def payload(): Z

}
