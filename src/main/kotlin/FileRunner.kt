import io.reactivex.rxkotlin.toObservable
import java.io.File

fun main(args: Array<String>) {
    File("commands.txt").useLines {
        sequence -> Interpreter().observe(sequence.toObservable().publish())
    }
}