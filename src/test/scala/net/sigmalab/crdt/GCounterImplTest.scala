package net.sigmalab.crdt

import org.scalatest.FunSuite
import cats.implicits._

/**
  * Created by schrepfler on 16/05/2016.
  */
class GCounterImplTest extends FunSuite {

  test("testMerge") {

    val counter1 = GCounterImpl()
    val counter2 = GCounterImpl()
    val counter3 = GCounterImpl()

    val counter123 = counter1.merge(counter2).merge(counter3)
    assert(0 == counter123.value)

    val counterOne = GCounterImpl()
    val counterOneInc = counterOne.increment(1)

    val counter123mergedWithcounterOneIncremented = counter123.merge(counterOneInc)
    assert(1 == counter123mergedWithcounterOneIncremented.value)

    var counterOfThree = GCounterImpl()
    counterOfThree = counterOfThree.increment(1)
    counterOfThree = counterOfThree.increment(1)
    counterOfThree = counterOfThree.increment(1)

    assert(3 == counterOfThree.value)

    var counterOfFive = GCounterImpl()
    counterOfFive = counterOfFive.increment(5)

    assert(5 == counterOfFive.value)

  }

  test("testValue") {

  }

  test("testPayload") {

  }

  test("testEquals") {

  }

}
