package com.example.moviesapp.data.source.local

import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val movieDao: MovieDao) : LocalDataSource {
}