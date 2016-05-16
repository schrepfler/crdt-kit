package net.sigmalab.crdt

import algebra.Monoid

/**
  * Created by schrepfler on 02/05/2016.
  */
trait Counter[Elt] {
  def increment(amt: Elt)(implicit monoid: Monoid[Elt]): Any
}
