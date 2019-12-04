import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.puebaandroid.api.Api

object RetrofitClient {

    private const val BASE_URL = "http://157.230.145.29:8080/"

    val instance: Api by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(Api::class.java)
    }

}