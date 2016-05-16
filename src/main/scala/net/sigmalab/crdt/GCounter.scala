package net.sigmalab.crdt

/**
  * Created by schrepfler on 01/05/2016.
  */
trait GCounter[Id, ThisType, Elt] extends Counter[Elt] with StateBased[ThisType, Elt] {

  def increment(amt: Elt): ThisType

  def value: Elt

  def merge(other: ThisType): ThisType

}
