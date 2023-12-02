package controllers

import models.Game
import models.Player
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import persistence.JSONSerializer
import java.io.File
import kotlin.test.assertEquals
class MainTest {
    private var player1: Player? = null
    private var player2: Player? = null
    private var player3: Player? = null

    private var game1: Game? = null
    private var game2: Game? = null
    private var game3: Game? = null
    private var game4: Game? = null
    private var game5: Game? = null
    private var game6: Game? = null

    private var populatedGames: GameAPI? = GameAPI(JSONSerializer(File("testGames.json")))
    private var emptyGames: GameAPI? = GameAPI(JSONSerializer(File("testEmptyGames.json")))
    private var populatedPlayers: PlayerAPI? = PlayerAPI(JSONSerializer(File("testPlayers.json")))
    private var emptyPlayers: PlayerAPI? = PlayerAPI(JSONSerializer(File("testEmptyPlayers.json")))

    @BeforeEach
    fun setup() {
        player1 = Player("10000", "Player1_name", 2, arrayListOf("00001", "00002", "00003", "00004", "00005"))
        player2 = Player("20000", "Player2_name", 3, arrayListOf("00001", "00002", "00006"))
        player3 = Player("30000", "Player3_name", 1, arrayListOf("00003", "00004", "00005", "00006"))

        game1 = Game("00001", Array(6, { IntArray(7) }), "Player2_name", 1.0, arrayOf("00001", "00002"))
        game2 = Game("00002", Array(6, { IntArray(7) }), "Player2_name", 2.0, arrayOf("00001", "00002"))
        game3 = Game("00003", Array(6, { IntArray(7) }), "Player1_name", 0.6, arrayOf("00001", "00003"))
        game4 = Game("00004", Array(6, { IntArray(7) }), "Player1_name", 1.5, arrayOf("00001", "00003"))
        game5 = Game("00005", Array(6, { IntArray(7) }), "Player3_name", 1.0, arrayOf("00001", "00003"))
        game6 = Game("00006", Array(6, { IntArray(7) }), "Player2_name", 0.4, arrayOf("00002", "00003"))

        populatedGames!!.addGame(game1!!)
        populatedGames!!.addGame(game2!!)
        populatedGames!!.addGame(game3!!)
        populatedGames!!.addGame(game4!!)
        populatedGames!!.addGame(game5!!)
        populatedGames!!.addGame(game6!!)

        populatedPlayers!!.addPlayer(player1!!)
        populatedPlayers!!.addPlayer(player2!!)
        populatedPlayers!!.addPlayer(player3!!)
    }

    @AfterEach
    fun tearDown() {
        player1 = null
        player2 = null
        player3 = null

        game1 = null
        game2 = null
        game3 = null
        game4 = null
        game5 = null
        game6 = null
    }

    @Nested
    inner class Adding {
        @Test
        fun `Adding a player to playerAPI`() {
            val player4 = Player("40000", "Player4_name", 2, arrayListOf())
            assertEquals(3, populatedPlayers!!.numberOfPlayers())
            populatedPlayers!!.addPlayer(player4)
            assertEquals(4, populatedPlayers!!.numberOfPlayers())
        }

        @Test
        fun `Adding a game to gameAPI`() {
            val game7 = Game("00007", Array(6, { IntArray(7) }), "", 0.0, arrayOf())
            assertEquals(6, populatedGames!!.numberOfGames())
            populatedGames!!.addGame(game7)
            assertEquals(7, populatedGames!!.numberOfGames())
        }
    }

