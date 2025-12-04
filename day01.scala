//> using scala 3.7.4
//> using toolkit default

class Dial:

  var pos = 50
  var num0 = 0

  def rotate(direction: Char, distance: Int): Unit =
    // println(s"\n$direction, $distance")
    if direction == 'R' then pos = pos + distance else pos = pos - distance
    // println(s"pre-fix pos = $pos")
    if pos >= 100 then pos = pos - (100 * (pos / 100))
    if pos <= -1 then pos = pos + (100 * (math.abs(pos / 100) + 1))
    // println(s"pos = $pos")
    if pos == 0 then num0 = num0 + 1
    // println(s"num0 = $num0")

  def rotate2(rotation: String): Unit =
    var direction = rotation.head
    var distance = rotation.tail
    // If distance is 100+, truncate to final two digits
    if distance.length > 2 then distance = distance.slice(distance.length - 2, distance.length)

    if direction == 'R' then
      pos = pos + distance.toInt
      if pos >= 100 then pos = pos - 100

    if direction == 'L' then
      pos = pos - distance.toInt
      if pos <= -1 then pos = pos + 100

    if pos == 0 then num0 = num0 + 1

  def processRotations(inputs: Seq[String]): Unit =
    for input <- inputs do rotate(input.head, input.tail.toInt)

def load_data(): Seq[String] =
  val path = os.pwd / "inputs" / "day01"
  os.read.lines(path)

@main
def day01(): Unit =
  var dial = Dial()
  // dial.processRotations(load_data())
  for input <- load_data() do dial.rotate2(input)
  println(dial.num0)
