package com.itaobox.game.core;

import org.andengine.engine.Engine;
import org.andengine.engine.Engine.EngineLock;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import android.content.Context;

public class ItaoboxSprite {
	private Context _context;
	private TextureManager _textureManager;
	private BuildableBitmapTextureAtlas _buildableBitmapTextureAtlas;
	
	/***
	 * 构造函数 1
	 * @param context
	 * @param textureManager
	 * @param folder
	 */
	public ItaoboxSprite(Context context,TextureManager textureManager,String folder){
		this._context = context;
		this._textureManager = textureManager;
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(folder);
		this._buildableBitmapTextureAtlas = new BuildableBitmapTextureAtlas(this._textureManager, 850, 480, TextureOptions.NEAREST);
	}
	
	/***
	 * 添加一个Sprite 2
	 * @param res
	 * @param pTileColumns
	 * @param pTileRows
	 * @return
	 */
	public TiledTextureRegion add(String res,int pTileColumns,int pTileRows){
		return BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this._buildableBitmapTextureAtlas, this._context, res, pTileColumns, pTileRows);
	}
	/***
	 * 配置资源 3
	 */
	public void configRes(){
		try {
			this._buildableBitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
			this._buildableBitmapTextureAtlas.load();
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}
	/***
	 * 获得一个动画Sprite 4
	 * @param tiledTextureRegion
	 * @param vertexBufferObjectManager
	 * @param pFrameDurationEach
	 * @param pX
	 * @param pY
	 * @return
	 */
	public AnimatedSprite getAnimationSprite(TiledTextureRegion tiledTextureRegion,VertexBufferObjectManager vertexBufferObjectManager,
			final float pX, final float pY,
			long pFrameDurationEach){
		final AnimatedSprite animatedSprite = new AnimatedSprite(pX, pY, tiledTextureRegion, vertexBufferObjectManager);
		animatedSprite.animate(pFrameDurationEach);
		return animatedSprite;
	}
	/***
	 * 获得一个动画Sprite 4
	 * @param tiledTextureRegion
	 * @param vertexBufferObjectManager
	 * @param pX
	 * @param pY
	 * @param pFrameDurationEach
	 * @param pFirstTileIndex
	 * @param pLastTileIndex
	 * @param pLoop
	 * @return
	 */
	public AnimatedSprite getAnimationSprite(TiledTextureRegion tiledTextureRegion,VertexBufferObjectManager vertexBufferObjectManager,
			final float pX, final float pY,
			long[] pFrameDurationEach,int pFirstTileIndex, int pLastTileIndex, boolean pLoop){
		final AnimatedSprite animatedSprite = new AnimatedSprite(pX, pY, tiledTextureRegion, vertexBufferObjectManager);
		//animatedSprite.animate(new long[]{1000, 200, 200}, 0, 2, true);
		animatedSprite.animate(pFrameDurationEach, pFirstTileIndex, pLastTileIndex, pLoop);
		return animatedSprite;
	}
	/***
	 * 删除一个AnimatedSprite
	 * @param animatedSprite
	 * @param engine
	 * @param scene
	 * @return
	 */
	public AnimatedSprite Remove(AnimatedSprite animatedSprite,Engine engine,Scene scene){
		if(animatedSprite == null) {
			return null;
		}
		final EngineLock engineLock = engine.getEngineLock();
		engineLock.lock();
		/* Now it is save to remove the entity! */
		scene.detachChild(animatedSprite);
		animatedSprite.dispose();
		animatedSprite = null;

		engineLock.unlock();
		return animatedSprite;
	}
	
}
