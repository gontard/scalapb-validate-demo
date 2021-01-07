import cats.data.NonEmptyList
import mypkg.demo._
import mypkg.PositiveInt

class Spec extends munit.FunSuite {
  test("PositiveInt") {
    // the field positive_int was transformed to PositiveInt
    val m = Demo(
      Some(PositiveInt(5))
    )

    assert(
      intercept[IllegalArgumentException](
        Demo(Some(PositiveInt(-7)))
      ).getMessage
        .contains("Demo.positive_int: -7 must be greater than 0")
    )

    assert(
      intercept[IllegalArgumentException](Demo(atLeast4 = Some(-3))).getMessage
        .contains("Demo.at_least_4: -3 must be greater than or equal to 4")
    )
  }

  test("Cat Types") {
    val m = NonEmpty(demos = NonEmptyList.of(Demo(Some(PositiveInt(5)))))
  }

  test("Unique") {
    val m2 = Unique2(values = Seq(1, 2, 2, 3))
    val invalidBytes = m2.toByteArray

    assert(Unique.parseFrom(invalidBytes) == Unique(values = Set(1, 2, 3)))
  }
}
