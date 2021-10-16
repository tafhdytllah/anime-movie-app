package com.tafh.animemovieapp.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tafh.animemovieapp.api.ApiService
import com.tafh.animemovieapp.data.model.Anime
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException


private const val STARTING_PAGE_INDEX = 1

class AnimePagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Anime>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
        return try {
            val currentLoadingPageKey = params.key ?: STARTING_PAGE_INDEX
            val response = apiService.getTopList(currentLoadingPageKey)
            val responseData = mutableListOf<Anime>()
            val data: List<Anime>
            if (response.isSuccessful) {
                data = response.body()?.top!!

                responseData.addAll(data)
            } else {
                Log.d("LOG", "${response.errorBody()}")
            }

            val prevKey = if (currentLoadingPageKey == STARTING_PAGE_INDEX) null else currentLoadingPageKey - 1
            val nextKey = currentLoadingPageKey + 1

            Log.d("CURRENTLOADINGPAGEKEY", "$prevKey")
            Log.d("CURRENTLOADINGPAGEKEY", "${nextKey.minus(1)} POSISI PAGE")
            Log.d("CURRENTLOADINGPAGEKEY", "$nextKey")

            LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (e: IOException) {
            LoadResult.Error(e)

        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Anime>): Int? {
        return state.anchorPosition
    }


}