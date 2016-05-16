package net.sigmalab.crdt

import io.jvm.uuid.UUID

/**
  * Created by schrepfler on 01/05/2016.
  */
case class GCounterImpl(val payload: Map[java.util.UUID, Int] = Map.empty, val shardId: java.util.UUID = UUID.randomUUID()) extends GCounter[java.util.UUID, GCounterImpl, Int] {

  override def increment(amt: Int): GCounterImpl = {
    assert(amt >= 0, s"GCounters can only grow, increment $amt is negative")
    payload.get(shardId) match {
      case Some(x) => GCounterImpl(payload.updated(shardId, amt + x), shardId)
      case None => GCounterImpl(payload.updated(shardId, amt), shardId)
    }
  }

  override def value: Int = payload.withDefaultValue(0).valuesIterator.sum

  override def merge(other: GCounterImpl): GCounterImpl = {
    val mergedPayload = (this.payload.keySet ++ other.payload.keySet).map(uuid => (uuid, Math.max(this.payload.get(uuid).getOrElse(0), other.payload.get(uuid).getOrElse(0)))).toMap
    GCounterImpl(mergedPayload, shardId)
  }

}
