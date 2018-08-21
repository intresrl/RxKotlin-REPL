import io.reactivex.Emitter
import io.reactivex.Observable

fun main(args: Array<String>) {
    val observable = Observable.create { emitter: Emitter<String> ->
        try {
            var more = true
            while (more) {
                print("> ")
                val s = readLine()!!
                when (s) {
                    "exit" -> more = false
                    else -> emitter.onNext(s)
                }
            }
            emitter.onComplete()
        } catch (e: Exception) {
            emitter.onError(e)
        }
    }.publish()

    Interpreter().observe(observable)
}

