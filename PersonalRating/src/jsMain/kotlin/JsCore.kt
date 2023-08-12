import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.promise
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.js.Promise

/**
 * Create a [Promise] that wraps the coroutine started by this builder and returns its result
 */
fun <T> promiseScope(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T,
): Promise<T> {
    return CoroutineScope(Dispatchers.Default).promise(
        context, start, block
    )
}
