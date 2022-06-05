package cinema

/**
 * Main function. Controls the flow of the program
 */
fun main() {
    println("Enter the number of rows:")
    val numberOfRows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seatsPerRow = readln().toInt()
    val cinemaRoom = CinemaRoom(numberOfRows, seatsPerRow)
    while (true) {
        for (n in Actions.fromNumber.keys) {
            println("$n. ${Actions.fromNumber[n]!!.description}")
        }
        println("0. Exit")
        when (val input = readln().toInt()) {
            0 -> return
            !in Actions.fromNumber.keys -> println("Unknown action")
            else -> {
                println()
                val controller = Actions.fromNumber[input]!!.controller
                controller.performAction(cinemaRoom)
                println()
            }
        }
    }
}
