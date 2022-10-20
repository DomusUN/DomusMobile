package co.domus.domusmobile.data

/**
 * Data class that represents the current UI state in terms of [quantity], [flavor],
 * [dateOptions], selected pickup [date] and [price]
 */
data class ServiceUiState(
    /** Selected cupcake quantity (1, 6, 12) */
    val name: String = "",
    /** Flavor of the cupcakes in the order (such as "Chocolate", "Vanilla", etc..) */
    val type: String = "",
    /** Selected date for pickup (such as "Jan 1") */
    val date: String = "",
    /** Total price for the order */
    val price: String = "",
)
