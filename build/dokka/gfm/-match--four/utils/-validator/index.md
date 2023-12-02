//[Match-Four](../../../index.md)/[utils](../index.md)/[Validator](index.md)

# Validator

[jvm]\
object [Validator](index.md)

This class provides methods for the robust handling of I/O using Scanner. It creates a new Scanner object for each read from the user, thereby eliminating the Scanner bug (where the buffers don't flush correctly after an int read).

The methods also parse the numeric data entered to ensure it is correct. If it isn't correct, the user is prompted to enter it again.

#### Author

Siobhan Drohan, Mairead Meagher

#### Since

1.0

## Functions

| Name | Summary |
|---|---|
| [readNextChar](read-next-char.md) | [jvm]<br>@[JvmStatic](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-static/index.html)<br>fun [readNextChar](read-next-char.md)(prompt: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?): [Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html)<br>Read a single character of text from the user.  There is no validation done on the entered data. |
| [readNextDouble](read-next-double.md) | [jvm]<br>@[JvmStatic](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-static/index.html)<br>fun [readNextDouble](read-next-double.md)(prompt: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Read a double from the user.  If the entered data isn't actually a double, the user is prompted again to enter the double. |
| [readNextInt](read-next-int.md) | [jvm]<br>@[JvmStatic](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-static/index.html)<br>fun [readNextInt](read-next-int.md)(prompt: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Read an int from the user. If the entered data isn't actually an int the user is prompted again to enter the int. |
| [readNextIntWithLimit](read-next-int-with-limit.md) | [jvm]<br>@[JvmStatic](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-static/index.html)<br>fun [readNextIntWithLimit](read-next-int-with-limit.md)(prompt: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, min: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), max: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Reads an integer from the user input with specified limits. |
| [readNextLine](read-next-line.md) | [jvm]<br>@[JvmStatic](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-static/index.html)<br>fun [readNextLine](read-next-line.md)(prompt: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Read a line of text from the user.  There is no validation done on the entered data. |
| [setLocalDate](set-local-date.md) | [jvm]<br>@[JvmStatic](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-static/index.html)<br>fun [setLocalDate](set-local-date.md)(input: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [LocalDate](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html)<br>Parses user input to a LocalDate, with error handling for invalid date formats. |
