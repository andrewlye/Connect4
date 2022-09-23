package andrew;
import c4interface.*;

public class Board implements Grid {

    private int[][] board;
    private int moves;

    public Board(){
        board = new int[6][7];
        moves = 0;
    }

    public Board(Grid g){
        board = new int[6][7];
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[0].length; col ++){
                board[row][col] = g.getPlayerAt(row, col);
            }
        }

    }

    public int getMoves(){
        return moves;
    }

    public int playerNumberToMove()
    {
        int count = 0;
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[0].length; col ++){
                if(board[row][col] == 1 || board[row][col] == 2)
                {
                    count++;
                }
            }
        }
        if (count % 2 == 0)
        {
            return 1;
        }
        else
        {
            return 2;
        }
    }

    public int playerNumberToWait()
    {
        int count = 0;
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[0].length; col ++){
                if(board[row][col] == 1 || board[row][col] == 2)
                {
                    count++;
                }
            }
        }
        if (count % 2 == 0)
        {
            return 2;
        }
        else
        {
            return 1;
        }
    }

    @Override
    public int getPlayerAt(int row, int col) {
        // TODO Auto-generated method stub
        return board[row][col];
    }

    @Override
    public boolean isColumnFull(int col) {
        int count = 0;
        for (int row = board.length - 1; row >= 0; row --)
        {
            if(board[row][col] == 1 || board[row][col] == 2)
            {
                count++;
            }
        }
        if (count == 6)
            return true;
        return false;
    }

    public void drop(int col, int player)
    {
        for (int row = board.length - 1; row >= 0; row --)
        {
            if(board[row][col] == 0)
            {
                board[row][col] = player;
                break;
            }
        }
        moves++;
    }

    public boolean checkWin(int player)
    {
        int count = 0;
        //horizontal check
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                if(board[i][j] == player)
                {
                    for(int k = 1; k <=3; k++)
                    {
                        if(board[i][j+k] == player)
                        {
                            count++;
                        }
                    }
                    if (count == 3)
                        return true;
                    else
                        count = 0;
                }
                
            }
        }


        //vertical check
        for(int col = 0; col < board[0].length; col++)
        {
            for(int row = 5; row >= 3; row--)
            {
                if(board[row][col] == player)
                {
                    for(int i = 1; i <=3; i++)
                    {
                        if(board[row-i][col] == player)
                        {
                            count++;
                        }
                        
                    }
                    if (count == 3)
                        return true;
                    else
                        count = 0;
                }
            }
        }

        //diagonal check

        //left-right from up-down check
        for(int row = 0; row < board.length; row++)
        {
            for(int col = 0; col < board[0].length; col++)
            {
                int down = 0;
                int right = 0;
                int round = 0;
                if(board[row][col] == player)
                {
                    while((row+down) <= 5 && (col+right) <= 6 && round <= 3)
                    {
                        if(board[row+down][col+right] == player)
                        {
                            count++;
                        }
                        down++;
                        right++;
                        round++;
                    }
                    if (count == 4)
                        return true;
                    else
                        count = 0;
                }
            }
        }

        //right-left from up-down check
        for(int row = 0; row < board.length; row++)
        {
            for(int col = 0; col < board[0].length; col++)
            {
                int down=0;
                int left = 0;
                int round = 0;
                if(board[row][col] == player)
                {
                    while((row+down) <= 5 && (col-left) >= 0 && round <= 3)
                    {
                        if(board[row+down][col-left] == player)
                        {
                            count++;
                        }
                        down++;
                        left++;
                        round++;
                    }
                    if (count == 4)
                        return true;
                    else   
                        count = 0;
                }
            }
        }

        return false;
    }

    public boolean checkTie(){

        int total = 0;

        for(int row = 0; row < board.length; row++)
        {
            for(int col = 0; col < board[0].length; col++)
            {
                if(board[row][col] == 1 || board[row][col] == 2)
                {
                    total++;
                }
            }
        }
        if(total == 42)
        {
            return true;
        }
        return false;
    }

    public void printBoard() {

        for(int i = 0; i < board.length; i++)
        {
            System.out.print(5 - i + " | "); 
            for(int j = 0; j < board[0].length; j++)
            {
                System.out.print(board[i][j]);
                System.out.print(" | ");
            }
            System.out.println("");
        }
        System.out.println("   --- --- --- --- --- --- ---");
        System.out.println("    0   1   2   3   4   5   6 ");

    }
    
}
