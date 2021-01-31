package com.h.devlife.extensions

import com.h.devlife.data.Gif
import com.h.devlife.data.ResultGif
import com.h.devlife.db.DbHotGif
import com.h.devlife.db.DbLatestGif
import com.h.devlife.db.DbTopGif

fun List<DbLatestGif>.fromLatestToDomainModel(): List<Gif> {
    return map {
        Gif(
            id = it.id,
            description = it.description,
            votes = it.votes ,
            author = it.author,
            date = it.date,
            gifURL = it.gifURL,
            gifSize = it.gifSize,
            previewURL = it.previewURL,
            videoURL = it.videoURL,
            videoPath = it.videoPath,
            videoSize = it.videoSize,
            type = it.type,
            width = it.width,
            height = it.height,
            commentsCount = it.commentsCount,
            fileSize = it.fileSize,
            canVote = it.canVote
        )
    }
}

fun List<DbTopGif>.fromTopToDomainModel(): List<Gif> {
    return map {
        Gif(
            id = it.id,
            description = it.description,
            votes = it.votes ,
            author = it.author,
            date = it.date,
            gifURL = it.gifURL,
            gifSize = it.gifSize,
            previewURL = it.previewURL,
            videoURL = it.videoURL,
            videoPath = it.videoPath,
            videoSize = it.videoSize,
            type = it.type,
            width = it.width,
            height = it.height,
            commentsCount = it.commentsCount,
            fileSize = it.fileSize,
            canVote = it.canVote
        )
    }
}
fun List<DbHotGif>.fromHotToDomainModel(): List<Gif> {
    return map {
        Gif(
            id = it.id,
            description = it.description,
            votes = it.votes ,
            author = it.author,
            date = it.date,
            gifURL = it.gifURL,
            gifSize = it.gifSize,
            previewURL = it.previewURL,
            videoURL = it.videoURL,
            videoPath = it.videoPath,
            videoSize = it.videoSize,
            type = it.type,
            width = it.width,
            height = it.height,
            commentsCount = it.commentsCount,
            fileSize = it.fileSize,
            canVote = it.canVote
        )
    }
}


fun List<ResultGif>.asDbModelLatest(): List<DbLatestGif> {
    return map {
        DbLatestGif(
            id = it.id,
            description = it.description,
            votes = it.votes ,
            author = it.author,
            date = it.date,
            gifURL = it.gifURL,
            gifSize = it.gifSize,
            previewURL = it.previewURL,
            videoURL = it.videoURL,
            videoPath = it.videoPath,
            videoSize = it.videoSize,
            type = it.type,
            width = it.width,
            height = it.height,
            commentsCount = it.commentsCount,
            fileSize = it.fileSize,
            canVote = it.canVote
        )
    }
}
fun List<ResultGif>.asDbModelTop(): List<DbTopGif> {
    return map {
        DbTopGif(
            id = it.id,
            description = it.description,
            votes = it.votes ,
            author = it.author,
            date = it.date,
            gifURL = it.gifURL,
            gifSize = it.gifSize,
            previewURL = it.previewURL,
            videoURL = it.videoURL,
            videoPath = it.videoPath,
            videoSize = it.videoSize,
            type = it.type,
            width = it.width,
            height = it.height,
            commentsCount = it.commentsCount,
            fileSize = it.fileSize,
            canVote = it.canVote
        )
    }
}
fun List<ResultGif>.asDbModelHot(): List<DbHotGif> {
    return map {
        DbHotGif(
            id = it.id,
            description = it.description,
            votes = it.votes ,
            author = it.author,
            date = it.date,
            gifURL = it.gifURL,
            gifSize = it.gifSize,
            previewURL = it.previewURL,
            videoURL = it.videoURL,
            videoPath = it.videoPath,
            videoSize = it.videoSize,
            type = it.type,
            width = it.width,
            height = it.height,
            commentsCount = it.commentsCount,
            fileSize = it.fileSize,
            canVote = it.canVote
        )
    }
}