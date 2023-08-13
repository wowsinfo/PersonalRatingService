package service

import KObjectCallback
import PrimitiveCallback
import StringCallback
import kotlinx.cinterop.*
import suspendScope

@OptIn(ExperimentalForeignApi::class)
class RemoteDataServiceNative : RemoteDataService() {

    fun getRemoteString(callback: StringCallback, target: COpaquePointer) = suspendScope {
        val result = getRemoteString()
        callback.invoke(result.cstr, target)
    }

    fun getRemoteData(callback: KObjectCallback, target: COpaquePointer) = suspendScope {
        val remoteData = getRemoteData()
        val ref = StableRef.create(remoteData)
        callback.invoke(ref.asCPointer(), target)
        ref.dispose()
    }
}
