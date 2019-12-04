package com.example.pruebaandroid.ui.prueba_android

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebaandroid.R
import com.example.puebaandroid.models.DefaultResponse
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        account.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        val intent = Intent(this, HomeActivity::class.java)

        val register = findViewById<TextView>(R.id.button_register)

        register.setOnClickListener {

            val email = findViewById<EditText>(R.id.email)
            val password = findViewById<EditText>(R.id.password)
            val username = findViewById<EditText>(R.id.username)
            val name = findViewById<EditText>(R.id.name)
            val lastname = findViewById<EditText>(R.id.lastname)
            val phone = findViewById<EditText>(R.id.phone)

            if(email.text.toString().trim().isEmpty()){
                email.error = "Campo requerido"
                email.requestFocus()
                return@setOnClickListener
            }

            if(password.text.toString().trim().isEmpty()){
                password.error = "Campo requerido"
                password.requestFocus()
                return@setOnClickListener
            }

            if(username.text.toString().trim().isEmpty()){
                username.error = "Campo requerido"
                username.requestFocus()
                return@setOnClickListener
            }

            if(name.text.toString().trim().isEmpty()){
                name.error = "Campo requerido"
                name.requestFocus()
                return@setOnClickListener
            }

            if(lastname.text.toString().trim().isEmpty()){
                lastname.error = "Campo requerido"
                lastname.requestFocus()
                return@setOnClickListener
            }

            if(phone.text.toString().trim().isEmpty()){
                phone.error = "Campo requerido"
                phone.requestFocus()
                return@setOnClickListener
            }


            RetrofitClient.instance.createUser(email.text.toString().trim(), password.text.toString().trim(), username.text.toString().trim(), name.text.toString().trim(), lastname.text.toString().trim(), phone.text.toString().trim())
                .enqueue(object: Callback<DefaultResponse> {
                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                        Toast.makeText(applicationContext, response.body()?.name, Toast.LENGTH_LONG).show()
                        goHome(name.text.toString().trim())
                    }

                })
        }
    }
    fun goHome(name: String?) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("name", name.toString())
        startActivity(intent)
    }
}
