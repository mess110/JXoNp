package ro.northpole.jxonp;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ro.northpole.jxonp.exceptions.JXoNpException;

public class GameTest {

	private Game game;
	private final String p1 = "cristi";
	private final String p2 = "felix";

	@Before
	public void setUp() throws Exception {
		game = new Game();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testJoin() {
		game.join(p1);
		assertEquals(false, game.isStarted());
		game.join(p2);
		assertEquals(true, game.isStarted());
	}

	@Test
	public void testMove() throws JXoNpException {
		game.join(p1);
		game.join(p2);

		game.move(p1, 0, 0);
		game.move(p2, 1, 1);
		game.move(p1, 4, 5);
		game.move(p2, 5, 8);
	}
}
