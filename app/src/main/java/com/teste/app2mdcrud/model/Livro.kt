package com.teste.app2mdcrud.model

data class Livro(
    val id: Int = 0,
    val nome: String,
    val autor: String,
    val resumo: String
) {
    override fun toString(): String {
        return nome
    }
}
