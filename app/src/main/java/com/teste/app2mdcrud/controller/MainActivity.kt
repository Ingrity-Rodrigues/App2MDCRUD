package com.teste.app2mdcrud.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.teste.app2mdcrud.R
import com.teste.app2mdcrud.data.dao.LivroDAO
import com.teste.app2mdcrud.model.Livro

class MainActivity : AppCompatActivity() {

    private lateinit var livroDAO: LivroDAO
    private lateinit var listView: ListView
    private lateinit var emptyTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.lvLivros)
        emptyTextView = findViewById(R.id.tvVazio)
        livroDAO = LivroDAO(this)

        listAllLivros()

        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedLivro = parent.getItemAtPosition(position) as Livro
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("livroID", selectedLivro.id)
            }
            startActivity((intent))
        }
    }

    private fun listAllLivros() {
        val livros = livroDAO.getAllBooks()
        if (livros.isEmpty()) {
            listView.visibility = ListView.GONE
            emptyTextView.visibility = TextView.VISIBLE
        } else {
            listView.visibility = ListView.VISIBLE
            emptyTextView.visibility = TextView.GONE
            val adapter: ArrayAdapter<Livro> = ArrayAdapter(this, android.R.layout.simple_list_item_1, livros)
            listView.adapter = adapter
        }
    }

    fun newLivro(view: View) {
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        listAllLivros()
    }
}