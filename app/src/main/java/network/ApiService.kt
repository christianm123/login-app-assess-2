package network

import data.Entity
import data.LoginRequest
import data.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @POST("/footscray/auth")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @GET("/dashboard/fashion")
    fun getEntities(@Header("Authorization") keypass: String): Call<List<Entity>>
}
