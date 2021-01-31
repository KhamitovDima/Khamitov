package com.h.devlife.ui

import android.app.Application
import androidx.lifecycle.*
import com.h.devlife.db.getDatabase
import com.h.devlife.repo.Repository
import kotlinx.coroutines.*
import java.util.*

enum class ApiStatus { LOADING, ERROR, DONE }

class GifCategoryViewModel(private val app : Application, private val gifCategory : String) : AndroidViewModel(app) {


    private val database = getDatabase(app)
    private val repository = Repository(database, gifCategory)
    private var page = 0

    val listGifs = repository.gifs

    private var _gifIndex = MutableLiveData(0)
    val gifIndex: LiveData<Int>
        get() = _gifIndex


    val sortedListFromDb = Transformations.map(listGifs) {
        if (it.isNotEmpty()) {
            val list = mutableListOf(*listGifs.value?.toTypedArray()!!)
            Collections.sort(list, Collections.reverseOrder())
            list
        } else
            mutableListOf()
    }

    var gifUrl = Transformations.map(sortedListFromDb) {
        if(it.isNotEmpty()) {
            it[_gifIndex.value!!].gifURL
        }else ""

    } as MutableLiveData


    val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())


    private val _status = repository.status
    val status: LiveData<ApiStatus>
        get() = _status

    init {
        scope.launch {
            repository.loadGifs(gifCategory,page)
        }
    }

    fun handleForwardClick(){
        _gifIndex.value = _gifIndex.value?.inc()
        gifUrl.value = sortedListFromDb.value?.get(_gifIndex.value!!)?.gifURL
        if(gifIndex.value == listGifs.value?.size?.minus(1)){
            scope.launch {
                repository.loadGifs(gifCategory, ++page)
            }
        }
    }
    fun handleBackClick() {
        _gifIndex.value = _gifIndex.value!!.dec()
        gifUrl.value = sortedListFromDb.value?.get(_gifIndex.value!!)?.gifURL
    }

    fun handleRepeatDownLoadClick() {
        scope.launch {
            repository.loadGifs(gifCategory, page)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}

class GifCategoryViewModelFactory(private val app: Application, private val gifCategory: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GifCategoryViewModel::class.java)){
            return GifCategoryViewModel(app, gifCategory) as T
        }
        throw  IllegalArgumentException("unknown ViewModel class")
    }

}