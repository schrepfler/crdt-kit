package net.sigmalab.crdt

import algebra.CommutativeMonoid
import io.jvm.uuid.UUID

/**
  * Created by schrepfler on 01/05/2016.
  */
case class GCounterImpl(val shardId: java.util.UUID = UUID.randomUUID(), val payload: Map[java.util.UUID, Int] = Map.empty) extends GCounter[java.util.UUID, GCounterImpl, Int] {

  override def increment(amt: Int)(implicit monoid: CommutativeMonoid[Int]): GCounterImpl = {
    assert(amt >= 0, s"GCounters can only grow, increment $amt is negative")
    payload.get(shardId) match {
      case Some(x) => GCounterImpl(shardId, payload.updated(shardId, monoid.combine(amt, x)))
      case None => GCounterImpl(shardId, payload.updated(shardId, amt))
    }
  }

  override def value(implicit monoid: CommutativeMonoid[Int]): Int = monoid.combineAll(payload.withDefaultValue(0).valuesIterator)

  override def merge(other: GCounterImpl): GCounterImpl = {
    val mergedPayload = (this.payload.keySet ++ other.payload.keySet).map(uuid => (uuid, Math.max(this.payload.get(uuid).getOrElse(0), other.payload.get(uuid).getOrElse(0)))).toMap
    GCounterImpl(shardId, mergedPayload)
  }

}
