package com.putya.qwotz.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.putya.qwotz.data.FetchedQuotesData
import com.putya.qwotz.data.QuotesData
import com.putya.qwotz.data.RemoteKeys

@Database(
    entities = [QuotesData::class, FetchedQuotesData::class, RemoteKeys::class],
    version = 1
)
abstract class QuotesDatabase : RoomDatabase(){

    abstract fun quotesDao() : QuotesDataDao

    abstract fun getRepoDao() : RemoteKeysDao

}