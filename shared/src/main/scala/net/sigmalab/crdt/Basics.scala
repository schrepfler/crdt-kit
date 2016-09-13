package net.sigmalab.crdt

import scala.language.higherKinds

/**
 * Created by schrepfler on 02/05/2016.
 */
trait Basics {
  type PNCounter
  type LWWRegister[T >: Null]
}
