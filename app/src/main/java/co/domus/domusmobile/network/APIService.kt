package co.domus.domusmobile.network

import co.domus.domusmobile.model.*
import co.domus.domusmobile.network.ApiConstants.BASE_URL
import co.domus.domusmobile.network.ApiConstants.ROLE_CLIENT
import co.domus.domusmobile.network.ApiConstants.ROLE_WORKER
import co.domus.domusmobile.network.ApiConstants.SERVICES
import co.domus.domusmobile.network.ApiConstants.USERS
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIService {
    @GET(SERVICES)
    suspend fun getServices(): List<Service>

    @POST(USERS)
    suspend fun createUser(@Body user: User): UserResponse

    @POST("$ROLE_CLIENT/{id}")
    suspend fun createClientRole(@Path("id") id: String, @Body client: Client): UserResponse

    @POST("$ROLE_WORKER/{id}")
    suspend fun createWorkerRole(@Path("id") id: String, @Body worker: Worker): UserResponse

    companion object{
        var apiService: APIService? = null
        fun getInstance() : APIService {
            if(apiService == null){
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIService::class.java)
            }
            return apiService!!
        }
    }
}