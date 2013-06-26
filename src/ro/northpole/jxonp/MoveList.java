package ro.northpole.jxonp;

import java.util.ArrayList;

public class MoveList extends ArrayList<Move> {

	private static final long serialVersionUID = -2273281584602946700L;

	public MoveList() {
		super();
	}

	public Move getLastMove() {
		return get(size() - 1);
	}
}
