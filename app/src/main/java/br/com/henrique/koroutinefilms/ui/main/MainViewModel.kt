package br.com.henrique.koroutinefilms.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    val filmsLiveData = MutableLiveData<List<Film>>()

    fun getFilms(){
        repository.getFilms { filmes -> filmsLiveData.postValue(filmes) }
    }

    fun getFilmsCoroutine(){
        CoroutineScope(Dispatchers.Main).launch {
            val films = withContext(Dispatchers.Default) {
                repository.getFilmesCoroutine()
            }

            filmsLiveData.value = films
        }
    }


}

class MainViewModelFactory(
    private val repository: MainRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}