package net.sigmalab.crdt

import cats.kernel.CommutativeMonoid
import cats.kernel.Order
import cats.implicits._

object GCounterImpl {

  /**
    * Created by schrepfler on 01/05/2016.
    */
  case class GCounterImpl[@specialized(Int, Long, Float, Double) N](shardId: String, payload: Map[String, N] = Map[String, N]()) extends GCounter[String, N] {

    override def increment(amt: N)(implicit order: Order[N], commutativeMonoid: CommutativeMonoid[N]): GCounterImpl[N] = {
      require(order.gt(amt, commutativeMonoid.empty), s"GCounters can only grow, increment $amt must be greater than neutral element ${commutativeMonoid.empty}")
      payload.get(shardId) match {
        case Some(x) => GCounterImpl(shardId, payload.updated(shardId, amt |+| x))
        case None => GCounterImpl(shardId, payload.updated(shardId, amt))
      }
    }

    override def value (implicit commutativeMonoid: CommutativeMonoid[N]): N = commutativeMonoid.combineAll(payload.withDefaultValue[N](commutativeMonoid.empty).valuesIterator)

    override def merge(other: StateBased[N, Map[String, N]])(implicit order: Order[N], commutativeMonoid: CommutativeMonoid[N]): GCounterImpl[N] = {
      val mergedPayload = (this.payload.keySet ++ other.payload.keySet).map(uuid => (uuid, this.payload.getOrElse(uuid, commutativeMonoid.empty) max other.payload.getOrElse(uuid, commutativeMonoid.empty))).toMap
      GCounterImpl(shardId, mergedPayload)
    }

  }

  def apply[@specialized(Int, Long, Float, Double) N](id: String, param: N): GCounterImpl[N] = {
    GCounterImpl(shardId = id, payload = Map(id -> param))
  }

}