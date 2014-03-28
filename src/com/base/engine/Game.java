package com.base.engine;

import java.util.ArrayList;

public class Game {
	
	Bitmap level;
	Shader shader;
	Material material;
	Mesh mesh;
	Transform transform;
	
	private static final float SPOT_WIDTH = 1;
	private static final float SPOT_LENGTH = 1;
	private static final float SPOT_HEIGHT = 1;

	public Game() {
		level = new Bitmap("levelTest.png").flipY();
		
		for (int i = 0; i < level.getWidth(); i++) {
			for (int j = 0; j < level.getHeight(); j++) {
				System.out.print(level.getPixel(i, j));
			}
			System.out.println();
		}
		
		shader = BasicShader.getInstance();
		material = new Material(new Texture("test.png"));
		
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		
		for (int i = 0; i < level.getWidth(); i++) {
			for (int j = 0; j < level.getHeight(); j++) {
				if ((level.getPixel(i, j) & 0xFFFFFF) == 0)
					continue;
				
				float xHigher = 1;
				float xLower = 0;
				float yHigher = 1;
				float yLower = 0;
				
				//Generate Floor
				indices.add(vertices.size() + 2);
				indices.add(vertices.size() + 1);
				indices.add(vertices.size() + 0);
				indices.add(vertices.size() + 3);
				indices.add(vertices.size() + 2);
				indices.add(vertices.size() + 0);
				
				vertices.add(new Vertex(new Vector3f(i * SPOT_WIDTH,0,j * SPOT_LENGTH), new Vector2f(xLower,yLower)));
				vertices.add(new Vertex(new Vector3f((i + 1) * SPOT_WIDTH,0,j * SPOT_LENGTH), new Vector2f(xHigher,yLower)));
				vertices.add(new Vertex(new Vector3f((i + 1) * SPOT_WIDTH,0,(j+1) * SPOT_LENGTH), new Vector2f(xHigher,yHigher)));
				vertices.add(new Vertex(new Vector3f(i * SPOT_WIDTH,0,(j+1) * SPOT_LENGTH), new Vector2f(xLower,yHigher)));
			
				//Generate Ceiling
				indices.add(vertices.size() + 0);
				indices.add(vertices.size() + 1);
				indices.add(vertices.size() + 2);
				indices.add(vertices.size() + 0);
				indices.add(vertices.size() + 2);
				indices.add(vertices.size() + 3);
				
				vertices.add(new Vertex(new Vector3f(i * SPOT_WIDTH,SPOT_HEIGHT,j * SPOT_LENGTH), new Vector2f(xLower,yLower)));
				vertices.add(new Vertex(new Vector3f((i + 1) * SPOT_WIDTH,SPOT_HEIGHT,j * SPOT_LENGTH), new Vector2f(xHigher,yLower)));
				vertices.add(new Vertex(new Vector3f((i + 1) * SPOT_WIDTH,SPOT_HEIGHT,(j+1) * SPOT_LENGTH), new Vector2f(xHigher,yHigher)));
				vertices.add(new Vertex(new Vector3f(i * SPOT_WIDTH,SPOT_HEIGHT,(j+1) * SPOT_LENGTH), new Vector2f(xLower,yHigher)));
				
				//Generate Walls
				if ((level.getPixel(i, j-1) & 0xFFFFFF) == 0) {
					indices.add(vertices.size() + 0);
					indices.add(vertices.size() + 1);
					indices.add(vertices.size() + 2);
					indices.add(vertices.size() + 0);
					indices.add(vertices.size() + 2);
					indices.add(vertices.size() + 3);
					
					vertices.add(new Vertex(new Vector3f(i * SPOT_WIDTH,0,j * SPOT_LENGTH), new Vector2f(xLower,yLower)));
					vertices.add(new Vertex(new Vector3f((i + 1) * SPOT_WIDTH,0,j * SPOT_LENGTH), new Vector2f(xHigher,yLower)));
					vertices.add(new Vertex(new Vector3f((i + 1) * SPOT_WIDTH,SPOT_HEIGHT,j * SPOT_LENGTH), new Vector2f(xHigher,yHigher)));
					vertices.add(new Vertex(new Vector3f(i * SPOT_WIDTH,SPOT_HEIGHT,j * SPOT_LENGTH), new Vector2f(xLower,yHigher)));
				}
				if ((level.getPixel(i, j+1) & 0xFFFFFF) == 0) {
					indices.add(vertices.size() + 2);
					indices.add(vertices.size() + 1);
					indices.add(vertices.size() + 0);
					indices.add(vertices.size() + 3);
					indices.add(vertices.size() + 2);
					indices.add(vertices.size() + 0);
					
					vertices.add(new Vertex(new Vector3f(i * SPOT_WIDTH,0,(j+1) * SPOT_LENGTH), new Vector2f(xLower,yLower)));
					vertices.add(new Vertex(new Vector3f((i + 1) * SPOT_WIDTH,0,(j+1) * SPOT_LENGTH), new Vector2f(xHigher,yLower)));
					vertices.add(new Vertex(new Vector3f((i + 1) * SPOT_WIDTH,SPOT_HEIGHT,(j+1) * SPOT_LENGTH), new Vector2f(xHigher,yHigher)));
					vertices.add(new Vertex(new Vector3f(i * SPOT_WIDTH,SPOT_HEIGHT,(j+1) * SPOT_LENGTH), new Vector2f(xLower,yHigher)));
				}
				if ((level.getPixel(i-1, j) & 0xFFFFFF) == 0) {
					indices.add(vertices.size() + 2);
					indices.add(vertices.size() + 1);
					indices.add(vertices.size() + 0);
					indices.add(vertices.size() + 3);
					indices.add(vertices.size() + 2);
					indices.add(vertices.size() + 0);
					
					vertices.add(new Vertex(new Vector3f(i * SPOT_WIDTH,0,j * SPOT_LENGTH), new Vector2f(xLower,yLower)));
					vertices.add(new Vertex(new Vector3f(i * SPOT_WIDTH,0,(j+1) * SPOT_LENGTH), new Vector2f(xHigher,yLower)));
					vertices.add(new Vertex(new Vector3f(i * SPOT_WIDTH,SPOT_HEIGHT,(j+1) * SPOT_LENGTH), new Vector2f(xHigher,yHigher)));
					vertices.add(new Vertex(new Vector3f(i * SPOT_WIDTH,SPOT_HEIGHT,j * SPOT_LENGTH), new Vector2f(xLower,yHigher)));
				}
				if ((level.getPixel(i+1, j) & 0xFFFFFF) == 0) {
					indices.add(vertices.size() + 0);
					indices.add(vertices.size() + 1);
					indices.add(vertices.size() + 2);
					indices.add(vertices.size() + 0);
					indices.add(vertices.size() + 2);
					indices.add(vertices.size() + 3);
					
					vertices.add(new Vertex(new Vector3f((i+1) * SPOT_WIDTH,0,j * SPOT_LENGTH), new Vector2f(xLower,yLower)));
					vertices.add(new Vertex(new Vector3f((i+1) * SPOT_WIDTH,0,(j+1) * SPOT_LENGTH), new Vector2f(xHigher,yLower)));
					vertices.add(new Vertex(new Vector3f((i+1) * SPOT_WIDTH,SPOT_HEIGHT,(j+1) * SPOT_LENGTH), new Vector2f(xHigher,yHigher)));
					vertices.add(new Vertex(new Vector3f((i+1) * SPOT_WIDTH,SPOT_HEIGHT,j * SPOT_LENGTH), new Vector2f(xLower,yHigher)));
				}
			}
		}
		
//		Vertex[] vertices = new Vertex[] {	new Vertex(new Vector3f(0,0,0), new Vector2f(0,0)), 
//											new Vertex(new Vector3f(0,1,0), new Vector2f(0,1)),
//											new Vertex(new Vector3f(1,1,0), new Vector2f(1,1)), 
//											new Vertex(new Vector3f(1,0,0), new Vector2f(1,0)), };
//		
//		int[] indices = new int[] {0, 1, 2,
//								   0, 2, 3};
		
		Vertex[] vertArray = new Vertex[vertices.size()];
		Integer[] intArray = new Integer[indices.size()];
		
		vertices.toArray(vertArray);
		indices.toArray(intArray);
		
		mesh = new Mesh(vertArray, Util.toIntArray(intArray));
		transform = new Transform();
		transform.setCamera(new Camera());
		transform.setProjection(70, Window.getWidth(), Window.getHeight(), 0.01f, 1000f);
	}

	public void input() {
		Transform.getCamera().input();
	}

	public void update() {
		
	}

	public void render() {
		shader.bind();
		shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
		mesh.draw();
	}
}
