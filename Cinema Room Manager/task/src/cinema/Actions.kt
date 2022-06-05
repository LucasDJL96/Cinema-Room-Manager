package cinema

/**
 * Enum representing the actions the cinema supports
 *
 * @param id an integer representing this action
 * @param description a description of this action
 * @param controller a controller for this action
 */
enum class Actions(val id: Int, val description: String, val controller: ActionController) {
    /** Shows the layout of seats in the cinema room */
    SHOW_SEATS(1, "Show the seats", ShowSeatsActionController),

    /** Buys a ticket */
    BUY_TICKET(2, "Buy a ticket", BuyTicketActionController),

    /** Prints the statistics for the cinema room */
    STATISTICS(3, "Statistics", StatisticsActionController);

    companion object {
        /** Map from id of an action to the action itself */
        val fromNumber = buildMap {
            for (action in Actions.values()) {
                put(action.id, action)
            }
        }
    }

}
