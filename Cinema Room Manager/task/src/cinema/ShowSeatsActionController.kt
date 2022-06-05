package cinema

/** Controller for the Show Seats action */
object ShowSeatsActionController : ActionController {
    override fun performAction(cinemaRoom: CinemaRoom) {
        cinemaRoom.printState()
    }
}
