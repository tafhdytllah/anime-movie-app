package com.tafh.animemovieapp.data.repository

import android.util.Log
import androidx.paging.PagingSource
import com.tafh.animemovieapp.api.ApiService
import com.tafh.animemovieapp.data.model.top.Top
import retrofit2.HttpException
import java.io.IOException


private const val STARTING_PAGE_INDEX = 1

class AnimePagingSource(
        private val apiService: ApiService
) : PagingSource<Int, Top>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Top> {
        return try {

            val page = params.key ?: STARTING_PAGE_INDEX
            val response = apiService.getTopList(page)
            var topAnimeList = emptyList<Top>()

            if (response.isSuccessful) {
                topAnimeList = response.body()!!.top
            } else {
                Log.e("LOG", response.message())
            }

            LoadResult.Page(
                    data = topAnimeList,
                    prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                    nextKey = if (topAnimeList.isEmpty()) null else page + 1
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }


}