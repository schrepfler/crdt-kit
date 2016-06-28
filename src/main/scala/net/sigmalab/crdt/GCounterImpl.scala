package net.sigmalab.crdt

import java.util.UUID

import algebra.{CommutativeMonoid, Order}

object GCounterImpl {

  /**
    * Created by schrepfler on 01/05/2016.
    */
  case class GCounterImpl[@specialized(Int, Long, Float, Double) N:Order:CommutativeMonoid](shardId: UUID = io.jvm.uuid.UUID.randomUUID(), payload: Map[UUID, N] = Map[UUID, N]()) extends GCounter[UUID, N] {

    override def increment(amt: N)(commutativeMonoid: CommutativeMonoid[N]): GCounter[UUID, N] = {
      //    assert(amt >= 0, s"GCounters can only grow, increment $amt is negative")
      payload.get(shardId) match {
        case Some(x) => GCounterImpl(shardId, payload.updated(shardId, commutativeMonoid.combine(amt, x)))
        case None => GCounterImpl(shardId, payload.updated(shardId, amt))
      }
    }

    override def value()(commutativeMonoid: CommutativeMonoid[N]): N = commutativeMonoid.combineAll(payload.withDefaultValue[N](commutativeMonoid.empty).valuesIterator)

    override def merge(other: StateBased[N, Map[java.util.UUID, N]])(order: Order[N]): StateBased[N, Map[UUID, N]] = {
      val mergedPayload = (this.payload.keySet ++ other.payload.keySet).map(uuid => (uuid, order.max(this.payload.get(uuid).get, other.payload.get(uuid).get))).toMap
      GCounterImpl(shardId, mergedPayload)
    }

  }

  def apply[@specialized(Int, Long, Float, Double) N](param: N): GCounterImpl[N] = {
    val uuid = java.util.UUID.randomUUID()
    GCounterImpl(shardId = uuid, payload = Map(uuid -> param))
  }

}