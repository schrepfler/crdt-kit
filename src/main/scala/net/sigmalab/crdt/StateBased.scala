package net.sigmalab.crdt

/**
  * Created by schrepfler on 02/05/2016.
  */
trait StateBased[ThisType, ValueType] {

  def merge(other: ThisType): ThisType

  def value: ValueType
}
