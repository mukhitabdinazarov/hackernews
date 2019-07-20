package kz.production.mukhit.hackernews.data.remote

import android.Manifest
import android.arch.persistence.room.Room
import android.content.Context
import android.os.Build
import android.util.Log

class DatabaseClient private constructor(private val mCtx: Context) {

    //our app database object
    val appDatabase: AppDatabase

    init {
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase::class.java, "hacker").allowMainThreadQueries().build()
    }

    companion object {
        private var mInstance: DatabaseClient? = null

        @Synchronized
        fun getInstance(mCtx: Context): DatabaseClient {
            if (mInstance == null) {
                mInstance = DatabaseClient(mCtx)
            }
            return mInstance!!
        }
    }



}