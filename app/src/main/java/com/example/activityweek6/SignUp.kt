package com.example.activityweek6
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activityweek6.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {
    //Declare Binding Var (object)
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Declare a variable to hold the view binding object.
        // The binding object allows access to all the views in the layout.
        binding = ActivitySignUpBinding.inflate(layoutInflater) //access to my components
        setContentView(binding.root)

        // Set up the action for the "Create Account" button when clicked.
        binding.btnCreateAccount2.setOnClickListener {
            // Get the email, password, and confirm password entered by the user.
            val email = binding.txtEmail.text.toString()
            val password = binding.txtPassword.text.toString()
            val confirmPassword = binding.txtConfirmPassword.text.toString()

            // Define regular expression patterns for validating email and password formats.
            val emailPattern = Regex("^[a-zA-Z0-9_.±]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+\$")// Pattern for a valid email.
            val passwordPattern = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*[@\$!_%*?&\\-])[A-Za-z\\d@\$!_%*?&\\-]{8,15}\$")// Pattern for password with rules.

            //Password Validation: Added validation for password matching, password strength requirements (8-15 characters, 1 capital letter, 1 special character), and email format validation using regular expressions.
            // Check if the entered password matches the confirmed password.
            if (password != confirmPassword){
                // If they don't match, show an error message.
                Toast.makeText(this, "Password do not match", Toast.LENGTH_SHORT).show()
            }
            // Check if the password meets the required pattern.
            else if (!password.matches(passwordPattern)){
                Toast.makeText(this, "Password needs 8 to 15 characters, 1 capital letter, 1 special character", Toast.LENGTH_LONG).show()
            }
            // Check if the email meets the valid format.
            else if (!email.matches(emailPattern)){
                Toast.makeText(this, "E-mail incorrect", Toast.LENGTH_SHORT).show()
            }
            // If all validations pass, save the email and password using SharedPreferences.
            else {
                val sharedPreferences: SharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE)
                val editor : SharedPreferences.Editor = sharedPreferences.edit()

                // Store the email and password in SharedPreferences.
                editor.putString("EMAIL", email)
                editor.putString("PASSWORD", password)
                editor.apply() // Apply changes to save the data.

                // Show a message indicating that the account was successfully created.
                Toast.makeText(this, "Account Created!", Toast.LENGTH_SHORT).show()

                // Create an Intent to navigate to the Login2 activity after account creation.
                val intent = Intent(this, Login::class.java)
                startActivity(intent)// Start the Login2 activity.
            }
        }

        // Set up the action for the "Back to Main" button when clicked.
        binding.btnBackToMain.setOnClickListener {
            // Create an Intent to navigate back to the MainActivity.
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)// Start the MainActivity.
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}