package com.application.movietime.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.application.movietime.data.local.entity.MovieRemoteKeyEntity

@Dao
interface RemoteKeyDao {

    @Query("SELECT * FROM movie_remotekey_entity WHERE id = :movieId")
    suspend fun getRemoteKeys(movieId: Int): MovieRemoteKeyEntity

    @Upsert
    suspend fun upsertAll(remoteKeys: List<MovieRemoteKeyEntity>)

    @Query("DELETE FROM movie_remotekey_entity")
    suspend fun deleteAllRemoteKeys()
}