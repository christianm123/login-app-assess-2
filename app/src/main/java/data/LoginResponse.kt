package data

data class LoginResponse(
    val token: String,
    val message: String,
    val keypass: String // Add keypass
)

