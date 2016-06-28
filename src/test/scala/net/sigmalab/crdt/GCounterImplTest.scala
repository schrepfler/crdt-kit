package net.sigmalab.crdt

import org.scalatest.FunSuite
import cats.implicits._

/**
  * Created by schrepfler on 16/05/2016.
  */
class GCounterImplTest extends FunSuite {

  test("testMerge") {
    val counter1 = GCounterImpl[Int](0)
    val counter2 = GCounterImpl[Int](0)
    val counter3 = GCounterImpl[Int](0)

    val counter12 = counter1.merge(counter2)


    val counter123 = counter1.merge(counter2).merge(counter3)
    assert(0 == counter123.value())

    val counterOne = GCounterImpl[Int](0)
    val counterOneInc = counterOne.increment(1)

    val counter123mergedWithcounterOneIncremented = counter123.merge(counterOneInc)
    assert(1 == counter123mergedWithcounterOneIncremented.value)

    var counterOfThree = GCounterImpl[Int](0)
    var counterOfThreeGCounter = counterOfThree.increment(1)
    counterOfThreeGCounter = counterOfThree.increment(1)
    counterOfThreeGCounter = counterOfThree.increment(1)

    assert(3 == counterOfThree.value)

    var counterOfFive = GCounterImpl[Int](0)
    var counterOfFiveGCounter = counterOfFive.increment(5)

    assert(5 == counterOfFive.value)

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
