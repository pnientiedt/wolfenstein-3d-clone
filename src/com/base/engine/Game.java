package com.base.engine;

public class Game {

	private static Level level;
	private static boolean isRunning;

	public Game() {
		Player player = new Player(new Vector3f(13, 0.4375f, 13));
		level = new Level("level1.png", "WolfCollection.png", player);

		Transform.setCamera(player.getCamera());
		Transform.setProjection(70, Window.getWidth(), Window.getHeight(), 0.01f, 1000f);
		isRunning = true;
	}

	public void input() {
		level.input();
	}

	public void update() {
		if (isRunning)
			level.update();
	}

	public void render() {
		if (isRunning)
			level.render();
	}
	
	public static Level getLevel() {
		return level;
	}
	
	public static void setIsRunning(boolean value) {
		isRunning = value;
	}
}
