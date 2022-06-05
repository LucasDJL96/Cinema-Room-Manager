package cinema

/**
 * A class representing a cinema room
 *
 * @param numberOfRows the number of rows in this cinema room
 * @param seatsPerRow the number of seats in each row
 */
class CinemaRoom(val numberOfRows: Int, val seatsPerRow: Int) {

    /** The total number of seats in the cinema room */
    val totalSeats = numberOfRows * seatsPerRow

    /** The number of seats that are currently booked */
    var bookedSeats = 0
        private set

    /** The total profit if all seat tickets are sold */
    val totalProfit: Int
        get() = if (totalSeats <= 60) {
                10 * totalSeats
            } else {
                val frontHalfSeats = seatsPerRow * (numberOfRows / 2)
                val backHalfSeats = totalSeats - frontHalfSeats
                10 * frontHalfSeats + 8 * backHalfSeats
            }

    /** The current profit from the number of seat tickets sold */
    val currentProfit: Int
        get() {
            var profit = 0
            for (i in 1..numberOfRows) {
                for (j in 1..seatsPerRow) {
                    val seat = seats[i - 1][j - 1]
                    if (seat.booked) profit += seat.ticketPrice
                }
            }
            return profit
        }

    /** Double list containing the seats of this cinema room */
    private val seats: List<List<Seat>> = buildList(numberOfRows) {
        for (i in 1..numberOfRows) {
            add(buildList(seatsPerRow) {
                for (j in 1..seatsPerRow) {
                    add(Seat(i, j))
                }
            })
        }
    }

    /** Prints the layout of seats on this cinema room */
    fun printState() {
        println("Cinema:")
        print(" ")
        for (j in 1..seatsPerRow) print(" $j")
        println()
        for (i in 1..numberOfRows) {
            print("$i")
            for (j in 1..seatsPerRow) print(" ${seats[i - 1][j - 1].symbol}")
            println()
        }
    }

    /**
     * Books the seat at the specified position
     *
     * @param row the row of the seat
     * @param seatNumber the number of the seat in the row
     *
     * @return the price of the ticket
     *
     * @throws SeatAlreadyBookedException if the seat is already booked
     * @throws IllegalStateException if row or seatNumber are out of the allowed ranges
     */
    fun bookSeat(row: Int, seatNumber: Int): Int {
        check(row in 1..numberOfRows && seatNumber in 1..seatsPerRow)
        val seat = seats[row - 1][seatNumber - 1]
        seat.book()
        bookedSeats++
        return seat.ticketPrice
    }

    /**
     * Checks if the seat at the specified position is booked
     *
     * @param row the row of the seat
     * @param seatNumber the number of the seat in the row
     *
     * @throws IllegalStateException if row or seatNumber are out of the allowed ranges
     */
    fun isSeatBooked(row: Int, seatNumber: Int): Boolean {
        check(row in 1..numberOfRows && seatNumber in 1..seatsPerRow)
        return seats[row - 1][seatNumber - 1].booked
    }

    /**
     * Class that represents a seat in this cinema room
     *
     * @param row the row of the seat
     * @param seatNumber the number of this seat in the row
     */
    private inner class Seat(val row: Int, val seatNumber: Int) {

        /** Whether this seat is booked */
        var booked: Boolean = false

        /**
         * Books this seat
         *
         * @throws SeatAlreadyBookedException if this seat is already booked
         */
        fun book() {
            if (booked) throw SeatAlreadyBookedException()
            booked = true
        }

        /**
         * Unbooks this seat
         *
         * @throws SeatNotBookedException if this seat is not booked
         */
        fun unbook() {
            if (!booked) throw SeatNotBookedException()
            booked = false
        }

        /** The symbol representing the state of this seat */
        val symbol: String
            get() = if (booked) "B" else "S"

        /** The price of the ticket for this seat */
        val ticketPrice: Int by lazy {
            if (totalSeats <= 60) 10
            else if (row <= numberOfRows / 2) 10
            else 8
        }
    }

}
