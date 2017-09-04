package net.sigmalab.crdt

import cats.kernel.{ Monoid, Order }
import cats.implicits._

object GCounterImpl {

  /**
   * Created by schrepfler on 01/05/2016.
   */
  case class GCounterImpl[N: Monoid: Order](shardId: String, payload: Map[String, N] = Map[String, N]()) extends GCounter[String, N] {

    val monoid = implicitly[Monoid[N]]
    val order = implicitly[Order[N]]

    override def increment(amt: N): GCounterImpl[N] = {
      require(order.gteqv(amt, monoid.empty), s"GCounters can only grow, increment $amt must be greater or equal than neutral element ${monoid.empty}")
      val sum = payload.get(shardId).fold(amt)(amt |+| _)
      GCounterImpl(shardId, payload.updated(shardId, sum))
    }

    override def value: N = {
      monoid.combineAll(payload.values)
    }

    override def merge(other: StateBased[N, Map[String, N]]): GCounterImpl[N] = {
      val mergedPayload = (this.payload.keySet ++ other.payload.keySet).map(uuid => (uuid, this.payload.getOrElse(uuid, monoid.empty).max(other.payload.getOrElse(uuid, monoid.empty)))).toMap
      //    val mergedPayload = (this.payload.keySet ++ other.payload.keySet).map(uuid => (uuid, Ior.fromOptions(this.payload.get(uuid), other.payload.get(uuid)).map(_.fold(identity, identity, order.max)))).toMap
      GCounterImpl(shardId, mergedPayload)
    }

  }

  def apply[N: Monoid: Order](id: String, param: N): GCounterImpl[N] = {
    GCounterImpl(shardId = id, payload = Map(id -> param))
  }

}
