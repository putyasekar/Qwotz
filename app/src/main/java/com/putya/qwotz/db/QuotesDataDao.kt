package com.putya.qwotz.db

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.putya.qwotz.data.FetchedQuotesData
import com.putya.qwotz.data.QuotesData

@Dao
interface QuotesDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote : QuotesData) : Long

    @Query("SELECT * FROM SavedQuotes")
    suspend fun getAllSavedQuotes() :List<QuotesData>

    @Query("DELETE FROM SavedQuotes WHERE quoteText = :quote")
    suspend fun deleteQuote(quote : String)

    @Query("SELECT * FROM SavedQuotes")
     fun getSavedQuotes() : LiveData<List<QuotesData>>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFetchedQuote(quote : List<FetchedQuotesData>)

    @Query("SELECT * FROM fetched_quotes")
    fun getAllFetchedQuotes() : PagingSource<Int, FetchedQuotesData>

    @Update
    suspend fun updateFetchedQuote(data : FetchedQuotesData)

    @Query("DELETE FROM fetched_quotes")
    suspend fun deleteFetchedQuote()


}