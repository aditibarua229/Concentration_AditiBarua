/** 
 * A game board of NxM board of tiles.
 * 
 *  @author PLTW
 * @version 2.0
 */

/** 
 * A Board class for concentration
 */
import java.util.*;
public class Board
{  
  private static String[] tileValues = {"lion", "lion",
                                        "penguin", "penguin",
                                        "dolphin", "dolphin",
                                        "fox", "fox",
                                        "monkey", "monkey",
                                        "turtle", "turtle"}; 
  private Tile[][] gameboard = new Tile[3][4];

  /**  
   * Constructor for the game. Creates the 2D gameboard
   * by populating it with card values
   * 
   */
  public Board()
  { 
    // // Populate gameboard
    // gameboard = new Tile[3][4]; // 3 rows, 4 columns
    // int index = 0;
    // for (int i = 0; i < gameboard.length; i++) {
    //     for (int j = 0; j < gameboard[i].length; j++) {
    //         if (index < tileValues.length) {
    //             gameboard[i][j] = new Tile(tileValues[index]);
    //             index++;
    //         }
    //     }
    // }
    //Populate gameboard randomly
    gameboard = new Tile[3][4]; // 3 rows, 4 columns
    List<String> shuffledValues = new ArrayList<>(Arrays.asList(tileValues));
    Collections.shuffle(shuffledValues); // Shuffle values

    int index = 0;
    for (int i = 0; i < gameboard.length; i++) {
        for (int j = 0; j < gameboard[i].length; j++) {
            gameboard[i][j] = new Tile(shuffledValues.get(index));
            index++;
        }
    }
    
    //used for testing
    
    // for (Tile[] row : gameboard) {
    //   for (Tile tile : row) {
    //       System.out.print(tile.getValue() + " ");
    //   }
    //   System.out.println();
    // } 
    

  }

 /** 
   * Returns a string representation of the board, getting the state of
   * each tile. If the tile is showing, displays its value, 
   * otherwise displays it as hidden.
   * 
   * Precondition: gameboard is populated with tiles
   * 
   * @return a string represetation of the board
   */
  //@Override
  public String toString()
  {
    StringBuilder boardString = new StringBuilder();
      for (Tile[] row : gameboard) {
          for (Tile tile : row) {
              if (tile.isShowing()) {
                  boardString.append(tile.getValue()).append("\t");
              } else {
                  boardString.append("_____").append("\t");
              }
          }
          boardString.append("\n");
      }
    return boardString.toString();
    
  }

  /** 
   * Determines if the board is full of tiles that have all been matched,
   * indicating the game is over.
   * 
   * Precondition: gameboard is populated with tiles
   * 
   * @return true if all tiles have been matched, false otherwse
   */
  public boolean allTilesMatch()
  {
    for (Tile[] row : gameboard) {
      for (Tile tile : row) {
          if (!tile.isMatched()) {
              return false;
          }
      }
    }
    return true;
  }

  /** 
   * Sets the tile to show its value (like a playing card face up)
   * 
   * Preconditions:
   *   gameboard is populated with tiles,
   *   row values must be in the range of 0 to gameboard.length,
   *   column values must be in the range of 0 to gameboard[0].length
   * 
   * @param row the row value of Tile
   * @param column the column value of Tile
   */
  public void showValue (int row, int column)
  {
    if (row >= 0 && row < gameboard.length && column >= 0 && column < gameboard[row].length) {
      gameboard[row][column].showValue();
    }
  }  

  /** 
   * Checks if the Tiles in the two locations match.
   * 
   * If Tiles match, show Tiles in matched state and return a "matched" message
   * If Tiles do not match, re-hide Tiles (turn face down).
   * 
   * Preconditions:
   *   gameboard is populated with Tiles,
   *   row values must be in the range of 0 to gameboard.length,
   *   column values must be in the range of 0 to gameboard[0].length
   * 
   * @param row1 the row value of Tile 1
   * @param col1 the column value of Tile 1
   * @param row2 the row value of Tile 2
   * @param col2 the column vlue of Tile 2
   * @return a message indicating whether or not a match occured
   */
  public String checkForMatch(int row1, int col1, int row2, int col2)
  {
    String msg = "Matched";

    Tile first = gameboard[row1][col1];
    Tile second = gameboard[row2][col2];

    if (first.getValue().equals(second.getValue())) {
        first.foundMatch();
        second.foundMatch();
        return msg;
    } else {
        first.hide();
        second.hide();
        return "Not a match";
    }
  }

  /** 
   * Checks the provided values fall within the range of the gameboard's dimension
   * and that the tile has not been previously matched
   * 
   * @param rpw the row value of Tile
   * @param col the column value of Tile
   * @return true if row and col fall on the board and the row,col tile is unmatched, false otherwise
   */
  public boolean validateSelection(int row, int col)
  {
    // Check if row and col are in bounds
    if (row < 0 || row >= gameboard.length || col < 0 || col >= gameboard[row].length) {
      return false; 
    }
    // Check if tile has already been matched
    Tile selectedTile = gameboard[row][col];
    if (selectedTile.isMatched()) {
      return false;
    }

    return true;
  }

}
