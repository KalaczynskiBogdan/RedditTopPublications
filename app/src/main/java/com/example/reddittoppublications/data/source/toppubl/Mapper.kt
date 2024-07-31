package com.example.reddittoppublications.data.source.toppubl

import com.example.reddittoppublications.data.source.toppubl.remote.dto.response.ChildrenJson
import com.example.reddittoppublications.data.source.toppubl.remote.dto.response.DataJson
import com.example.reddittoppublications.data.source.toppubl.remote.dto.response.DataXJson
import com.example.reddittoppublications.data.source.toppubl.remote.dto.response.ImageJson
import com.example.reddittoppublications.data.source.toppubl.remote.dto.response.PreviewJson
import com.example.reddittoppublications.data.source.toppubl.remote.dto.response.SourceJson
import com.example.reddittoppublications.data.source.toppubl.remote.dto.response.TopPublicationsResponse
import com.example.reddittoppublications.domain.models.Children
import com.example.reddittoppublications.domain.models.Data
import com.example.reddittoppublications.domain.models.DataX
import com.example.reddittoppublications.domain.models.Image
import com.example.reddittoppublications.domain.models.Preview
import com.example.reddittoppublications.domain.models.Source
import com.example.reddittoppublications.domain.models.TopPublications

fun ChildrenJson.toUi(): Children {
    return Children(
        dataX = (dataX ?: DataXJson()).toUi(),
        kind = kind ?: ""
    )
}

fun DataJson.toUi(): Data {
    return Data(
        after = after ?: "",
        before = before ?: "",
        children = children?.map { it.toUi() } ?: listOf(),
        dist = dist ?: -1,
        geoFilter = geoFilter ?: "",
        modhash = modhash ?: ""
        )
}

fun DataXJson.toUi() : DataX{
    return DataX(
        id = id ?: "",
        author = author ?: "",
        created = created ?: -1.0,
        createdUtc = createdUtc ?: -1.0,
        postHint = postHint ?: "",
        numComments = numComments ?: -1,
        preview = (preview ?: PreviewJson()).toUi(),
        thumbnail = thumbnail ?: "",
        isVideo = isVideo ?: false
        )
}

fun ImageJson.toUi(): Image{
    return Image(
        id = id ?: "",
        source = (source ?: SourceJson()).toUi()
    )
}

fun PreviewJson.toUi() : Preview{
    return Preview(
        enabled = enabled ?: false,
        images = images?.map { it.toUi() } ?: listOf()
    )
}

fun SourceJson.toUi(): Source{
    return Source(
        height = height ?:-1,
        url = url ?: "",
        width = width ?: -1
    )
}

fun TopPublicationsResponse.toUi(): TopPublications{
    return TopPublications(
        data = (data ?: DataJson()).toUi(),
        kind = kind ?: ""
    )
}