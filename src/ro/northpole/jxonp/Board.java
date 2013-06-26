package ro.northpole.jxonp;

import ro.northpole.jxonp.exceptions.CanNotMoveThere;
import ro.northpole.jxonp.exceptions.InvalidBoardCoordinates;
import ro.northpole.jxonp.exceptions.JXoNpException;
import ro.northpole.jxonp.exceptions.NotYourTurn;
import ro.northpole.jxonp.util.Const;

public class Board {

	private MiniBoard miniBoard;
	private int[][] tiles;
	private MoveList moves;
	private Mask mask;

	public Board() {
		tiles = new int[9][9];
		moves = new MoveList();
		mask = new Mask();
		miniBoard = new MiniBoard();
	}

	public int whosTurnIsIt() {
		return moves.size() % 2 == 0 ? Const.X : Const.O;
	}

	public void move(Move m) throws JXoNpException {
		if (!isValidCoordinates(m)) {
			throw new InvalidBoardCoordinates();
		}
		if (!isCorrectPlayerMoving(m)) {
			throw new NotYourTurn();
		}
		if (!isEmptyPosition(m)) {
			throw new CanNotMoveThere();
		}
		if (!isCorrectMovePosition(m)) {
			throw new CanNotMoveThere();
		}

		tiles[m.x][m.y] = m.kind;
		moves.add(m);

		MiniBoard mB = new MiniBoard();
		mB.from(tiles, mask, m);
		if (!mB.isFinished()) {
			Move tmpMove = new Move(m.x % 3, m.y % 3, m.kind);
			miniBoard.setTile(tmpMove);
		}
	}

	public int getWinner() {
		return miniBoard.getWinner();
	}

	public int getTile(int x, int y) throws JXoNpException {
		if (!isValidCoordinates(x, y)) {
			throw new InvalidBoardCoordinates();
		}
		return tiles[x][y];
	}

	private boolean isCorrectMovePosition(Move m) {
		if (!moves.isEmpty()) {
			MoveList allowedMoves = mask.getAllowedMovesAfter(moves
					.getLastMove());
			Move tmpMove = new Move(m.x, m.y, Const.NONE);

			if (!allowedMoves.contains(tmpMove)) {
				return false;
			}
		}
		return true;
	}

	private boolean isEmptyPosition(Move m) {
		return tiles[m.x][m.y] == Const.NONE;
	}

	private boolean isCorrectPlayerMoving(Move m) {
		return m.kind == whosTurnIsIt();
	}

	private boolean isValidCoordinates(Move m) {
		return isValidCoordinates(m.x, m.y);
	}

	private boolean isValidCoordinates(int x, int y) {
		return (0 <= x && x < 9 && 0 <= y && y < 9);
	}

	public boolean isFinished() {
		return miniBoard.isFinished();
	}
}
