package net.sigmalab.crdt

/**
  * Created by schrepfler on 01/05/2016.
  */
trait PNCounter[Id, Elt] {

  def inc(id: Id, amt: Elt)

  def dec(id: Id, amt: Elt)

  def merge(c: PNCounter[Id, Elt]): PNCounter[Id, Elt]

  def total: Elt

}
