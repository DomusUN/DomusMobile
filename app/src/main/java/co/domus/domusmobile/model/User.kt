package co.domus.domusmobile.model

data class User(
    var email: String,
    var password: String?,
    var name: String?,
    var lastname: String,
    var address: String,
    var phone: String,
)