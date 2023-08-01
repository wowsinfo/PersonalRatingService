package annotation

//@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class JsExportWithSuspend

@AutoService(SymbolProcessor::class)
class JsExportWithSuspendProcessor {}