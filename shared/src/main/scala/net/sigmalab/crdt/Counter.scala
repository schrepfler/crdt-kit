package net.sigmalab.crdt

/**
  * Created by schrepfler on 02/05/2016.
  */
trait Counter[N] {

  def increment(amt: N): Counter[N]

}