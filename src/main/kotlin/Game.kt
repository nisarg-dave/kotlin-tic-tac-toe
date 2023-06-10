import java.lang.NumberFormatException
import kotlin.system.exitProcess

class Game {
    private var currentPlayer = Piece.X
    private val board = arrayOfNulls<Piece>(9)

    init{
        introScreen()
    }

    private fun introScreen(){
        println("Welcome to Kotlin Tic Tac Toe")
        println("Enter 1 to start the game.")
        println("Enter 2 to quit.")
        print("Enter your choice: ")
        try{
            handleInput(readlnOrNull()?.toInt())
        }
        catch(e: Exception){
            println("\nPlease enter either 1 or 2!")
            introScreen()
        }
    }

    private fun handleInput(int: Int?){
        when(int){
            1 -> startGame()
            2 -> exitGame()
            else -> {
                println("\nPlease enter either 1 or 2!")
                introScreen()
            }
        }
    }

    private fun startGame(){
        println("\nPlayers will alternate turns placing your pieces in one of the 9 cells.")
        println("The cell position is listed as such.")
        println(" 1 | 2 | 3 ")
        println("---+---+---")
        println(" 4 | 5 | 6 ")
        println("---+---+---")
        println(" 7 | 8 | 9 ")
        println("Player 1 is X and Player 2 is 0.")
        print("\nPlayer 1's turn, please enter a cell position from 1 to 9: ")
        gameLogic(readlnOrNull()?.toInt())
    }

    private fun exitGame(){
        exitProcess(0)
    }

    private fun gameLogic(position: Int?){
        if (position !in 1..9){
            print("Please enter a value from 1 to 9: ")
            gameLogic(readlnOrNull()?.toInt())
        }
        if (position != null) {
            if(board[position - 1] != Piece.X &&  board[position - 1] != Piece.O) {
                board[position - 1] = currentPlayer
                printBoard()
                checkWinner()
                changePlayer()


            }
            else{
                print("There's already a piece here! Try another position: ")
                gameLogic(readlnOrNull()?.toInt())
            }
        }
    }

    private fun changePlayer(){
        if(currentPlayer == Piece.X){
            currentPlayer = Piece.O
            print("\nPlayer 2's turn. Select a position: ")
            gameLogic(readlnOrNull()?.toInt())

        }
        else{
            currentPlayer = Piece.X
            print("\nPlayer 1's turn. Select a position: ")
            gameLogic(readlnOrNull()?.toInt())
        }
    }

    private fun printBoard(){
        println(" ${board[0] ?: " "} | ${board[1] ?: " "} | ${board[2] ?: " "} ")
        println("---+---+---")
        println(" ${board[3] ?: " "} | ${board[4] ?: " "} | ${board[5] ?: " "} ")
        println("---+---+---")
        println(" ${board[6] ?: " "} | ${board[7] ?: " "} | ${board[8] ?: " "} ")
    }

    private fun checkWinner(){
        if(board[0] == board[1] && board[0] == board[2] && board[0] != null){
            val winner = if (currentPlayer == Piece.X) "Player 1" else "Player 2"
            println("$winner is the winner!")
        }
    }


}