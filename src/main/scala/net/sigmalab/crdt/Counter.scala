package net.sigmalab.crdt

import cats.kernel.CommutativeMonoid

/**
  * Created by schrepfler on 02/05/2016.
  */
trait Counter[@specialized(Int, Long, Float, Double) N] {

  def increment(amt: N)(implicit commutativeMonoid: CommutativeMonoid[N]): Counter[N]

}
