package com.tafh.animemovieapp.data.repository

import android.util.Log
import androidx.paging.PagingSource
import com.tafh.animemovieapp.api.ApiService
import com.tafh.animemovieapp.data.model.Anime
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class AnimeGenrePagingSource(
        private val apiService: ApiService,
        private val genreId: Int
) : PagingSource<Int, Anime>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
        return try {

            val currentLoadingPageKey = params.key ?: STARTING_PAGE_INDEX
            val response = apiService.getGenreList(genreId = genreId, page = currentLoadingPageKey)
            val responseData = mutableListOf<Anime>()
            val data : List<Anime>
            if (response.isSuccessful) {
                data = response.body()?.animeGenres!!
                responseData.addAll(data)
            } else {
                Log.d("LOG", "${response.errorBody()}")
            }

            val prevKey = if (currentLoadingPageKey == STARTING_PAGE_INDEX) null else currentLoadingPageKey - 1
            val nextKey = currentLoadingPageKey + 1

            LoadResult.Page(
                    data = responseData,
                    prevKey = prevKey,
                    nextKey = nextKey
            )
        }
        catch (e: IOException) {
            LoadResult.Error(e)
        }
        catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}