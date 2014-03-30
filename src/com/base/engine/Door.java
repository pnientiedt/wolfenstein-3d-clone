package com.base.engine;

public class Door {
	
	public final static float LENGTH = 1;
	private final static float HEIGHT = 1;
	public final static float WIDTH = 0.125f;
	private final static float START = 0;
	private final static double TIME_TO_OPEN = 1.0;
	private final static double CLOSE_DELAY = 2.0;
	
	private static Mesh mesh;
	private Material material;
	private Transform transform;
	
	private boolean isOpening;
	private double openingStartTime;
	private double openTime;
	private double closingStartTime;
	private double closeTime;
	
	public Door(Transform transform, Material material) {
		this.transform = transform;
		this.material = material;
		isOpening = false;
		
		if(mesh == null) {
			//NOTE: You may need to add top/bottom face
			
			Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(START,START,START), new Vector2f(0.5f,1)),
											  new Vertex(new Vector3f(START,HEIGHT,START), new Vector2f(0.5f,0.75f)),
											  new Vertex(new Vector3f(LENGTH,HEIGHT,START), new Vector2f(0.75f,0.75f)),
											  new Vertex(new Vector3f(LENGTH,START,START), new Vector2f(0.75f,1)),
											  
											  new Vertex(new Vector3f(START,START,START), new Vector2f(0.73f,1)),
											  new Vertex(new Vector3f(START,HEIGHT,START), new Vector2f(0.73f,0.75f)),
											  new Vertex(new Vector3f(START,HEIGHT,WIDTH), new Vector2f(0.75f,0.75f)),
											  new Vertex(new Vector3f(START,START,WIDTH), new Vector2f(0.75f,1)),
											  
											  new Vertex(new Vector3f(START,START,WIDTH), new Vector2f(0.5f,1)),
											  new Vertex(new Vector3f(START,HEIGHT,WIDTH), new Vector2f(0.5f,0.75f)),
											  new Vertex(new Vector3f(LENGTH,HEIGHT,WIDTH), new Vector2f(0.75f,0.75f)),
											  new Vertex(new Vector3f(LENGTH,START,WIDTH), new Vector2f(0.75f,1)),
											  
											  new Vertex(new Vector3f(LENGTH,START,START), new Vector2f(0.73f,1)),
											  new Vertex(new Vector3f(LENGTH,HEIGHT,START), new Vector2f(0.73f,0.75f)),
											  new Vertex(new Vector3f(LENGTH,HEIGHT,WIDTH), new Vector2f(0.75f,0.75f)),
											  new Vertex(new Vector3f(LENGTH,START,WIDTH), new Vector2f(0.75f,1))};
			
			int[] indices = new int[]{0,1,2,
									  0,2,3,
									  
									  6,5,4,
									  7,6,4,
									  
									  10,9,8,
									  11,10,8,
									  
									  12,13,14,
									  12,14,15};
			
			mesh = new Mesh(vertices, indices);
		}
	}
	
	public void open() {
		if (isOpening)
			return;
		
		openingStartTime = (double)Time.getTime()/(double)Time.SECOND;
		openTime = openingStartTime + TIME_TO_OPEN;
		closeTime = openTime + CLOSE_DELAY;
		isOpening = true;
	}
	
	public void update() {
		
	}
	
	public void render() {
		Shader shader = Game.getLevel().getShader();
		shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
		mesh.draw();
	}
	
	public Transform getTransform() {
		return transform;
	}
}
