package com.example.loginappassess2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import data.LoginRequest
import data.LoginResponse
import network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get references to the UI elements
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        // Set an OnClickListener for the login button
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Validate if the username and password fields are not empty
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
            } else {
                // Call the login API
                login(username, password)
            }
        }
    }

    // Function to call the login API
    private fun login(username: String, password: String) {
        val loginRequest = LoginRequest(username, password)

        RetrofitClient.instance.login(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val loginResponse = response.body()
                    val keypass = loginResponse?.keypass

                    if (!keypass.isNullOrEmpty()) {
                        // Log the keypass to check if it's being received correctly
                        Log.d("MainActivity", "Received keypass: $keypass")

                        // Navigate to the DashboardActivity
                        val intent = Intent(this@MainActivity, DashboardActivity::class.java)
                        intent.putExtra("fashion", keypass) // Pass the keypass to the Dashboard
                        startActivity(intent)
                        finish() // Close the login screen after successful login
                    } else {
                        Log.e("MainActivity", "Login failed: keypass is null or empty")
                        Toast.makeText(this@MainActivity, "Login failed: Missing keypass", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("MainActivity", "Login failed: ${response.message()}")
                    Toast.makeText(this@MainActivity, "Login failed: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // Show an error message if the login request fails
                Log.e("MainActivity", "Login API failed: ${t.message}")
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}


