package Day2

class Game() {
    var Id: Int = 0
    var Green: Int = 0
    var Red: Int = 0
    var Blue: Int = 0

    constructor(line: String, efficient: Boolean) : this() {
        Id = getGameId(line);
        if (efficient) {
            var colors = getAllCubes(line)
            Green = colors.first
            Red = colors.second
            Blue = colors.third
        } else {
            Green = GetCubes(CubeColor.Green, line);
            Red = GetCubes(CubeColor.Red, line)
            Blue = GetCubes(CubeColor.Blue, line)
        }
    }


    public fun CheckColor(maxCubes: Int, color: CubeColor): Boolean {
        when (color) {
            CubeColor.Green -> return maxCubes >= Green
            CubeColor.Blue -> return maxCubes >= Blue
            CubeColor.Red -> return maxCubes >= Red
        }
    }


    enum class CubeColor {
        Green,
        Blue,
        Red
    }

    private fun GetCubes(color: CubeColor, line: String): Int {
        when (color) {
            CubeColor.Green -> {
                var highestnum = 0;
                for (s in line.split(":")[1].split(";")) {
                    if (s.contains("green")) {
                        s.split(",").forEach {
                            if (it.contains("green")) {
                                var number = it.removeSuffix(" green").removePrefix(" ").toInt()
                                if (number > highestnum) {
                                    highestnum = number
                                }
                            }
                        }
                    }
                }
                return highestnum
            }

            CubeColor.Blue -> {

                var highestnum = 0;
                for (s in line.split(":")[1].split(";")) {
                    if (s.contains("blue")) {
                        s.split(",").forEach {
                            if (it.contains("blue")) {
                                var number = it.removeSuffix(" blue").removePrefix(" ").toInt()
                                if (number > highestnum) {
                                    highestnum = number
                                }
                            }
                        }
                    }
                }
                return highestnum
            }

            CubeColor.Red -> {
                var highestnum = 0;
                for (s in line.split(":")[1].split(";")) {
                    if (s.contains("red")) {
                        s.split(",").forEach {
                            if (it.contains("red")) {
                                var number = it.removeSuffix(" red").removePrefix(" ").toInt()
                                if (number > highestnum) {
                                    highestnum = number
                                }
                            }
                        }
                    }
                }
                return highestnum
            }
        }
        return 0

    }

    private fun getAllCubes(line: String): Triple<Int, Int, Int> {
        var highestgreen = 0;
        var highestred = 0;
        var highestblue = 0;
        for (s in line.split(":")[1].split(";")) {
            s.split(",").forEach {
                if (it.contains("green")) {
                    var number = it.removeSuffix(" green").removePrefix(" ").toInt()
                    if (number > highestgreen) {
                        highestgreen = number
                    }
                }
                if (it.contains("red")) {
                    var number = it.removeSuffix(" red").removePrefix(" ").toInt()
                    if (number > highestred) {
                        highestred = number
                    }
                }
                if (it.contains("blue")) {
                    var number = it.removeSuffix(" blue").removePrefix(" ").toInt()
                    if (number > highestblue) {
                        highestblue = number
                    }
                }
            }
        }
        return Triple(highestgreen, highestred, highestblue)
    }

    private fun getGameId(line: String): Int {
        return line.split(":")[0].removePrefix("Game ").toInt()
    }

    public fun GetId(): Int {
        return Id;
    }
}