package labyrinth.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import grid.CellPosition;
import grid.GridCell;
import grid.GridDimension;
import grid.GridDirection;
import labyrinth.controller.ControllableLabyrinthModel;
import labyrinth.model.board.items.BoardPositionFactory;
import labyrinth.model.board.LabyrinthBoard;
import labyrinth.model.board.items.BoardItem;
import labyrinth.model.board.items.LabyrinthTile;
import labyrinth.model.board.items.Monster;
import labyrinth.model.board.items.Player;
import labyrinth.view.ViewableTetrisModel;

public class LabyrinthModel implements ViewableTetrisModel, ControllableLabyrinthModel {

    private LabyrinthBoard board;

    // Board Items
    private BoardItem player;
    private BoardItem monster;

    private int playerScore;
    private GameState gameState;

    public LabyrinthModel(LabyrinthBoard board) {
        this(board, BoardPositionFactory.randomPosition(board), BoardPositionFactory.randomPosition(board));
    }

    public LabyrinthModel(LabyrinthBoard board, CellPosition playerPos, CellPosition monsterPos) {
        this.player = new Player(playerPos);
        this.monster = new Monster(monsterPos);
        this.board = board;
        this.gameState = GameState.ACTIVE_GAME;
    }

    public void moveMonster() {
        CellPosition playerPos = player.getPos();
        CellPosition monsterPos = monster.getPos();
        HashMap<CellPosition, HashSet<CellPosition>> adjacencyList = new HashMap<>();
        addEdges(adjacencyList);
    
        ArrayList<CellPosition> path = bfs(adjacencyList, monsterPos, playerPos);
        
        if (path.size() > 1) {
            CellPosition nextStep = path.get(1); // Get the next step after the current position
            GridDirection dir = monsterPos.getDirectionTowards(nextStep);
    
            if (moveBoardItem(dir, monster)) {
                monster = monster.shiftedBy(dir);
            }
        }
    
        if (monster.getPos().equals(player.getPos())) {
            gameState = GameState.GAME_OVER;
        }
    }

    // Initalize graph, adds edges to each cellPos
    private void addEdges(HashMap<CellPosition, HashSet<CellPosition>> adjacencyList){
        for(int row = 0; row < board.rows(); row++){
            for(int col = 0; col < board.cols(); col++){
                CellPosition currPos = new CellPosition(row, col);
                HashSet<CellPosition> adjacentCells = getAdjacentCells(currPos);
                adjacencyList.put(currPos, adjacentCells);
            }
        }
    }

    private ArrayList<CellPosition> bfs(HashMap<CellPosition, HashSet<CellPosition>> adjacencyList, CellPosition monsterPos, CellPosition playerPos) {
        HashSet<CellPosition> visited = new HashSet<>();
        HashMap<CellPosition, CellPosition> cameFrom = new HashMap<>();
        Queue<CellPosition> q = new LinkedList<>();
        
        visited.add(monsterPos);
        q.add(monsterPos);
        cameFrom.put(monsterPos, null);
    
        while (!q.isEmpty()) {
            CellPosition curr = q.poll();
            
            if (curr.equals(playerPos)) {
                return reconstructPath(cameFrom, playerPos);
            }
            
            HashSet<CellPosition> neighbors = adjacencyList.get(curr);
            if (neighbors != null) {
                for (CellPosition neighbour : neighbors) {
                    if (!visited.contains(neighbour)) {
                        visited.add(neighbour);
                        q.add(neighbour);
                        cameFrom.put(neighbour, curr);
                    }
                }
            }
        }
        
        // If no path is found, return a list containing only the monster's position
        ArrayList<CellPosition> noPath = new ArrayList<>();
        noPath.add(monsterPos);
        return noPath;
    }
    
    private ArrayList<CellPosition> reconstructPath(HashMap<CellPosition, CellPosition> cameFrom, CellPosition goal) {
        ArrayList<CellPosition> path = new ArrayList<>();
        CellPosition current = goal;
        
        while (current != null) {
            path.add(0, current);
            current = cameFrom.get(current);
        }
        
        return path;
    }



    // Gets adjacents cells that do not contain walls
    private HashSet<CellPosition> getAdjacentCells(CellPosition pos) {
        HashSet<CellPosition> adjacentCells = new HashSet<>();
        if(board.get(pos)==LabyrinthTile.WALL.getSymbol()){
            return adjacentCells;
        }

        if (pos.row() > 0 && board.get(new CellPosition(pos.row() - 1, pos.col())) != LabyrinthTile.WALL.getSymbol()) {
            adjacentCells.add(new CellPosition(pos.row() - 1, pos.col()));
        }
        if (pos.row() < board.rows() - 1 && board.get(new CellPosition(pos.row() + 1, pos.col())) != LabyrinthTile.WALL.getSymbol()) {
            adjacentCells.add(new CellPosition(pos.row() + 1, pos.col()));
        }
        if (pos.col() > 0 && board.get(new CellPosition(pos.row(), pos.col() - 1)) != LabyrinthTile.WALL.getSymbol()) {
            adjacentCells.add(new CellPosition(pos.row(), pos.col() - 1));
        }
        if (pos.col() < board.cols() - 1 && board.get(new CellPosition(pos.row(), pos.col() + 1)) != LabyrinthTile.WALL.getSymbol()) {
            adjacentCells.add(new CellPosition(pos.row(), pos.col() + 1));
        }
    
        return adjacentCells;
    }


    @Override
    public void movePlayer(GridDirection dir) {
        if (gameState == GameState.GAME_OVER)
            return;
        if (moveBoardItem(dir, player))
            player = player.shiftedBy(dir);
    }

    private boolean moveBoardItem(GridDirection dir, BoardItem item) {
        BoardItem candidate = item.shiftedBy(dir);
        if (legalMove(candidate)) {
            return true;
        }
        return false;
    }

    private boolean legalMove(BoardItem piece) {
        for (GridCell<Character> cell : piece) {
            if (!board.positionIsOnGrid(cell.pos()))
                return false;
            char charOnBoard = board.get(cell.pos());
            if (charOnBoard == LabyrinthTile.WALL.getSymbol())
                return false;
        }
        return true;
    }

    @Override
    public GridDimension getDimension() {
        return board;
    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard() {
        return board;
    }

    @Override
    public Iterable<GridCell<Character>> getPlayer() {
        return player;
    }

    @Override
    public Iterable<GridCell<Character>> getMonster() {
        return monster;
    }

    @Override
    public int getScore() {
        return playerScore;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public int getTimerDelay() {
        return Math.max(600 - (playerScore * 5), 160);
    }

    @Override
    public void clockTick() {
        if (gameState == GameState.GAME_OVER)
            return;
        moveMonster();
        playerScore++;
    }

    @Override
    public void reset() {
        this.player = new BoardItem(BoardPositionFactory.randomPosition(board), LabyrinthTile.PLAYER);
        this.monster = new BoardItem(BoardPositionFactory.randomPosition(board), LabyrinthTile.MONSTER);

        this.board = LabyrinthBoard.makeRandomBoard(board.rows(), board.cols());
        this.playerScore = 0;

        this.gameState = GameState.ACTIVE_GAME;
    }

}
