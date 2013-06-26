package ro.northpole.jxonp;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ro.northpole.jxonp.util.Const;

public class MiniBoardTest {

	private MiniBoard miniBoard;

	@Before
	public void setUp() throws Exception {
		miniBoard = new MiniBoard();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsFinished() {
		assertEquals(false, miniBoard.isFinished());
	}

	@Test
	public void testIsFinishedXWon() {
		miniBoard.setTile(new Move(0, 0, Const.X));
		miniBoard.setTile(new Move(0, 1, Const.X));
		miniBoard.setTile(new Move(0, 2, Const.X));
		assertEquals(true, miniBoard.isFinished());
	}

	@Test
	public void testIsFinishedXWon2() {
		miniBoard.setTile(new Move(0, 1, Const.X));
		miniBoard.setTile(new Move(1, 1, Const.X));
		miniBoard.setTile(new Move(2, 1, Const.X));
		assertEquals(true, miniBoard.isFinished());
	}

	@Test
	public void testIsFinishedXWonDiagonally() {
		miniBoard.setTile(new Move(0, 0, Const.X));
		miniBoard.setTile(new Move(1, 1, Const.X));
		miniBoard.setTile(new Move(2, 2, Const.X));
		assertEquals(true, miniBoard.isFinished());
	}

	@Test
	public void testFrom() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				assertEquals(0, miniBoard.getTile(i, j));
			}
		}

		int[][] mainTiles = new int[][] {
				{ 0, 1, 2, 0, 0, 0 },
				{ 0, 1, 0, 0, 0, 0 },
				{ 0, 2, 1, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 }};
		miniBoard.from(mainTiles, new Mask(), new Move(1, 1, 0));
		
		assertEquals(0, miniBoard.getTile(0, 0));
		assertEquals(1, miniBoard.getTile(0, 1));
		assertEquals(1, miniBoard.getTile(1, 1));
		
		assertEquals(2, miniBoard.getTile(0, 2));
		assertEquals(1, miniBoard.getTile(2, 2));
	}

}
