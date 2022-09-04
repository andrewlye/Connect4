package andrew;
import connectFour.*;
import java.util.*;

import javax.lang.model.util.ElementScanner14;

public class AutomatedPlayerVerTwo implements Player{

    private String name = "Andrew's Automated Player";
    private ArrayList<Integer> moves;

    @Override
    public int getMoveColumn(Grid g) {
        // TODO Auto-generated method stub
        moves = new ArrayList<Integer>();
        Board board = new Board(g);
        for (int column = 0; column < Grid.COLS; column++) {
			if (!g.isColumnFull(column)) {
				moves.add(column);
			}
		}

        //search for next win/next lose moves

        if (nextWin(g) > -1)
        {
            return nextWin(g);
        }
        else if (nextLose(g) > -1)
        {
            return nextLose(g);
        }

        //remove moves that gives the other player the win

        for(int i = 0; i < moves.size(); i++)
        {
            if (twoMovesAhead(g, moves.get(i)))
            {
                moves.remove(i);
                i--;
            }
        }

        //block moves that give the opponent two win spaces
        if (twoSpaceWin(g) > -1)
        {
            return twoSpaceWin(g);
        }

        if (twoSpaceLoss(g) > -1)
        {
            return twoSpaceLoss(g);
        }

        // random games

        int player = board.playerNumberToMove();
        int opp = board.playerNumberToWait();
        int highScore = 0;
        int bestMove = 0;

        for (int i = 0; i < moves.size(); i++)
        {
            int score = 0;
            board = new Board(g);
            board.drop(moves.get(i), player);
            Player basic = new AutomatedPlayerBasic();
            boolean p1turn = true;
            if (player == 1)
            {
                p1turn = false;
            }
            for (int j = 0; j < 1000; j++)
            {
                while (board.checkWin(1) || board.checkWin(2) || board.checkTie())
                {
                    if (p1turn)
                    {
                        board.drop(basic.getMoveColumn(board), 1);
                        p1turn = false;
                    }
                    else
                    {
                        board.drop(basic.getMoveColumn(board), 2);
                        p1turn = true;
                    }
                }
                if (board.checkWin(player))
                {
                    score++;
                }
                else if (board.checkWin(opp))
                {
                    score--;
                }
                else if (board.checkTie())
                {

                }
            }
            if (score > highScore)
            {
                highScore = score;
                bestMove = moves.get(i);
            }
        }

        return bestMove;


    }


