package net.sigmalab.crdt

/**
  * Created by schrepfler on 02/05/2016.
  */
trait Counter[Elt] {
  def increment(amt: Elt): Any
}
