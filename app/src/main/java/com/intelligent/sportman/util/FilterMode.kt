package com.intelligent.sportman.util

import com.intelligent.sportman.model.SportEvent
import kotlin.streams.toList

/**
 * Three available filter modes:
 * ALL: contains all sport events fetched by the mock api
 * UPCOMING: contains all sport events that hasn't started yet
 * LIKED: contains all sport events that user likes
 */
enum class Mode {
    ALL,
    UPCOMING,
    LIKED
}

/**
 * Updates the sport event list based on the selected filter mode
 */
fun filterMode(
    action: Mode,
    sportEvents: List<SportEvent>,
    isLikedList: Boolean = false
): List<SportEvent> {

    when (action) {
        Mode.ALL -> {
            return sportEvents
        }
        Mode.UPCOMING -> {
            return sportEvents.stream().filter { it.startTime > System.currentTimeMillis() / 1000 }
                .toList().sortedBy { it.startTime }
        }
        Mode.LIKED -> {
            if (!isLikedList) return listOf()
        }
    }
    return sportEvents
}