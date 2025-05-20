package com.teste.app2mdcrud

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {

    private lateinit var livroDAO: LivroDAO
    private  var livroID: Int = 0
    private lateinit var nome: EditText
    private lateinit var autor: EditText
    private lateinit var resumo: EditText
    private lateinit var btnDeletar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        livroDAO = LivroDAO(this)

        nome = findViewById(R.id.nome)
        autor = findViewById(R.id.autor)
        resumo = findViewById(R.id.resumo)
        btnDeletar = findViewById(R.id.btnDeletar)

        livroID = intent.getIntExtra("livroID",0)
        if (livroID != 0){
            btnDeletar.visibility = Button.VISIBLE
            editarLivro()
        }
    }

    fun salvarLivro(view: View) {
        if (nome.text.isNotEmpty() && autor.text.isNotEmpty() && resumo.text.isNotEmpty()) {
            if (livroID == 0) {
                val novoLivro = Livro(
                    name = nome.text.toString(),
                    autor = autor.text.toString(),
                    resumo = resumo.text.toString()
                )

                livroDAO.addLivro(novoLivro)
                Toast.makeText(this, "Livro adicionado", Toast.LENGTH_SHORT).show()
            } else {
                val atualizaLivro = Livro(
                    id = livroID,
                    name = nome.text.toString(),
                    autor = autor.text.toString(),
                    resumo = resumo.text.toString()
                )
                livroDAO.updateLivro(atualizaLivro)
                Toast.makeText(this, "Livro atualizado", Toast.LENGTH_SHORT).show()
            }
            finish()
        }else{
            Toast.makeText(this,"Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun editarLivro() {
        val livro = livroDAO.getCharById(livroID)
        livro?.let {
            nome.setText(it.nome)
            autor.setText(it.autor)
            resumo.setText(it.resumo)
        }
    }

    private fun deletarLivro(view: View) {
        livroDAO.deleteLivro(livroID)
        Toast.makeText(this, "Livro excluido!", Toast.LENGTH_SHORT).show()
    }

}