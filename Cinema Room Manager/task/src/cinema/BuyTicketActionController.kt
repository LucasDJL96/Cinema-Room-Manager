package cinema

/** Controller for the Buy Ticket action */
object BuyTicketActionController : ActionController {
    override fun performAction(cinemaRoom: CinemaRoom) {
        var row: Int
        var seatNumber: Int
        while (true) {
            println("Enter a row number:")
            row = readln().toInt()
            println("Enter a seat number in that row:")
            seatNumber = readln().toInt()
            if (row !in 1..cinemaRoom.numberOfRows || seatNumber !in 1..cinemaRoom.seatsPerRow) {
                println("Wrong input!")
                continue
            }
            if (cinemaRoom.isSeatBooked(row, seatNumber)) {
                println("That ticket has already been purchased!")
                continue
            }
            break
        }
        val price = cinemaRoom.bookSeat(row, seatNumber)
        println("Ticket price: $$price")
    }
}
