package com.tafh.animemovieapp.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tafh.animemovieapp.api.ApiService
import com.tafh.animemovieapp.data.model.Anime
import retrofit2.HttpException
import java.io.IOException

private const val DEFAULT_PAGE_INDEX = 1

class SearchPagingSource(
    private val apiService: ApiService,
    private val query: String
) : PagingSource<Int, Anime>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {

        val page = params.key ?: DEFAULT_PAGE_INDEX

        return try {

            val response = apiService.searchAnime(query = query, page = page)
            var animes: List<Anime> = mutableListOf()

            if (response.isSuccessful) {
                animes = response.body()?.results!!
            } else {
                Log.d("LOG", "${response.errorBody()}")
            }

            LoadResult.Page(
                data = animes,
                prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (animes.isEmpty()) null else page + 1
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }
}