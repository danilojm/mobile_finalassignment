package com.example.activityweek6
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activityweek6.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    // Declare a variable to hold the view binding object.
    // The binding object allows access to all the views in the layout.
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize the binding object. It inflates (creates) the layout views and binds them to this activity.
        binding = ActivityLoginBinding.inflate(layoutInflater) //access to my components
        setContentView(binding.root) // Set the activity's content to the layout bound by the binding object.

        // Set up the action for the "Login" button when it's clicked.
        binding.btnLogin.setOnClickListener {
            // Get the text entered in the email and password fields.
            val username = binding.txtEmail.text.toString()
            val password = binding.txtPassword.text.toString()


            // Check if both email and password are not empty.
            if (username.isNotEmpty() && password.isNotEmpty()) {
                // Access SharedPreferences to validate the stored email and password.
                val sharedPreferences: SharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE)
                val usernameValidation = sharedPreferences.all.get("EMAIL") // Retrieve the saved email.
                val passwordValidation = sharedPreferences.all.get("PASSWORD") // Retrieve the saved password.


                // Check if the entered email and password match the stored values.
                if (username == usernameValidation && password == passwordValidation ) {
                    // If valid, open the web page and show a success message.
                    val intent = Intent(this, Difficulties::class.java) // Create an Intent to navigate back to the main activity.
                    startActivity(intent) // Open the URL in a browser.
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                } else {
                    // If invalid, show an error message.
                    Toast.makeText(this, "Username or password is incorrect. Try again!", Toast.LENGTH_SHORT).show()
                }

            } else {
                // If either field is empty, show a message asking the user to enter both.
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
            }
        }






        // Set up the action for the "Back to Main" button when it's clicked.
        binding.btnBackToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java) // Create an Intent to navigate back to the main activity.
            startActivity(intent) // Start the MainActivity.
        }
        // This is used to handle the display of system bars (like status and navigation bars)
        // and apply appropriate padding to the layout to prevent overlap.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            // Get the dimensions of system bars (top, bottom, left, right).
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Apply padding to the view to ensure it doesn't overlap with system bars.
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets // Return the insets after applying them.
        }
    }
}