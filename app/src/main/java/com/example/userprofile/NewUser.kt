package com.example.userprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.URLUtil
import android.widget.Toast
import com.example.userprofile.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout

class NewUser : AppCompatActivity() {
    private var allGood = false

    //Boolean's for check all the data.
    private var booleanUser = false
    private var booleanEmail = false
    private var booleanPassword = false
    private var booleanAge = false
    private var booleanURL = false
    private var booleanGender = false

    //End of declaration
    lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Intent:
        val i = Intent(this, ProfileActivity::class.java)
/*
        binding.TIETUserName.onFocusChangeListener = View.OnFocusChangeListener { view, focus ->
            if (!focus) {
                checkUserName()
            }
        }

        binding.TIETUserGmail.onFocusChangeListener = View.OnFocusChangeListener { view, focus ->
            if (!focus) {
                checkUserEmail()
            }
        }

        binding.TIETUserPassword.onFocusChangeListener = View.OnFocusChangeListener { view, focus ->
            if (!focus) {
                checkUserPassword()
            }
        }

        binding.TIETUserURL.onFocusChangeListener = View.OnFocusChangeListener { view, focus ->
            if (!focus) {
                checkUserURL()
            }
        }

        binding.TIETUserAge.onFocusChangeListener = View.OnFocusChangeListener { view, focus ->
            if (!focus) {
                checkUserAge()
            }
        }



 */




        binding.fab1.setOnClickListener {
            //Con check() se sabe si todos los campos son validados correctamente, devuelve true si todo está en orden.
            allGood = check()
            if(allGood){

                //Colocar toda la entrada de datos.
                i.putExtra("userName", binding.TIETUserName.text.toString())
                i.putExtra("userEmail", binding.TIETUserGmail.text.toString())
                i.putExtra("userPassword", binding.TIETUserPassword.text.toString())
                i.putExtra("userURL", binding.TIETUserURL.text.toString())
                i.putExtra("userAge", binding.TIETUserAge.text.toString())
                i.putExtra("userAge", binding.TIETUserAge.text.toString())
                i.putExtra("userGender", binding.RGGender.checkedButtonId)
                i.putExtra("userVIP", binding.VIPCB.isSelected)



                //It creates the new activity.
                startActivity(i)
            }else{
                Toast.makeText(this, R.string.msg_error_activity, Toast.LENGTH_SHORT).show()
            }
        }



    }




    private fun checkUserName() {
        val user: String = binding.TIETUserName.text.toString().trim()

        /*Al ejecutar isTextEmpty, se sabe si el texto esta vacío o no, si lo está ya pondrá el mensaje de error por su cuenta
        por eso se pasa por parametro un textInputLayout.
         */

        if (!isTextEmpty(user, binding.TILUserName)) {
            //Si no está vacío, se pasa el boolean a true, el error a null por si antes ya marcó error en campo vacío.
                //(Al error ser null, se borra el error que estuvo antes), esto aplica para todos los demás métodos de abajo.
                    //I hope you get it, mate.
            binding.TILUserName.error = null
            booleanUser = true
        }
    }

    private fun checkUserEmail() {

        //Aquí error se inicializa con null, abajo el porqué.

        val email: String = binding.TIETUserGmail.text.toString().trim()
        var error: String? = null

        if (!isTextEmpty(email, binding.TILUserGmail)) {
            var numChar = 0

            email.forEach { char ->
                if (char == '@') {
                    numChar++
                }
            }

            //Comprobar si solo existe un arroba.
            if (numChar == 1) {
                booleanEmail = true
            } else {
                //Escribir el error de email inválido.
                error = getString(R.string.error_invalid_email)
            }
            //Se selecciona el error correspondiente, en caso de no haberlo solo se pondrá null porque al inicio se la marcó como null
            //Así en todos los demás.
            binding.TILUserGmail.error = error
        }

    }

    private fun checkUserPassword() {
        val password = binding.TIETUserPassword.text.toString().trim()
        var error: String? = null

        if (!isTextEmpty(password, binding.TILUserPassword)) {
            //Comprobar que por lo menos contenga 1 número.
            var numbers = 0
            password.forEach { char ->
                if (!char.isLetter()) {
                    numbers++
                }
            }

            if (numbers > 0) {
                booleanPassword = true
            } else {
                error = getString(R.string.error_password_et)
            }

            binding.TILUserPassword.error = error
        }

    }

    private fun checkUserURL() {
        val url = binding.TIETUserURL.text.toString()
        var error: String? = null

        if (!isTextEmpty(url, binding.TILUserURL)) {

           //Checar que la URL sea válida.
            if (URLUtil.isValidUrl(url)) {
                booleanURL = true

            } else {
                error = getString(R.string.invalid_URL)
            }
            binding.TILUserURL.error = error
        }
    }

    private fun checkUserAge() {


        val userAge = binding.TIETUserAge.text.toString()
        if (!isTextEmpty(userAge, binding.TILUserAge)) {
            binding.TILUserAge.error = null
            booleanAge = true
        }


    }

    private fun checkUserGender() {

        val button = binding.RGGender.checkedButtonId

        if(button != View.NO_ID){
            booleanGender = true
            Toast.makeText(this, "RadioButtons", Toast.LENGTH_LONG).show()
        }


    }

    //Función para crear el perfil.
    private fun makeProfile() {
        if (allGood) {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, R.string.msg_error_profile, Toast.LENGTH_SHORT).show()
        }

    }

    private fun isTextEmpty(text: String, TIL: TextInputLayout): Boolean {
        if (text.trim().isEmpty()) {
            TIL.error = getText(R.string.text_empty_error)
            return true
        }

        return false
    }

    private fun check() : Boolean{
        //Checar todo.
        checkUserName()
        checkUserEmail()
        checkUserPassword()
        checkUserURL()
        checkUserAge()
        checkUserGender()
        if(booleanUser && booleanEmail && booleanPassword && booleanURL && booleanAge && booleanGender){
            return true
        }
        return false
    }


}


