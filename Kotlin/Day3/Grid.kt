package Day3

import Common.Input
import java.util.logging.XMLFormatter

class Grid {
    var Grid: List<String> = arrayListOf<String>()

    constructor(input: Input){
        Grid = input.Get()
    }

    public fun findByCoords(x:Int, y:Int) : Char {
        return Grid[y][x];
    }

    public fun getPosOf(string: String) : Position {
        for ((index,line) in Grid.withIndex()){
            if(line.contains(string)){
                return Position(line.indexOf(string),index)
            }
        }
        return Position(-1,-1);
    }

    public fun getMaxX() : Int {
        return Grid[0].length;
    }
    public fun getMaxY() : Int {
        return Grid.size;
    }
}

class Position {
    var X: Int = 0
    var Y: Int = 0

    constructor(x: Int, y: Int){
        X = x;
        Y = y;
    }

    override fun equals(other: Any?) : Boolean
        = (other is Position)
            && this.X == other.X
            && this.Y == other.Y
}
