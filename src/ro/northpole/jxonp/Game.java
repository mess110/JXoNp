package ro.northpole.jxonp;

import ro.northpole.jxonp.exceptions.GameFinished;
import ro.northpole.jxonp.exceptions.GameNotStarted;
import ro.northpole.jxonp.exceptions.JXoNpException;
import ro.northpole.jxonp.util.Const;

public class Game {

	private String p1, p2;
	private Board board;

	public Game() {
		board = new Board();
	}

	public boolean join(String playerName) {
		if (p1 == null) {
			p1 = playerName;
			return true;
		} else if (p2 == null) {
			p2 = playerName;
			return true;
		} else {
			return false;
		}
	}

	public void move(String playerName, int x, int y) throws JXoNpException {
		if (!isStarted()) {
			throw new GameNotStarted();
		}
		if (isFinished()) {
			throw new GameFinished();
		}
		int kind = playerName.equals(p1) ? Const.X : Const.O;
		board.move(new Move(x, y, kind));
	}

	public boolean isFinished() {
		return board.isFinished();
	}

	public int getWinner() {
		return board.getWinner();
	}

	public boolean isStarted() {
		return p1 != null && p2 != null;
	}
}
