package com.base.engine;

public class Monster {
	
	public final static float SCALE = 0.7f;
	public final static float SIZEY = SCALE;
	public final static float SIZEX = (float)((double)SIZEY / (1.9319344827586206896551724137931 * 2.0));
	public final static float START = 0;
	
	public final static float OFFSET_X = 0.01f;
	public final static float OFFSET_Y = 0.01f;
	
	public final static float OFFSET_FROM_GROUND = 0.7f;
	
	public static final float TEX_MIN_X = -OFFSET_X;
	public static final float TEX_MAX_Y = -1 -OFFSET_X;
	public static final float TEX_MIN_Y = -OFFSET_Y;
	public static final float TEX_MAX_X = 1 - OFFSET_Y;
	
	public static final int STATE_IDLE = 0;
	public static final int STATE_CHASE = 1;
	public static final int STATE_ATTACK = 2;
	public static final int STATE_DYING = 3;
	public static final int STATE_DEAD = 4;
	
	private static Mesh mesh;
	private Material material;
	private Transform transform;
	private int state;
	
	public Monster(Transform transform) {
		this.transform = transform;
		this.state = STATE_IDLE;
		this.transform.setRotation(this.transform.getRotation().add(new Vector3f(0,0,180)));
//		this.transform.setTranslation(this.transform.getTranslation().add(new Vector3f(0,0.7f,0)));
		material = new Material(new Texture("SSWVA1.png"));
		if(mesh == null) {
			
			Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(-SIZEX,START,START), new Vector2f(TEX_MAX_X,TEX_MAX_Y)),
											  new Vertex(new Vector3f(-SIZEX,SIZEY,START), new Vector2f(TEX_MAX_X,TEX_MIN_Y)),
											  new Vertex(new Vector3f(SIZEX,SIZEY,START), new Vector2f(TEX_MIN_X,TEX_MIN_Y)),
											  new Vertex(new Vector3f(SIZEX,START,START), new Vector2f(TEX_MIN_X,TEX_MAX_Y))};
			
			int[] indices = new int[]{0,1,2,
									  0,2,3};
			
			mesh = new Mesh(vertices, indices);
		}
	}
	
	private void idleUpdate() {
		
	}
	
	private void chaseUpdate() {
		
	}
	
	private void attackUpdate() {
		
	}
	
	private void dyingUpdate() {
		
	}
	
	private void deadUpdate() {
		
	}
	
	private void alignWithGround() {
		transform.getTranslation().setY(OFFSET_FROM_GROUND);
	}
	
	public void faceCamera() {
		Vector3f directionToCamera = transform.getTranslation().sub(Transform.getCamera().getPos());
		float angleToFaceTheCamera = (float) Math.toDegrees(Math.atan(directionToCamera.getZ() / directionToCamera.getX()));
		
		if (directionToCamera.getX() > 0) {
			angleToFaceTheCamera += 180;
		}
		
		transform.getRotation().setY(-(angleToFaceTheCamera + 90));
	}
	
	public void update() {
		alignWithGround();
		faceCamera();		
		
		switch(state) {
			case STATE_IDLE: idleUpdate(); break;
			case STATE_CHASE: chaseUpdate(); break;
			case STATE_ATTACK: attackUpdate(); break;
			case STATE_DYING: dyingUpdate(); break;
			case STATE_DEAD: deadUpdate(); break;
		}
	}
	
	public void render() {
		Shader shader = Game.getLevel().getShader();
		shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
		mesh.draw();
	}
}
