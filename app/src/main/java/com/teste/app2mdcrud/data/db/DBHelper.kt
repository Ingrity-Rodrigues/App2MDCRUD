package com.teste.app2mdcrud.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object {
        const val DATABASE_NAME = "livros.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "livrosTb"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                autor TEXT NOT NULL,
                resumo TEXT NOT NULL
            )
        """.trimIndent()
        db?.execSQL(createTable)

        db?.execSQL("INSERT INTO livrosTB (nome, autor, resumo) VALUES ('Harry Potter e a Pedra Filosofal', 'J.K. Rowling', 'Harry Potter descobre que é um bruxo e vai estudar em Hogwarts, uma escola de magia. Lá, ele faz amigos, enfrenta perigos e descobre o mistério da Pedra Filosofal.' )")
        db?.execSQL("INSERT INTO livrosTB (nome, autor, resumo) VALUES ('Jogos Vorazes', 'Suzanne Collins', 'Em uma sociedade distópica, Katniss Everdeen se oferece para participar dos Jogos Vorazes no lugar de sua irmã. Lutando por sua vida, ela desafia o sistema cruel que controla seu mundo.' )")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}