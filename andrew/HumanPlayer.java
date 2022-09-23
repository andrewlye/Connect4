package andrew;
import java.util.*;

import c4interface.*;

public class HumanPlayer implements Player{

    private String name;
    private Scanner scanner;

    public HumanPlayer(Scanner scanner)
    {
        this.scanner = scanner;
        System.out.print("Enter name: ");
        name = scanner.nextLine();

    }

    @Override
    public int getMoveColumn(Grid g) {
        // TODO Auto-generated method stub
        Board board = new Board(g);
        while (true){
            System.out.print("Choose column for " + name + " (0-6): ");
            int col = scanner.nextInt();
            if (col < 7 && board.isColumnFull(col) == false)
            {
                return col;               
            }
            else{
                System.out.println("Input invalid. Please choose a valid column.");
            }
        }
    }

    @Override
    public String getPlayerName() {
        // TODO Auto-generated method stub
        return name;
    }
    
}
