package cinema

/** Interface for controllers of the actions */
interface ActionController {
    /**
     * Performs the action
     *
     * @param cinemaRoom the cinema room on which the action is performed
     */
    fun performAction(cinemaRoom: CinemaRoom)

}
