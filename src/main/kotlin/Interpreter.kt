import io.reactivex.observables.ConnectableObservable
import javax.script.ScriptEngineManager

class Interpreter {

    fun observe(observable: ConnectableObservable<String>) {
        // echo everything
        observable
                .filter { it.isNotBlank() }
                .subscribe(::echo, ::error, ::end)

        // censored history (everything ending with "ck" is a bad word)
        observable
                .filter { !it.toLowerCase().endsWith("ck") }
                .distinctUntilChanged()
                .subscribe(historian(), ::error)

        // execute arithmetic operations
        val jsEngine = ScriptEngineManager().getEngineByName("js")
        observable
                .filter { it.matches(Regex("(\\s*[-+]?\\d+\\s*[-+*/])*\\s*[-+]?\\d+\\s*")) }
                .map { jsEngine.eval(it) }
                .subscribe { println(" = $it") }

        observable.connect()
    }

    private fun echo(it: String?) {
        println(" & $it")
    }

    private fun error(it: Throwable) {
        it.printStackTrace()
    }

    private fun end() {
        println("Done!")
    }

    private fun historian(): (String) -> Unit {
        val history = mutableListOf<String>()
        return {
            when (it) {
                "history", "hist" -> println(" Previous commands: $history")
                else -> history.add(it)
            }
        }
    }


}