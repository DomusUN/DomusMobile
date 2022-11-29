package co.domus.domusmobile.network

import co.domus.domusmobile.model.User
import co.domus.domusmobile.model.UserResponse
import co.domus.domusmobile.network.ApiConstants.BASE_URL
import co.domus.domusmobile.network.ApiConstants.ENDPOINT
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface APIService {
    @GET(ENDPOINT)
    suspend fun getCompanies(): List<User>

    @GET(ENDPOINT)
    suspend fun getCompaniesByDepartment(
        @Query(value = "departamento_domicilio") department: String
    ): List<User>

    @POST(ENDPOINT)
    suspend fun createUser(@Body user: User): UserResponse

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