    private int twoSpaceWin(Grid g)
    {
        Board board = new Board(g);
        int opp = board.playerNumberToMove();
        for(int i = 0; i < moves.size(); i++)
        {
           board = new Board(g);
           board.drop(moves.get(i), opp);
           int count = 0;
           boolean edgesOpen = false;;

           //horizontal check

           for(int row = 0; row < Grid.ROWS; row++){
               for(int col = 1; col < 4; col++){
                   count = 0;
                   edgesOpen = false;
                   if (board.getPlayerAt(row, col) == opp){
                       for(int j = 1; j < 3; j++)
                       {
                            if (board.getPlayerAt(row, col + j) == opp)
                            {
                                count++;
                            }  
                       }
                       if (board.getPlayerAt(row, col - 1) == 0 && board.getPlayerAt(row, (col + 3)) == 0)
                       {
                            edgesOpen = true;
                       }
                   }
                   if (count == 2 && edgesOpen)
                    {
                        return moves.get(i);
                    }

               }
            }
            // no need for vertical check

            /*
            // diagonal check - unfinished, mostly works but a little bugged

            //left to right up to down check

            for(int row = 1; row < Grid.ROWS - 1; row++){
                for (int col = 1; col < Grid.COLS - 1; col++){
                    int down = 0;
                    int right = 0;
                    int round = 0;
                    count = 0;
                    edgesOpen = false;
                    if(board.getPlayerAt(row, col) == opp)
                    {
                        while((row+down) < 4 && (col+right) < 3 && round <= 2)
                        {
                            if(board.getPlayerAt(row + down, col + right) == opp)
                            {
                                count++;
                            }
                            down++;
                            right++;
                            round++;
                        }
                        if(board.getPlayerAt(row + down + 1, col + right + 1) == 0 && board.getPlayerAt(row - 1, col - 1) == 0)
                        {
                            edgesOpen = true;
                        }
                        if (count == 3 && edgesOpen)
                            return moves.get(i);
                    }
                }
            }

            //right to left up to down check

            for(int row = 1; row < Grid.ROWS - 1; row++){
                for (int col = 1; col < Grid.COLS - 1; col++){
                    int down = 0;
                    int left = 0;
                    int round = 0;
                    count = 0;
                    edgesOpen = false;
                    if(board.getPlayerAt(row, col) == opp)
                    {
                        while((row+down) < 4 && (col-left) < 3 && round <= 2)
                        {
                            if(board.getPlayerAt(row + down, col - left) == opp)
                            {
                                count++;
                            }
                            down++;
                            left++;
                            round++;
                        }
                        if(board.getPlayerAt(row + down + 1, col - left - 1) == 0 && board.getPlayerAt(row + 1, col + 1) == 0)
                        {
                            edgesOpen = true;
                        }
                        if (count == 3 && edgesOpen)
                            return moves.get(i);
                    }
                }
            }
            */


           }




        
        return -1;
    }
    private int twoSpaceLoss(Grid g)
    {
        Board board = new Board(g);
        int opp = board.playerNumberToWait();
        for(int i = 0; i < moves.size(); i++)
        {
           board = new Board(g);
           board.drop(moves.get(i), opp);
           int count = 0;
           boolean edgesOpen = false;;

           //horizontal check

           for(int row = 0; row < Grid.ROWS; row++){
               for(int col = 1; col < 4; col++){
                   count = 0;
                   edgesOpen = false;
                   if (board.getPlayerAt(row, col) == opp){
                       for(int j = 1; j < 3; j++)
                       {
                            if (board.getPlayerAt(row, col + j) == opp)
                            {
                                count++;
                            }  
                       }
                       if (board.getPlayerAt(row, col - 1) == 0 && board.getPlayerAt(row, (col + 3)) == 0)
                       {
                            edgesOpen = true;
                       }
                   }
                   if (count == 2 && edgesOpen)
                    {
                        return moves.get(i);
                    }

               }
            }
            // no need for vertical check

            /*
            // diagonal check - unfinished, mostly works but a little bugged

            //left to right up to down check

            for(int row = 1; row < Grid.ROWS - 1; row++){
                for (int col = 1; col < Grid.COLS - 1; col++){
                    int down = 0;
                    int right = 0;
                    int round = 0;
                    count = 0;
                    edgesOpen = false;
                    if(board.getPlayerAt(row, col) == opp)
                    {
                        while((row+down) < 4 && (col+right) < 3 && round <= 2)
                        {
                            if(board.getPlayerAt(row + down, col + right) == opp)
                            {
                                count++;
                            }
                            down++;
                            right++;
                            round++;
                        }
                        if(board.getPlayerAt(row + down + 1, col + right + 1) == 0 && board.getPlayerAt(row - 1, col - 1) == 0)
                        {
                            edgesOpen = true;
                        }
                        if (count == 3 && edgesOpen)
                            return moves.get(i);
                    }
                }
            }

            //right to left up to down check

            for(int row = 1; row < Grid.ROWS - 1; row++){
                for (int col = 1; col < Grid.COLS - 1; col++){
                    int down = 0;
                    int left = 0;
                    int round = 0;
                    count = 0;
                    edgesOpen = false;
                    if(board.getPlayerAt(row, col) == opp)
                    {
                        while((row+down) < 4 && (col-left) < 3 && round <= 2)
                        {
                            if(board.getPlayerAt(row + down, col - left) == opp)
                            {
                                count++;
                            }
                            down++;
                            left++;
                            round++;
                        }
                        if(board.getPlayerAt(row + down + 1, col - left - 1) == 0 && board.getPlayerAt(row + 1, col + 1) == 0)
                        {
                            edgesOpen = true;
                        }
                        if (count == 3 && edgesOpen)
                            return moves.get(i);
                    }
                }
            }
            */


           }




        
        return -1;
    }

    private boolean isColumnOpen(Grid g, int col)
    {
            Board board = new Board(g);
            int count = 0;
            int player = board.playerNumberToMove();
            for (int row = Grid.ROWS - 1; row >= 0; row --)
            {
                if(board.getPlayerAt(row, col) == 0 || board.getPlayerAt(row, col) == player)
                {
                    count++;
                }
                else
                {
                    count = 0;
                }
            }
            if (count >= 4)
                return true;
            else
                return false;
    }

    private int nextWin(Grid g) {
        Board board = new Board(g);
        int player = board.playerNumberToMove();
        
        for(int i = 0; i < moves.size(); i++)
        {
            board = new Board(g);
            board.drop(moves.get(i), player);
            if (board.checkWin(player))
            {
                return moves.get(i);
            }

        }
       
        return -1;
    }

    private boolean twoMovesAhead(Grid g, int col){
        Board board = new Board(g);
        int player = board.playerNumberToMove();
        int opp = board.playerNumberToWait();
        for(int i = 0; i < moves.size(); i++)
        {
            board = new Board(g);
            board.drop(moves.get(i), player);
            board.drop(moves.get(i), opp);
            if (board.checkWin(opp))
            {
                return true;
            }
        }

        return false;
    }

    private int nextLose(Grid g){
        Board board = new Board(g);
        int player = board.playerNumberToWait();
        for(int i = 0; i < moves.size(); i++)
        {
            board = new Board(g);
            board.drop(moves.get(i), player);
            if (board.checkWin(player))
            {
                return moves.get(i);
            }

        }
        return -1;
    }

    @Override
    public String getPlayerName() {
        // TODO Auto-generated method stub
        return name;
    }
    
}
