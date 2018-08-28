import io.reactivex.Emitter
import io.reactivex.Observable

const val PROMPT_STRING = "> "

fun main(args: Array<String>) {
    val observable = Observable.create(emitterFunction).publish()
    Interpreter().observe(observable)
}

private val emitterFunction = { emitter: Emitter<String> ->
    try {
        var shouldAskForCommand = true
        while (shouldAskForCommand) {
            print(PROMPT_STRING)
            val command = readLine()!!
            when (command) {
                "exit" -> shouldAskForCommand = false
                else -> emitter.onNext(command)
            }
        }
        emitter.onComplete()
    } catch (e: Exception) {
        emitter.onError(e)
    }
}


