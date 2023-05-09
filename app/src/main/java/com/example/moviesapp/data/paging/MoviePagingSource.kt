package com.example.moviesapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesapp.common.MovieTypeEnum
import com.example.moviesapp.data.api.MovieApiService
import com.example.moviesapp.data.dto.MovieResponseDTO
import okio.IOException
import retrofit2.HttpException

class MoviePagingSource(
    private val api: MovieApiService,
    private val movieTypeEnum: MovieTypeEnum,
    private val query: String = ""
) : PagingSource<Int, MovieResponseDTO>() {
    override fun getRefreshKey(state: PagingState<Int, MovieResponseDTO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponseDTO> {
        val page = params.key ?: 1

        return try {

            val response = when (movieTypeEnum) {
                MovieTypeEnum.POPULAR_MOVIES -> {
                    api.getPopularApi(page)
                }

                MovieTypeEnum.TOP_RATED_MOVIES -> {
                    api.getTopRatedApi(page)
                }

                MovieTypeEnum.SEARCH_MOVIES -> {
                    api.getSearchMovieApi(page, query)
                }
            }

            LoadResult.Page(
                data = response.body()?.results.orEmpty(),
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.body()?.results?.isEmpty() == true) null else page.plus(1)
            )

        } catch (e: IOException) {
            LoadResult.Error(throwable = e)
        } catch (e: HttpException) {
            LoadResult.Error(throwable = e)
        }
    }
}