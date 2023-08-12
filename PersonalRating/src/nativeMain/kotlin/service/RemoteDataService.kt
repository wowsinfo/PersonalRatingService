package service

import KObjectCallback
import StringCallback
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.StableRef
import kotlinx.cinterop.cstr
import kotlinx.cinterop.invoke
import suspendScope

@OptIn(ExperimentalForeignApi::class)
class RemoteDataServiceNative : RemoteDataService() {
    fun getRemoteString(callback: StringCallback) = suspendScope {
        val result = getRemoteString()
        callback.invoke(result.cstr)
    }

    fun getRemoteData(callback: KObjectCallback) = suspendScope {
        val remoteData = getRemoteData()
        val ref = StableRef.create(remoteData)
        callback.invoke(ref.asCPointer())
        ref.dispose()
    }
}
