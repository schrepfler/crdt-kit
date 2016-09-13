package net.sigmalab.crdt

/**
 * Created by schrepfler on 02/05/2016.
 */
trait StateBased[N, Z] {

  def merge(other: StateBased[N, Z]): StateBased[N, Z]

  def value: N

  def payload(): Z

}
