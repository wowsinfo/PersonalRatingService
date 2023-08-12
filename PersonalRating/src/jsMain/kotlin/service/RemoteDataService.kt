package service

import model.RemoteDataSource
import promiseScope
import kotlin.js.Promise

@JsExport
class RemoteDataServiceJs : RemoteDataService() {
    fun getRemoteStringPromise(): Promise<String> = promiseScope {
        getRemoteString()
    }

    fun getRemoteDataPromise(): Promise<RemoteDataSource> = promiseScope {
        getRemoteData()
    }
}
