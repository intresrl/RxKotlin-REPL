# RxKotlin REPL

This is an example using [RxKotlin](https://github.com/ReactiveX/RxKotlin) to implement a simple [REPL](https://en.wikipedia.org/wiki/Read%E2%80%93eval%E2%80%93print_loop).

The supported commands are:
* `history` (or `hist`)
  * display previously executed commands
  * commands ending with "ck" are excluded (to emulate a profanity filter)
  * consecutive duplicates are displayed only once
* `exit`
  * ends application
* any arithmetic expression using integers and operators `+`, `-`, `*`, `/`
  * display the result of the operation as though it were evaluated in Javascript;
* anything else
  * just echo the command
  
  
There are two executable Kotlin files:
* `Repl.kt` starts the interactive REPL shell;
* `FileRunner.kt` reads a list of commands from file `commands.txt` and executes them in sequence. 