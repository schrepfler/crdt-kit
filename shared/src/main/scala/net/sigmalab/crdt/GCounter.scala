package net.sigmalab.crdt


/**
  * Created by schrepfler on 01/05/2016.
  */
trait GCounter[Id, N] extends Counter[N] with StateBased[N, Map[Id, N]] {

}
