package com.teste.app2mdcrud.data.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.teste.app2mdcrud.data.db.DBHelper
import com.teste.app2mdcrud.model.Livro

class LivroDAO (private val context: Context) {

    private val dbHelper = DBHelper(context)

    fun addLivro(livro: Livro): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nome", livro.nome)
            put("autor", livro.autor)
            put("resumo", livro.resumo)
        }
        val id = db.insert(DBHelper.TABLE_NAME, null, values)
        db.close()
        return id
    }

    fun getAllBooks(): List<Livro> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null, null, null)
        val livroList = mutableListOf<Livro>()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"))
            val autor = cursor.getString(cursor.getColumnIndexOrThrow("autor"))
            val resumo = cursor.getString(cursor.getColumnIndexOrThrow("resumo"))
            livroList.add(Livro(id, nome, autor, resumo))
        }
        cursor.close()
        db.close()
        return livroList
    }

    fun getLivroById(id: Int): Livro? {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(DBHelper.TABLE_NAME, null, "id=?", arrayOf(id.toString()), null, null, null)

        var livro: Livro? = null
        if (cursor.moveToFirst()) {
            val nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"))
            val autor = cursor.getString(cursor.getColumnIndexOrThrow("autor"))
            val resumo = cursor.getString(cursor.getColumnIndexOrThrow("resumo"))
            livro = Livro(id, nome, autor, resumo)
        }
        cursor.close()
        db.close()
        return livro
    }

    fun updateLivro(livro: Livro): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nome", livro.nome)
            put("autor", livro.autor)
            put("resumo", livro.resumo)
        }
        val rowsAffected = db.update(DBHelper.TABLE_NAME, values, "id=?", arrayOf((livro.id.toString())))
        db.close()
        return rowsAffected
    }

    fun deleteLivro(id: Int): Int {
        val db = dbHelper.writableDatabase
        val rowsDeleted = db.delete(DBHelper.TABLE_NAME, "id=?", arrayOf(id.toString()))
        db.close()
        return rowsDeleted
    }
}