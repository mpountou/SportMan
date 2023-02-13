package com.intelligent.sportman.model

import com.squareup.moshi.Json

/**
 * Used to convert api json response to kotlin data class
 */

data class SportEvent(
    @Json(name = "i") val id: String,
    @Json(name = "d") val name: String,
    @Json(name = "si") val sportId: String,
    @Json(name = "tt") val startTime: Long,
)

/**
    {
        "d":"Juventus FC - Paris Saint-Germain",
        "i":"29135390",
        "si":"FOOT",
        "sh":"Juventus FC - Paris Saint-Germain",
        "tt":1667447160
    }
 */