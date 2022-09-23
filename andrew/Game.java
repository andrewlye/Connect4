package andrew;
import java.util.Scanner;

import c4interface.*;
import sample.SampleRandomPlayer;

public class Game {
    
    public static void main(String[] args)
    {
        Board board = new Board();
        boolean p1turn = true;
        int turnCounter = 0;
        Player player1 = null;
        Player player2 = null;
        Scanner scanner = new Scanner(System.in);

        while(true)
        {
            System.out.print("Please choose player 1 type (random, human, computer): ");
            String p1type = scanner.nextLine();
            if (p1type.equals("human"))
            {
                player1 = new HumanPlayer(scanner);
                break;
            }
            else if (p1type.equals("computer"))
            {
                player1 = new AutomatedPlayer();
                break;
            }
            else if (p1type.equals("random"))
            {
                player1 = new SampleRandomPlayer();
                break;
            }
            else 
            {
                System.out.println("Please enter in a valid player type.");
            }
        }

        while(true)
        {
            System.out.print("Please choose player 2 type (random, human, computer): ");
            String p2type = scanner.nextLine();
            if (p2type.equals("human"))
            {
                player2 = new HumanPlayer(scanner);
                break;
            }
            else if (p2type.equals("computer"))
            {
                player2 = new AutomatedPlayer();
                break;
            }
            else if (p2type.equals("random"))
            {
                player2 = new SampleRandomPlayer();  
                break; 
            }
            else
            {
                System.out.println("Please enter in a valid player type.");
            } 
        }   
        System.out.println("Starting game between player 1 (" + player1.getPlayerName() + ") and player 2 (" + player2.getPlayerName()+")");
        System.out.println();
        board.printBoard();
        System.out.println();
        while(true)
        {
            if (p1turn)
            {
                System.out.println("Player 1 to move.");
                int drop = player1.getMoveColumn(board);
                board.drop(drop, 1);
                turnCounter++;
                System.out.println("Move " + board.getMoves() + ": player 1 moved in column " + drop);
                board.printBoard();
                p1turn = false;
                if (board.checkWin(1))
                    {
                        System.out.println("Player 1 (" + player1.getPlayerName() + ") wins!");
                        break;
                    }
                if (board.checkTie())
                {
                    System.out.println("Tie game!");
                    break;
                }
            }
            else
            {
                System.out.println("Player 2 to move.");
                int drop = player2.getMoveColumn(board);
                board.drop(drop, 2);
                turnCounter++;
                System.out.println("Move " + board.getMoves() + ": player 2 moved in column " + drop);
                board.printBoard();
                p1turn = true;
                if (board.checkWin(2))
                    {
                        System.out.println("Player 2 (" + player2.getPlayerName() + ") wins!");
                        break;
                    }
                if (board.checkTie())
                {
                    System.out.println("Tie game!");
                    break;
                }
            }

        }
    }

}
