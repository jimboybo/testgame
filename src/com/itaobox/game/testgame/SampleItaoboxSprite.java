package com.itaobox.game.testgame;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.itaobox.game.core.ItaoboxSprite;


public class SampleItaoboxSprite extends SimpleBaseGameActivity {

	private static final int CAMERA_WIDTH = 480;
	private static final int CAMERA_HEIGHT = 320;
	
	private Camera mCamera;
	
	private ItaoboxSprite itaoboxSprite;
	private TiledTextureRegion tiled1;
	private AnimatedSprite as1;
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera);
	}

	@Override
	protected void onCreateResources() {
		this.itaoboxSprite = new ItaoboxSprite(this, this.getTextureManager(), "mine/");
		this.tiled1 = this.itaoboxSprite.add("person.png", 3, 4);
		this.itaoboxSprite.configRes();
	}

	@Override
	protected Scene onCreateScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

		final Scene scene = new Scene();
		scene.setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
		
		//this.as1 = this.itaoboxSprite.getAnimationSprite(this.tiled1, this.getVertexBufferObjectManager(), 100, 100, 200);
		this.as1 = this.itaoboxSprite.getAnimationSprite(this.tiled1, getVertexBufferObjectManager(), 100, 100, new long[]{200,200,200}, 3, 5, true);
		scene.attachChild(as1);
		

		return scene;
	}

}
