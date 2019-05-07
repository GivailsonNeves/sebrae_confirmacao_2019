package com.acptdev.sebraeconfirmacaapp

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.Toast
import com.acptdev.sebraeconfirmacaapp.models.Register
import com.acptdev.sebraeconfirmacaapp.retrofit.RetrofitInitializer
import kotlinx.android.synthetic.main.form.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var progress: ProgressDialog? = null
    private var register = Register("", "", "", "", false, "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prepareActions()
    }

    private fun prepareActions() {
        btnRegister.setOnClickListener { doRegister() }
        swVinculado.setOnCheckedChangeListener { _, isChecked -> register.vinculado = isChecked  }
        prepareSpinner()
    }

    private fun prepareSpinner() {
        val ufAdapter =
            ArrayAdapter.createFromResource(this, R.array.uf_array, android.R.layout.simple_spinner_dropdown_item)
        ufAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spUf.adapter = ufAdapter
    }

    private fun showLoading() {
        if (progress == null) {
            progress = ProgressDialog(this)
            progress!!.setMessage(getString(R.string.registrando))
            progress!!.show()
        }
    }

    private fun hideLoading() {
        if (progress != null) {
            progress!!.dismiss()
            progress = null
        }
    }

    private fun doRegister() {

        if (registerDone()) {
            showLoading()
            RetrofitInitializer(context = this)
                .mainService()
                .register(register)
                .enqueue(object : Callback<Void> {

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Toast.makeText(this@MainActivity, getString(R.string.registrado_sucesso), Toast.LENGTH_LONG)
                            .show()
                        hideLoading()
                    }

                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        Toast.makeText(this@MainActivity, getString(R.string.registro_erro), Toast.LENGTH_LONG)
                            .show()
                        hideLoading()
                        resetForm()
                    }

                })
        } else {
            Toast.makeText(this@MainActivity, getString(R.string.field_validation), Toast.LENGTH_LONG)
                .show()
        }

    }

    private fun resetForm() {
        tfNome.setText("")
        tfCpfCnpj.setText("")
        tfEmail.setText("")
        tfQual.setText("")
    }

    private fun registerDone(): Boolean {
        if (tfNome.text.toString().isNullOrEmpty()) return false
            register.nome = tfNome.text.toString()
        if (tfCpfCnpj.text.toString().isNullOrEmpty()) return false
            register.cpfcnpj = tfCpfCnpj.text.toString()
        if (tfEmail.text.toString().isNullOrEmpty()) return false
            register.email = tfEmail.text.toString()
        if (tfQual.text.toString().isNullOrEmpty()) return false
            register.qual = tfQual.text.toString()

        register.uf = spUf.selectedItem.toString()

        return true
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {

            val checked = view.isChecked

            when (view.getId()) {
                R.id.aluno ->
                    if (checked) {
                        register.tipoParticipante = getString(R.string.aluno)
                    }
                R.id.gestorpublica ->
                    if (checked) {
                        register.tipoParticipante = getString(R.string.gestor_publico)
                    }
                R.id.gestorprivada ->
                    if (checked) {
                        register.tipoParticipante = getString(R.string.gestor_privada)
                    }
                R.id.professor ->
                    if (checked) {
                        register.tipoParticipante = getString(R.string.professor)
                    }
                R.id.outros ->
                    if (checked) {
                        register.tipoParticipante = getString(R.string.outros)
                    }
            }
        }
    }
}
