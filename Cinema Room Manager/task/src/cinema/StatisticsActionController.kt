package cinema

/** Controller for the Statistics action */
object StatisticsActionController : ActionController {
    override fun performAction(cinemaRoom: CinemaRoom) {
        println("Number of purchased tickets: ${cinemaRoom.bookedSeats}")
        val percentage = "%.2f".format(100.0 * cinemaRoom.bookedSeats / cinemaRoom.totalSeats)
        println("Percentage: ${percentage}%")
        println("Current income: $${cinemaRoom.currentProfit}")
        println("Total income: $${cinemaRoom.totalProfit}")
    }
}
