//[Match-Four](../../../index.md)/[controllers](../index.md)/[PlayerAPI](index.md)

# PlayerAPI

[jvm]\
class [PlayerAPI](index.md)(serializerType: [Serializer](../../persistence/-serializer/index.md))

## Constructors

| | |
|---|---|
| [PlayerAPI](-player-a-p-i.md) | [jvm]<br>constructor(serializerType: [Serializer](../../persistence/-serializer/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [addId](add-id.md) | [jvm]<br>fun [addId](add-id.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [addPlayer](add-player.md) | [jvm]<br>fun [addPlayer](add-player.md)(player: [Player](../../models/-player/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [chechkId](chechk-id.md) | [jvm]<br>fun [chechkId](chechk-id.md)(newId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [deletePlayer](delete-player.md) | [jvm]<br>fun [deletePlayer](delete-player.md)(indexToDelete: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Player](../../models/-player/index.md)? |
| [findPlayer](find-player.md) | [jvm]<br>fun [findPlayer](find-player.md)(index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Player](../../models/-player/index.md)? |
| [findPlayerById](find-player-by-id.md) | [jvm]<br>fun [findPlayerById](find-player-by-id.md)(playerId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Player](../../models/-player/index.md)? |
| [generateID](generate-i-d.md) | [jvm]<br>fun [generateID](generate-i-d.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [isValidIndexPlayers](is-valid-index-players.md) | [jvm]<br>fun [isValidIndexPlayers](is-valid-index-players.md)(index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [listAllPlayers](list-all-players.md) | [jvm]<br>fun [listAllPlayers](list-all-players.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [listPlayersByGamesPlayed](list-players-by-games-played.md) | [jvm]<br>fun [listPlayersByGamesPlayed](list-players-by-games-played.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [listPlayersByVictoriesAmount](list-players-by-victories-amount.md) | [jvm]<br>fun [listPlayersByVictoriesAmount](list-players-by-victories-amount.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [load](load.md) | [jvm]<br>fun [load](load.md)() |
| [numberOfPlayers](number-of-players.md) | [jvm]<br>fun [numberOfPlayers](number-of-players.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [save](save.md) | [jvm]<br>fun [save](save.md)() |
| [searchPlayerByTitle](search-player-by-title.md) | [jvm]<br>fun [searchPlayerByTitle](search-player-by-title.md)(searchString: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [updatePlayerNickname](update-player-nickname.md) | [jvm]<br>fun [updatePlayerNickname](update-player-nickname.md)(indexToUpdate: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), newName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
