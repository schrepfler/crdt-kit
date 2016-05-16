package net.sigmalab.crdt

import algebra.CommutativeMonoid

/**
  * Created by schrepfler on 02/05/2016.
  */
trait Counter[Elt] {
  def increment(amt: Elt)(implicit monoid: CommutativeMonoid[Elt]): Any
}
