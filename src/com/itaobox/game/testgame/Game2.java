package com.itaobox.game.testgame;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.itaobox.game.core.ItaoboxSprite;

public class Game2 extends SimpleBaseGameActivity {

	private static final int CAMERA_WIDTH = 720;
	private static final int CAMERA_HEIGHT = 480;

	private static final float DEMO_VELOCITY = 100.0f;
	
	private ItaoboxSprite itaoboxSprite;
	private TiledTextureRegion tiled1;
	private AnimatedSprite as1;
	
	

	@Override
	public EngineOptions onCreateEngineOptions() {
		final Camera camera = new Camera(0, 0, Game2.CAMERA_WIDTH, Game2.CAMERA_HEIGHT);

		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(Game2.CAMERA_WIDTH, Game2.CAMERA_HEIGHT), camera);
	}

	@Override
	public void onCreateResources() {
		this.itaoboxSprite = new ItaoboxSprite(this, this.getTextureManager(), "mine/");
		this.tiled1 = this.itaoboxSprite.add("person.png", 3, 4);
		this.itaoboxSprite.configRes();
	}

	@Override
	public Scene onCreateScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

		final Scene scene = new Scene();
		scene.setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
		
		this.as1 = this.itaoboxSprite.getAnimationSprite(this.tiled1, getVertexBufferObjectManager(), 100, 100, new long[]{200,200,200}, 3, 5, true);
		scene.attachChild(as1);

		final float centerX = (Game2.CAMERA_WIDTH - this.tiled1.getWidth()) / 2;
		final float centerY = (Game2.CAMERA_HEIGHT - this.tiled1.getHeight()) / 2;
		final Ball ball = new Ball(centerX, centerY, this.tiled1, this.getVertexBufferObjectManager());

		scene.attachChild(ball);

		return scene;
	}

	private static class Ball extends AnimatedSprite {
		private final PhysicsHandler mPhysicsHandler;

		public Ball(final float pX, final float pY, final TiledTextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager) {
			super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
			this.mPhysicsHandler = new PhysicsHandler(this);
			this.registerUpdateHandler(this.mPhysicsHandler);
			this.mPhysicsHandler.setVelocity(Game2.DEMO_VELOCITY, Game2.DEMO_VELOCITY);
		}

		@Override
		protected void onManagedUpdate(final float pSecondsElapsed) {
			this.mPhysicsHandler.setVelocityX(100f);
			android.util.Log.e("ooooo","x="+String.valueOf(this.mX)+";y="+String.valueOf(this.mY));
			
			/*
			if(this.mX < 0) {
				this.mPhysicsHandler.setVelocityX(Game2.DEMO_VELOCITY);
			} else if(this.mX + this.getWidth() > Game2.CAMERA_WIDTH) {
				this.mPhysicsHandler.setVelocityX(-Game2.DEMO_VELOCITY);
			}

			if(this.mY < 0) {
				this.mPhysicsHandler.setVelocityY(Game2.DEMO_VELOCITY);
			} else if(this.mY + this.getHeight() > Game2.CAMERA_HEIGHT) {
				this.mPhysicsHandler.setVelocityY(-Game2.DEMO_VELOCITY);
			}
			*/
			super.onManagedUpdate(pSecondsElapsed);
		}
	}
}
