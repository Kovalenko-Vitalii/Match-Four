//[Match-Four](../../../index.md)/[controllers](../index.md)/[GameAPI](index.md)

# GameAPI

[jvm]\
class [GameAPI](index.md)(serializerType: [Serializer](../../persistence/-serializer/index.md))

## Constructors

| | |
|---|---|
| [GameAPI](-game-a-p-i.md) | [jvm]<br>constructor(serializerType: [Serializer](../../persistence/-serializer/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [addGame](add-game.md) | [jvm]<br>fun [addGame](add-game.md)(game: [Game](../../models/-game/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [addId](add-id.md) | [jvm]<br>fun [addId](add-id.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [chechkId](chechk-id.md) | [jvm]<br>fun [chechkId](chechk-id.md)(newId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [deleteGame](delete-game.md) | [jvm]<br>fun [deleteGame](delete-game.md)(indexToDelete: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Game](../../models/-game/index.md)? |
| [generateID](generate-i-d.md) | [jvm]<br>fun [generateID](generate-i-d.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getGameById](get-game-by-id.md) | [jvm]<br>fun [getGameById](get-game-by-id.md)(index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Game](../../models/-game/index.md)? |
| [isValidIndexGames](is-valid-index-games.md) | [jvm]<br>fun [isValidIndexGames](is-valid-index-games.md)(index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [listAllGames](list-all-games.md) | [jvm]<br>fun [listAllGames](list-all-games.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [listGamesByTime](list-games-by-time.md) | [jvm]<br>fun [listGamesByTime](list-games-by-time.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [load](load.md) | [jvm]<br>fun [load](load.md)() |
| [numberOfGames](number-of-games.md) | [jvm]<br>fun [numberOfGames](number-of-games.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [save](save.md) | [jvm]<br>fun [save](save.md)() |
| [searchGameByTitle](search-game-by-title.md) | [jvm]<br>fun [searchGameByTitle](search-game-by-title.md)(searchString: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
