package com.teste.app2mdcrud.controller

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.teste.app2mdcrud.R
import com.teste.app2mdcrud.data.dao.LivroDAO

class PesquisaActivity : AppCompatActivity() {


    private lateinit var livroDao: LivroDAO
    private lateinit var resultTextView: TextView
    private lateinit var pesquisaEditText: EditText
    private lateinit var btnPesquisar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesquisa)

        resultTextView = findViewById(R.id.resultTextView)
        pesquisaEditText = findViewById(R.id.pesquisaEditText)
        btnPesquisar = findViewById(R.id.btnPesquisar)


        livroDao = LivroDAO(this)

        btnPesquisar.setOnClickListener {
            val palavra = pesquisaEditText.text.toString()
            val resultado = livroDao.buscaLivroPorNome(palavra)
            resultTextView.text = if (resultado.isEmpty()) {
                "Nenhum resultado encontrado."
            } else {
                resultado.joinToString("\n")
            }
        }
    }
}