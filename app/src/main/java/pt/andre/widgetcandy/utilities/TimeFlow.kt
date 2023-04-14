package pt.andre.widgetcandy.utilities

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal fun time(delay: Long = 250L): Flow<Long> = flow {
    while (true) {
        emit(System.currentTimeMillis())
        delay(delay)
    }
}
