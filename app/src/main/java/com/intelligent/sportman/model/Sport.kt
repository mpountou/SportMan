package com.intelligent.sportman.model

import com.squareup.moshi.Json


/**
 * Used to convert api json response to kotlin data class
 */

data class Sport(
    @Json(name="i") val id: String,
    @Json(name="d") val name: String,
    @Json(name="e") var sportEvents: List<SportEvent>,
    var favoriteSportEvents: List<SportEvent> = listOf(),
)

/**
         * [
        {
            "i":"FOOT",
            "d":"SOCCER",
            "e":[ .. ]
        }
        ]
 */