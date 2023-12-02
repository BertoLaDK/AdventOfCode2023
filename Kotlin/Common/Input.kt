package Common;

import java.util.List;
import java.io.File;

public class Input {
    var input:MutableList<String> = arrayListOf()

    constructor(day:String){
        File("C:\\Users\\lucas\\OneDrive\\Advent Of Code\\2023\\AdventOfCode2023\\Kotlin\\${day}\\input.txt").forEachLine { input.add(it) }
    }
    public fun Get() : MutableList<String>{
        return input;
    }
}
