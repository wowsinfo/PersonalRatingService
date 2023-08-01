package service

import model.RemoteDataSource
import util.promise
import kotlin.js.Promise

@JsExport
fun RemoteDataService.getRemoteString(): Promise<String> = promise {
    getRemoteString()
}

@JsExport
fun RemoteDataService.getRemoteData(): Promise<RemoteDataSource> = promise {
    getRemoteData()
}
