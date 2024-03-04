package tictactoe

fun checkWin(grid: List<Char>, player: Char): Boolean {
    // check horizontal wins
    for (i in 0 until 9 step 3) {
        if (grid[i] == player && grid[i + 1] == player && grid[i + 2] == player) {
            return true
        }
    }

    // check vertical wins
    for (i in 0 until 3) {
        if (grid[i] == player && grid[i + 3] == player && grid[i + 6] == player) {
            return true
        }
    }

    // check diagonal wins
    if (grid[0] == player && grid[4] == player && grid[8] == player) {
        return true
    }
    if (grid[2] == player && grid[4] == player && grid[6] == player) {
        return true
    }

    return false
}

fun main() {
    val input = "         "
    val grid = input.toCharArray().toMutableList()
    var x = true

    fun printGameGrid() {
        println("---------")
        for (i in 0 until 9 step 3) {
        println("| ${grid[i]} ${grid[i + 1]} ${grid[i + 2]} |")
        }
        println("---------")
    }

    printGameGrid()
    
    while (' ' in grid) {
        try {
            val (row, col) = readln().split(" ").map { it.toInt() }
            
            if (row !in 1..3 || col !in 1..3) {
                println("Coordinates should be from 1 to 3!")
                continue
            }

            val index = (row - 1) * 3 + (col - 1)
            if (grid[index] != ' ') {
                println("This cell is occupied! Choose another one!")
                continue
            }

            if (x == true) {
                grid[index] = 'X'
                printGameGrid()
                x = false
            } else {
                grid[index] = 'O'
                printGameGrid()
                x = true
            }

            val xCount = grid.count { it == 'X' }
            val oCount = grid.count { it == 'O' }

            if (Math.abs(xCount - oCount) > 1) {
                println("Impossible")
                return
            }
            
            val xWins = checkWin(grid, 'X')
            val oWins = checkWin(grid, 'O')

            if (xWins) {
                println("X wins")
                break
            } else if (oWins) {
                println("O wins")
                break
            } else if (!grid.contains(' ')) {
                println("Draw")
                break
            }
            
        } catch (e: Exception) {
            println("You should enter numbers!")
        }
    }
}
