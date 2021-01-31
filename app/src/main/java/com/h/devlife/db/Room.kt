package com.h.devlife.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GifDao {
    @Query("select * from dblatestgif")
    fun getLatestGifs(): LiveData<List<DbLatestGif>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllLatest(gifs: List<DbLatestGif>)

    @Query("select * from dbtopgif")
    fun getTopGifs(): LiveData<List<DbTopGif>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTop(gifs: List<DbTopGif>)

    @Query("select * from dbhotgif")
    fun getHotGifs(): LiveData<List<DbHotGif>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllHot(gifs: List<DbHotGif>)
}

@Database(entities = [DbLatestGif::class, DbTopGif::class, DbHotGif::class], exportSchema = false, version = 1)
abstract class GifsDatabase : RoomDatabase() {
    abstract val gifDao: GifDao
}


private lateinit var INSTANCE: GifsDatabase

fun getDatabase(context: Context): GifsDatabase {
    synchronized(GifsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                GifsDatabase::class.java,
                "gifs").build()
        }
    }
    return INSTANCE
}