    @Nested
    inner class Listing {
        @Test
        fun `Listing empty games`() {
            assertTrue(emptyGames!!.listAllGames().lowercase().contains("no games recorded."))
        }

        @Test
        fun `Listing empty players`() {
            assertTrue(emptyPlayers!!.listAllPlayers().lowercase().contains("no players recorded."))
        }

        @Test
        fun `Listing games`() {
            val result = populatedGames!!.listAllGames()
            assertTrue(result.contains("Player1_name"))
            assertTrue(result.contains("Player2_name"))
            assertTrue(result.contains("Player3_name"))
        }

        @Test
        fun `Listing players`() {
            val result = populatedPlayers!!.listAllPlayers()
            assertTrue(result.contains("Player1_name"))
            assertTrue(result.contains("Player2_name"))
            assertTrue(result.contains("Player3_name"))
        }
    }

    @Nested
    inner class Deleting {
        @Test
        fun `Deleting non existing game`() {
            assertNull(populatedGames!!.deleteGame(-1))
        }

        @Test
        fun `Deleting non existing player`() {
            assertNull(populatedPlayers!!.deletePlayer(-1))
        }

        @Test
        fun `Deleting game`() {
            assertEquals(6, populatedGames!!.numberOfGames())
            populatedGames!!.deleteGame(5)
            assertEquals(5, populatedGames!!.numberOfGames())
        }

        @Test
        fun `Deleting player`() {
            assertEquals(3, populatedPlayers!!.numberOfPlayers())
            populatedPlayers!!.deletePlayer(2)
            assertEquals(2, populatedPlayers!!.numberOfPlayers())
        }
    }

    @Nested
    inner class Updating {
        @Test
        fun `Updating player name`() {
            assertTrue(populatedPlayers!!.findPlayer(0)!!.playerName == "Player1_name")
            populatedPlayers!!.updatePlayerNickname(0, "newName")
            assertTrue(populatedPlayers!!.findPlayer(0)!!.playerName == "newName")
        }
    }

    @Nested
    inner class PersistenceTests {
        @Test
        fun `saving and loading an empty games collection in JSON doesn't crash app`() {
            val storingGames = GameAPI(JSONSerializer(File("gamesSaved.json")))
            storingGames.save()

            val loadedGames = GameAPI(JSONSerializer(File("gamesSaved.json")))
            loadedGames.load()

            assertEquals(0, storingGames.numberOfGames())
            assertEquals(0, loadedGames.numberOfGames())
        }

        @Test
        fun `saving and loading an empty players collection in JSON doesn't crash app`() {
            val storingPlayers = PlayerAPI(JSONSerializer(File("playersSaved.json")))
            storingPlayers.save()

            val loadedPlayers = PlayerAPI(JSONSerializer(File("gamesSaved.json")))
            loadedPlayers.load()

            assertEquals(0, storingPlayers.numberOfPlayers())
            assertEquals(0, loadedPlayers.numberOfPlayers())
        }

        @Test
        fun `saving and loading an loaded gamescollection in JSON doesn't loose data`() {
            val storingGames = GameAPI(JSONSerializer(File("gamesSaved.json")))
            storingGames.addGame(game1!!)
            storingGames.addGame(game2!!)
            storingGames.addGame(game3!!)
            storingGames.addGame(game4!!)
            storingGames.addGame(game5!!)
            storingGames.addGame(game6!!)
            storingGames.save()

            val loadedGames = GameAPI(JSONSerializer(File("gamesSaved.json")))
            loadedGames.load()
            assertEquals(6, loadedGames.numberOfGames())
            assertEquals(6, storingGames.numberOfGames())
        }

        @Test
        fun `saving and loading an loaded player collection in JSON doesn't loose data`() {
            val storingPlayers = PlayerAPI(JSONSerializer(File("playersSaved.json")))
            storingPlayers.addPlayer(player1!!)
            storingPlayers.addPlayer(player2!!)
            storingPlayers.addPlayer(player3!!)
            storingPlayers.save()

            val loadedPlayers = PlayerAPI(JSONSerializer(File("playersSaved.json")))
            loadedPlayers.load()
            assertEquals(3, loadedPlayers.numberOfPlayers())
            assertEquals(3, storingPlayers.numberOfPlayers())
        }
    }
}
