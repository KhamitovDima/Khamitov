package com.h.devlife.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.h.devlife.data.ApiResponse
import com.h.devlife.data.Gif
import com.h.devlife.data.ResultGif
import com.h.devlife.db.GifsDatabase
import com.h.devlife.extensions.*
import com.h.devlife.network.NetworkService
import com.h.devlife.network.RestService
import com.h.devlife.ui.ApiStatus
import com.h.devlife.ui.GifsType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val database: GifsDatabase, private var gifCategory: String) {

    private val api : RestService = NetworkService.retrofitService
    val status : MutableLiveData<ApiStatus> = MutableLiveData(ApiStatus.LOADING)

    val gifs: LiveData<List<Gif>> =
        when(gifCategory) {
            GifsType.LATEST.titleAPI -> Transformations.map(database.gifDao.getLatestGifs()) {
                it.fromLatestToDomainModel()
            }
            GifsType.TOP.titleAPI -> Transformations.map(database.gifDao.getTopGifs()){
                it.fromTopToDomainModel()
            }
            GifsType.HOT.titleAPI -> Transformations.map(database.gifDao.getHotGifs()){
                it.fromHotToDomainModel()
            }
            else -> Transformations.map(database.gifDao.getLatestGifs()){
                it.fromLatestToDomainModel()
            }
        }

    suspend fun loadGifs(gifCategory: String, page: Int = 0) {
        withContext(Dispatchers.IO){
            status.postValue(ApiStatus.LOADING)
            var apiResponse = ApiResponse(mutableListOf(),0)
            try {
                apiResponse = api.getGifs(gifCategory, page).await()
                status.postValue(ApiStatus.DONE)
            }catch (e: Exception) {
                Log.d("GifCategoryViewModel","${e.message}")
                status.postValue(ApiStatus.ERROR)
            }

            when(gifCategory){
                GifsType.LATEST.titleAPI -> database.gifDao.insertAllLatest(apiResponse.result.asDbModelLatest())
                GifsType.TOP.titleAPI -> database.gifDao.insertAllTop(apiResponse.result.asDbModelTop())
                GifsType.HOT.titleAPI -> database.gifDao.insertAllHot(apiResponse.result.asDbModelHot())
            }
        }
    }
}