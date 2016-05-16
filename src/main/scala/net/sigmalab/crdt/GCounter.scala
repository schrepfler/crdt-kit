package net.sigmalab.crdt

import algebra.CommutativeMonoid

/**
  * Created by schrepfler on 01/05/2016.
  */
trait GCounter[Id, ThisType, Elt] extends Counter[Elt] with StateBased[ThisType, Elt] {

  def increment(amt: Elt)(implicit commutativeMonoid: CommutativeMonoid[Elt]): ThisType

  def value(implicit commutativeMonoid: CommutativeMonoid[Elt]): Elt

  def merge(other: ThisType): ThisType

}
