package com.example.pruebaandroid.ui.prueba_android

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.pruebaandroid.R
import com.example.puebaandroid.models.WeatherResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {

    val CITY: String = "morelia,mx"
    val API: String = "d2595d04fb2f1281f818a7ce08eb83c"
    var NAME: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        NAME=intent.getStringExtra("name").toString()

        weatherTask().execute()

        val logout = findViewById<TextView>(R.id.logout)
        logout.setOnClickListener {
            finish()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    inner class weatherTask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): String? {
            var response:String?
            try{
                response = URL("api.openweathermap.org/data/2.5/weather?q=London").readText(
                    Charsets.UTF_8
                )
            }catch (e: Exception){
                response = null
            }
            RetrofitClientOpenWeather.instance.getDataWeather()
                .enqueue(object: Callback<WeatherResponse> {
                    override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                        //Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
                        if(response.code()==200){
                            Toast.makeText(
                                applicationContext,
                                "CLIMA RESPONSE: " + response.body()?.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                            val rootObject= JSONObject()
                            rootObject.put("main",response.body()?.main)
                            rootObject.put("sys",response.body()?.sys)
                            rootObject.put("wind",response.body()?.wind)
                            rootObject.put("weather",response.body()?.weather)
                            rootObject.put("dt",response.body()?.dt)
                            //Toast.makeText(applicationContext, rootObject.toString(), Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(
                                applicationContext,
                                "Error al cargar el clima",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                })
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)


            findViewById<TextView>(R.id.hello).text = "¡Hola "+NAME+"!"

            try {
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)

                val updatedAt:Long = jsonObj.getLong("dt")
                val updatedAtText = "Updated at: "+ SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(Date(updatedAt*1000))
                val temp = main.getString("temp")+"°C"
                val tempMin = "Min Temp: " + main.getString("temp_min")+"°C"
                val tempMax = "Max Temp: " + main.getString("temp_max")+"°C"
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")

                val sunrise:Long = sys.getLong("sunrise")
                val sunset:Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")

                val address = jsonObj.getString("name")+", "+sys.getString("country")

                /* Populating extracted data into our views */
                findViewById<TextView>(R.id.address).text = address
                findViewById<TextView>(R.id.updated_at).text =  updatedAtText
                findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
                findViewById<TextView>(R.id.temp).text = temp
                findViewById<TextView>(R.id.temp_min).text = tempMin
                findViewById<TextView>(R.id.temp_max).text = tempMax
                findViewById<TextView>(R.id.sunrise).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise*1000))
                findViewById<TextView>(R.id.sunset).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset*1000))
                findViewById<TextView>(R.id.wind).text = windSpeed
                findViewById<TextView>(R.id.pressure).text = pressure
                findViewById<TextView>(R.id.humidity).text = humidity


            } catch (e: Exception) {
            }

        }
    }
}
