package net.sigmalab.crdt

import algebra.Monoid

/**
  * Created by schrepfler on 02/05/2016.
  */
trait StateBased[ThisType, ValueType] {

  def merge(other: ThisType): ThisType

  def value(implicit monoid: Monoid[ValueType]): ValueType
}
