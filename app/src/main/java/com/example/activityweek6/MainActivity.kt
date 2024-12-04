package com.example.activityweek6

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activityweek6.databinding.ActivityMainBinding
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


private lateinit var googleSignInClient: GoogleSignInClient

/* Created a private variable (object) called googleSignInClient
* GoogleSignInClient class is responsible for managing the sign in process (sign in, sign out, disconnect)*/

private lateinit var auth: FirebaseAuth
/* We created a variable (objects) called auth,
* FirebaseAuth is used for helping the first variable
* It is used for different types of authentication
* (google, facebook, username / password )*/

private val google_sign_in_code = 123
//done_code = 5678
private lateinit var callbackManager: CallbackManager

private val accessToken : AccessToken? = AccessToken.getCurrentAccessToken();


class MainActivity : AppCompatActivity() {

    // Declare a variable to hold the view binding object.
    // The binding object allows access to all the views in the layout.
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable edge-to-edge display, allowing app content to extend behind system bars like status and navigation bars.
        enableEdgeToEdge()

//        try {
//            val info = packageManager.getPackageInfo(
//                "{com.example.activityweek6}",  //Insert your own package name.
//                PackageManager.GET_SIGNATURES
//            )
//            for (signature in info.signatures) {
//                val md = MessageDigest.getInstance("SHA")
//                md.update(signature.toByteArray())
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
//            }
//        } catch (e: NameNotFoundException) {
//        } catch (e: NoSuchAlgorithmException) {
//        }

        // Initialize the binding object. It inflates (creates) the layout views and binds them to this activity.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // Set the activity's content to the layout bound by the binding object.

        // Set up the action for the "Sign Up 2" button to navigate to the SignUp2 activity.
        binding.btnSignUp.setOnClickListener {
            // set an order to the sign up page to open (intent). To do it first we create the intent then send
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)// Start the SignUp2 activity.
        }

        // Set up the action for the "Login 2" button to navigate to the Login2 activity.
        binding.btnLogin.setOnClickListener {
            // set an order to the sign up page to open (intent). To do it first we create the intent then send
            val intent = Intent(this, Login::class.java)
            startActivity(intent)// Start the Login2 activity.
        }

        /* We are using the first connection only between the Kotlin and xml File*/

        /* Initialize the auth object
        * giving it a value */

        auth = FirebaseAuth.getInstance()
        /* The value (role) of the auth object is to be a fixed copy of my class
        * so that each time I run or open the app (this copy will be there, not a new one)*/

        /* Everytime the user login, we need to check their info
        * to make from their identity and allow him to log in
        *
        * We can specify the data that should be checked (specify the data
        * that google account will add in the sent report (token)*/

        /* We will create a variable
        * the role of this variable is to specify for firebase authentication what data
        * should be expected in the report (token) */

        // gso =  Google Sign Options
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        /* Apply the created variable (gso) that will do some actions to this activity (main page)
        * To apply a variable to a page (activity) we use the function .getClient from the library
        * GoogleSignIn*/

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Set up the action for the "Login 2" button to navigate to the Login2 activity.
        binding.btnGoogle.setOnClickListener {
            // set an order to the sign up page to open (intent). To do it first we create the intent then send
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, google_sign_in_code)
        }


        // Initialize Facebook SDK
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(application)

        callbackManager = CallbackManager.Factory.create()

        // Facebook Login Button
        // Custom button for Facebook login
        val customFacebookButton = findViewById<Button>(R.id.btnFacebook)

        customFacebookButton.setOnClickListener {
            // Start Facebook login process
            LoginManager.getInstance().logInWithReadPermissions(
                this,
                listOf("public_profile")
            )
        }
        // Register callback for login result
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                // Log when login is successful
                Log.d("FacebookLogin", "Login successful: ${result.accessToken.userId}")

                val accessToken = result.accessToken
                // Log the access token for debugging purposes
                Log.d("FacebookLogin", "Access Token: ${accessToken.token}")

                // Firebase Facebook Authentication
                val credential = FacebookAuthProvider.getCredential(accessToken.token)
                auth.signInWithCredential(credential)
                    .addOnCompleteListener(this@MainActivity) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            // Log the user's name upon successful authentication
                            Log.d("FacebookLogin", "Welcome ${user?.displayName}")
                            navigateToNextScreen()
                        } else {
                            // Log the error if authentication fails
                            Log.e("FacebookLogin", "Authentication failed", task.exception)
                        }
                    }
            }

            override fun onCancel() {
                // Log if the user cancels the login
                Log.d("FacebookLogin", "Login canceled by user")
            }

            override fun onError(error: FacebookException) {
                // Log any error that occurs during the login process
                Log.e("FacebookLogin", "Login error: ${error?.message}", error)
            }
        })


        //We will add a listener to the button:
        // We will add a listener to the button that has id: btnGoogleSignIn

        // Set up the action for the "Updates" button to navigate to the updates URL in a web browser.
        /*binding.btnUpdates.setOnClickListener {
            // intent to go to URL, using uri (if we want our code to access any information it will access in the form URI)
            // we want to access URL (link), we will give the link in the form of URI
            val updatesUrl = "https://github.blog/changelog/"
            // now create the order (intent)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(updatesUrl))
            // start the activity created in the previous line
            startActivity(intent)
        }*/

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        callbackManager.onActivityResult(requestCode, resultCode, data)

        if (requestCode == google_sign_in_code) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                val idToken = account?.idToken

                if (idToken != null) {
                    val credential = GoogleAuthProvider.getCredential(idToken, null)
                    auth.signInWithCredential(credential).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            Log.d("GoogleSignIn", "Welcome ${user?.displayName}")
                            val intent = Intent(this, Difficulties::class.java)
                            startActivity(intent)
                        } else {
                            Log.e("GoogleSignIn", "Firebase sign-in failed: ${task.exception?.message}")
                        }
                    }
                } else {
                    Log.e("GoogleSignIn", "idToken is null")
                }
            } catch (e: ApiException) {
                Log.e("GoogleSignIn", "Google Sign-In Failed: ${e.message}")
            }
        }
    }


    private fun navigateToNextScreen() {
        val intent = Intent(this, Difficulties::class.java)
        startActivity(intent)
    }







}