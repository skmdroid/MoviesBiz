package com.skmdroid.moviesbiz.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.skmdroid.moviesbiz.MovieRepository
import com.skmdroid.moviesbiz.data.remote.Result
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

    private val _isRefreshing = MutableStateFlow<Boolean>(false)
    val isRefreshing: StateFlow<Boolean> get() = _isRefreshing

    init {
        getPopularMovies()
    }

    fun refresh(){
        viewModelScope.launch {
            _isRefreshing.value = true
            pullPopularMoviesFromRemote()
        }

    }

    fun pullPopularMoviesFromRemote() {
        getPopularMovies(true)
    }

    private fun getPopularMovies(forceRefresh: Boolean = false) {
        viewModelScope.launch {
            _isRefreshing.value = true
            try {
                _loading.value = true
                _movies.value = repository.getPopularMovies(forceRefresh)
                _isRefreshing.value = false
            } catch (e: Exception) {
                _error.value = "Error fetching movies: ${e.message}"
                _isRefreshing.value = false
            } finally {
                _loading.value = false
                _isRefreshing.value = false
            }
        }
    }

}