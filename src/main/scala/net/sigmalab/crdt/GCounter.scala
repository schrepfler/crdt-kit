package net.sigmalab.crdt

/**
  * Created by schrepfler on 01/05/2016.
  */
trait GCounter[Id, @specialized(Int, Long, Float, Double) N] extends Counter[N] with StateBased[N, Map[Id, N]] {

  def value: N

}
