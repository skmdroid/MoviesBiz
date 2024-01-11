package com.skmdroid.moviesbiz.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.skmdroid.moviesbiz.MovieRepository
import com.skmdroid.moviesbiz.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val repository: MovieRepository
) : AndroidViewModel(application) {
    private val _movies = MutableStateFlow<List<Result>>(emptyList())
    val movies: StateFlow<List<Result>> get() = _movies

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            try {
                _loading.value = true
                _movies.value = repository.getPopularMovies()
            } catch (e: Exception) {
                _error.value = "Error fetching movies: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }

}