package be.tftic.web2023.exo01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var inputUsername : EditText
    lateinit var inputPassword : EditText
    lateinit var btnValid : Button
    lateinit var btnReset : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Binding des views du layout
        inputUsername = findViewById(R.id.et_main_username)
        inputPassword = findViewById(R.id.et_main_password)
        btnValid = findViewById(R.id.btn_main_valid)
        btnReset = findViewById(R.id.btn_main_reset)

        // Add Listenner
        btnValid.setOnClickListener(this)
        btnReset.setOnClickListener(this)

        inputUsername.addTextChangedListener {
                text -> lockLoginBtn()
        }
        inputPassword.addTextChangedListener {
                text -> lockLoginBtn()
        }
    }

    private fun lockLoginBtn() {
        val username : String = inputUsername.text.toString()
        val password : String = inputPassword.text.toString()

        btnValid.isEnabled = !(username.isBlank() || password.isEmpty())
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_main_valid -> login()
            R.id.btn_main_reset -> reset()
            else -> Toast.makeText(this, R.string.toast_unsupported, Toast.LENGTH_SHORT).show()
        }
    }

    private fun login() : Unit {
        val username : String = inputUsername.text.toString()
        val password : String = inputPassword.text.toString()

        if(username == "Della" && password == "Test1234=") {
            val message : String = getString(R.string.toast_welcome).format(username)
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText(this, R.string.toast_error_login, Toast.LENGTH_LONG).show()

            inputPassword.text.clear()
            inputUsername.requestFocus()
        }
    }

    private fun reset() : Unit {
        inputUsername.text.clear()
        inputPassword.text.clear()

        inputUsername.clearFocus()
        inputPassword.clearFocus()
    }
}