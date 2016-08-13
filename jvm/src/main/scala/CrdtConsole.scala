import java.util.UUID

import net.sigmalab.crdt.GCounterImpl
import cats.implicits._

/**
  * Created by ssrepfler on 29/06/16.
  */
object CrdtConsole extends App {

  private def randomUUID = UUID.randomUUID.toString

  override def main(args: Array[String]): Unit = {

    val counter1 = GCounterImpl[Int](randomUUID, 1)
    println(counter1)
    val counter2 = GCounterImpl[Int](randomUUID, 1)
    println(counter2)
    val counter3 = GCounterImpl[Int](randomUUID, 1)
    println(counter3)

    val counter12 = counter1.merge(counter2)
    println(counter12)

    val counter123 = counter1.merge(counter2).merge(counter3)
    println(counter123)
    println(counter123.value)

    val counter1234 = counter123.merge(GCounterImpl[Int](randomUUID, 2))
    println(counter1234)
    println(counter1234.value)

    val counter12340 = counter1234.merge(GCounterImpl[Int](randomUUID, 0))
    println(counter12340)
    println(counter12340.value)

    val counter12340incdByZero = counter12340.increment(0)
    println(counter12340incdByZero.value)
  }
}
