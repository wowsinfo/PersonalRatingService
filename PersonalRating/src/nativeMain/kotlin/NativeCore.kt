import kotlinx.cinterop.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@OptIn(ExperimentalForeignApi::class)
typealias KObjectCallback = CPointer<CFunction<(COpaquePointer) -> Unit>>
@OptIn(ExperimentalForeignApi::class)
typealias StringCallback = CPointer<CFunction<(CValuesRef<ByteVar>) -> Unit>>

fun suspendScope(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit,
) {
    CoroutineScope(Dispatchers.Default).launch(context, start, block)
}
