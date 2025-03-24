package no.uib.inf101.tetris.view;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;
import java.awt.geom.Rectangle2D;


// copied from lab4
public class CellPositionToPixelConverter {

  private Rectangle2D box;
  private GridDimension gd;
  private double margin;

  public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin) {
    this.box = box;
    this.gd = gd;
    this.margin = margin;
  }

  public Rectangle2D getBoundsForCell(CellPosition cp){
    double cellWidth = (box.getWidth() - (gd.cols() + 1) * margin) / gd.cols();
    double cellHeight = (box.getHeight() - (gd.rows() + 1) * margin) / gd.rows();

    double cellX = box.getMinX() + margin + cp.col() * (cellWidth + margin);
    double cellY = box.getMinY() + margin + cp.row() * (cellHeight + margin);

    return new Rectangle2D.Double(cellX, cellY, cellWidth, cellHeight);
  }

    
}