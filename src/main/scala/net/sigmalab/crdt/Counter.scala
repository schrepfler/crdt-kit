package net.sigmalab.crdt

import algebra.CommutativeMonoid

/**
  * Created by schrepfler on 02/05/2016.
  */
trait Counter[@specialized(Int, Long, Float, Double) N] {

  def increment(amt: N)(commutativeMonoid: CommutativeMonoid[N]): Counter[N]

}
