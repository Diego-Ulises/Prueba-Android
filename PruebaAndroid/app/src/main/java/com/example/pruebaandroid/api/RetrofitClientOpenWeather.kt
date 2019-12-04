import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.puebaandroid.api.ApiOpenWeather

object RetrofitClientOpenWeather {

    private const val BASE_URL = "https://api.openweathermap.org/"

    val instance: ApiOpenWeather by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiOpenWeather::class.java)
    }

}