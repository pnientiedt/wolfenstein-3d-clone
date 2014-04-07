package com.base.engine;

public class Game {

	private static Level level;
	private static boolean isRunning;
	private static int levelNum = 0;

	public Game() {
//		Player player = new Player(new Vector3f(13, 0.4375f, 13));
		loadNextLevel();
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
	
	public static void loadNextLevel() {
		levelNum++;
		level = new Level("level" + levelNum + ".png", "WolfCollection.png");
		
		Transform.setCamera(level.getPlayer().getCamera());
		Transform.setProjection(70, Window.getWidth(), Window.getHeight(), 0.01f, 1000f);
		isRunning = true;
	}
	
	public static Level getLevel() {
		return level;
	}
	
	public static void setIsRunning(boolean value) {
		isRunning = value;
	}
}
