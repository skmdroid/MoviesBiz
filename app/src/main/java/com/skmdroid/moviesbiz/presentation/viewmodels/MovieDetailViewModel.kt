package com.skmdroid.moviesbiz.presentation.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.skmdroid.moviesbiz.MovieRepository
import com.skmdroid.moviesbiz.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MovieDetailViewModel @javax.inject.Inject constructor(
    application: Application,
    private val repository: MovieRepository
) : AndroidViewModel(application) {
    private val _movie: MutableStateFlow<Result?> = MutableStateFlow(null)
    val movie: StateFlow<Result?> get() = _movie

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    fun getMovieDetail(id: Int) {
        viewModelScope.launch {
            try {
                _loading.value = true
                val detail = repository.getMovieDetail(id)
                _movie.value = detail
            } catch (e: Exception) {
                _error.value = "Error fetching movies: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }
}