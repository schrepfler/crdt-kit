package net.sigmalab.crdt

import java.util.UUID

import cats.implicits._
import org.scalatest.funsuite.AnyFunSuite

/**
 * Created by schrepfler on 16/05/2016.
 */
class GCounterImplTest extends AnyFunSuite {

  private def randomUUID = UUID.randomUUID.toString

  test("testMerge") {
    val counter1 = GCounterImpl[Int](randomUUID, 0)
    val counter2 = GCounterImpl[Int](randomUUID, 0)
    val counter3 = GCounterImpl[Int](randomUUID, 0)

    val counter12 = counter1.merge(counter2)

    val counter123 = counter1.merge(counter2).merge(counter3)
    assert(0 == counter123.value)

    val counterOne = GCounterImpl[Int](randomUUID, 0)
    val counterOneInc = counterOne.increment(1)

    val counter123mergedWithcounterOneIncremented = counter123.merge(counterOneInc)
    assert(1 == counter123mergedWithcounterOneIncremented.value)

    var counterOfThree = GCounterImpl(randomUUID, 0)
    var counterOfThree3 = counterOfThree.increment(1).increment(1).increment(1)

    assert(3 == counterOfThree3.value)

    var counterOfFive = GCounterImpl[Int](randomUUID, 0)
    counterOfFive = counterOfFive.increment(5)

    assert(5 == counterOfFive.value)

    intercept[IllegalArgumentException] {
      counterOfFive.increment(-1)
    }

    val counterA = GCounterImpl[Int]("a", 10)
    val counterB = GCounterImpl[Int]("b", 21)

    val mergedAB = counterA.merge(counterB)
    println(mergedAB)
    val counterASecundum = GCounterImpl[Int]("a", 12)
    println(counterASecundum)

    val mergedAASecundumB = mergedAB.merge(counterASecundum)
    println(mergedAASecundumB)

  }

  //  test("Associativity: x ∧ (y ∧ z) = (x ∧ y) ∧ z")
  //
  //  test("Commutativity: x ∧ y = y ∧ x") {
  //
  //  }
  //
  //  test("Idempotency: x ∧ x = x") {
  //
  //  }

}
