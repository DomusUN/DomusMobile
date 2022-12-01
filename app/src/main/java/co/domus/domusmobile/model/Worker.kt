package co.domus.domusmobile.model

data class Worker(
    var number_id: String,
    var description: String,
    var score: Float,
    var places: MutableList<Int>,
    var reviews: MutableList<String>,
    var arr_services: MutableList<ServicesWorker>)

