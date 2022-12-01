package co.domus.domusmobile.model

data class Worker(
    var number_id: String,
    var description: String,
    var score: Float,
    var places: List<Int>,
    var reviews: List<String>,
    var arr_services: List<ServicesWorker>,
)