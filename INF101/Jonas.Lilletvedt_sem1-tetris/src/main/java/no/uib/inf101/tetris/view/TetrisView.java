
package no.uib.inf101.tetris.view;

import javax.swing.JPanel;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.GameState;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;


public class TetrisView extends JPanel {
  public ViewableTetrisModel tetrisModel;
  private ColorTheme colorTheme;
  

  // Constructor
  public TetrisView(ViewableTetrisModel tetrisModel) {
    this.setFocusable(true);
    this.setPreferredSize(new Dimension(300, 400));

    this.colorTheme = new DefaultColorTheme();
    this.setBackground(colorTheme.getBackgroundColor());
    this.tetrisModel = tetrisModel;


  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    drawGame(g2);
    
    
    // if gameState == GameStat.GAME_OVER 
    if((tetrisModel.getGameState() == GameState.GAME_OVER)){

      // creates a transparent rectangle over the board
      g.setColor(ColorTheme.TRANSPARENT);
      g.fillRect(0, 0, getWidth(), getHeight());

      // Writes Game Over 
      g.setColor(Color.WHITE);
      g.setFont(new Font("Serif", Font.BOLD, 30));
      String gameOverText = "Game Over";
      int textX = (getWidth() - g.getFontMetrics().stringWidth(gameOverText)) / 2;
      int textY = getHeight() / 2;
      g.drawString(gameOverText, textX, textY);
    }
  }


  public void drawGame(Graphics2D g2) {
    double margin = 2;
    double x = margin;
    double y = margin;
    double width = this.getWidth() - 2 * margin;
    double height = this.getHeight() - 2 * margin;
    g2.setColor(colorTheme.getFrameColor());
    Rectangle2D rectangle = new Rectangle2D.Double(x, y, width, height);
    g2.fill(rectangle);
    
    GridDimension gd = tetrisModel.getDimension();
    Iterable<GridCell<Character>> cellsToDraw = tetrisModel.getTilesOnBoard();

    

    CellPositionToPixelConverter cellPositionToPixelConverter = new CellPositionToPixelConverter(rectangle, gd, margin);
    
  
    drawCells(g2, cellsToDraw, cellPositionToPixelConverter, colorTheme);
    drawCells(g2, tetrisModel.getTilesOnFallingTetromino(), cellPositionToPixelConverter, colorTheme);

    
   
  }


public static void drawCells(Graphics2D g2, Iterable<GridCell<Character>> cellNum, CellPositionToPixelConverter cellPositionToPixelConverter, ColorTheme colorTheme){
  for (GridCell<Character> cell : cellNum){
    
    CellPosition cellPosition = cell.pos();
    Rectangle2D cellBounds = cellPositionToPixelConverter.getBoundsForCell(cellPosition);

    Color cellColor = colorTheme.getCellColor(cell.value());


    g2.setColor(cellColor);
    g2.fill(cellBounds);
  }

}


}