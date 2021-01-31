package com.h.devlife.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dblatestgif")
data class DbLatestGif(
    @PrimaryKey
    val id: Int,
    val description: String,
    val votes: Int,
    val author: String,
    val date: String,
    val gifURL: String,
    val gifSize: Int,
    val previewURL: String,
    val videoURL: String,
    val videoPath: String,
    val videoSize: Int,
    val type: String,
    val width: Int,
    val height: Int,
    val commentsCount: Int,
    val fileSize: Int,
    val canVote: Boolean
)

@Entity(tableName = "dbhotgif")
data class DbHotGif(
    @PrimaryKey
    val id: Int,
    val description: String,
    val votes: Int,
    val author: String,
    val date: String,
    val gifURL: String,
    val gifSize: Int,
    val previewURL: String,
    val videoURL: String,
    val videoPath: String,
    val videoSize: Int,
    val type: String,
    val width: Int,
    val height: Int,
    val commentsCount: Int,
    val fileSize: Int,
    val canVote: Boolean
)

@Entity(tableName = "dbtopgif")
data class DbTopGif(
    @PrimaryKey
    val id: Int,
    val description: String,
    val votes: Int,
    val author: String,
    val date: String,
    val gifURL: String,
    val gifSize: Int,
    val previewURL: String,
    val videoURL: String,
    val videoPath: String,
    val videoSize: Int,
    val type: String,
    val width: Int,
    val height: Int,
    val commentsCount: Int,
    val fileSize: Int,
    val canVote: Boolean
)