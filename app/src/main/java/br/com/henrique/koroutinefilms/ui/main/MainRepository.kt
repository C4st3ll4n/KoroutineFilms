package br.com.henrique.koroutinefilms.ui.main

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MainRepository{
    fun getFilms(callback: (filmes: List<Film>) -> Unit){
        Thread {
            Thread.sleep(3000)
            callback.invoke(
                listOf(
                    Film(1, "A volta dos que não foram"),
                    Film(2, "Incêndio em alto mar"),
                    Film(3, "A seca da água"),
                    Film(4, "Chuva molhada"),
                )
            )
        }.start()

    }

    suspend fun getFilmesCoroutine(): List<Film>{
        return withContext(Dispatchers.Default){
            delay(3000)
            listOf(
                Film(1, "A volta dos que não foram"),
                Film(2, "Incêndio em alto mar"),
                Film(3, "A seca da água"),
                Film(4, "Chuva molhada"),
            )
        }
    }
}