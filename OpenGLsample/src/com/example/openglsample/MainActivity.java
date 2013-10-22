package com.example.openglsample;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;

public class MainActivity extends Activity {
	
	/*
	 * OpenGLを管理するView
	 */
	GLSurfaceView glSurfaceView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 描画用の専用Viewを作成する
		glSurfaceView = new GLSurfaceView(this);
		// DEPTHを有効にする
		glSurfaceView.setEGLConfigChooser(true);
		// レンダラーを登録する
		glSurfaceView.setRenderer(new SampleRender());
		// Activityに関連づける
		setContentView(glSurfaceView);
		//setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
    @Override
    protected void onResume(){
      super.onResume();
      glSurfaceView.onResume();
    }
    
    @Override
    protected void onPause(){
      super.onPause();
      glSurfaceView.onPause();
    }
    
    /*
     * 初期化するだけのレンダラー
     */
    class SampleRender implements Renderer {
    	
    	int texture = 0;
    	int viewWidth = 0;
    	int viewHeight = 0;
    		
		@Override
		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			// TODO Auto-generated method stub
			
			
		}
    	
		@Override
		public void onSurfaceChanged(GL10 gl, int width, int height) {
			// TODO Auto-generated method stub
			// 深度テストを有効にする
			gl.glEnable(GL10.GL_DEPTH_TEST);
			gl.glViewport(0, 0, width, height); // 画面全域を指定する
			this.viewWidth = width;
			this.viewHeight = height;
			
//			// テクスチャ領域を確保
//			{
//				int[] textures = {
//						0
//				};
//				gl.glGenTextures(1, textures, 0);
//				texture = textures[0];
//			}
//			
//			// テクスチャを読み込む
//			{
//				Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.neko);
//				gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
//				GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
//				
//				// テクスチャのフィルタを設定する
//				// これを忘れると正常に読みkめない場合がある
//				gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
//				gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
//			}
//			
//			// UV情報：頂点へのテクスチャ上の位置の対応
//			{
//				float coords[] = {
//					// u vの順で定義する
//					0.5f, 0,   // 頂点１
//					0, 1,      // 頂点２
//					1, 1,      // 頂点３
//				};
//				// バッファへ転送
//				ByteBuffer textureCoord = ByteBuffer.allocateDirect(coords.length * 4);
//				textureCoord.order(ByteOrder.nativeOrder());
//				textureCoord.asFloatBuffer().put(coords);
//				
//				gl.glEnable(GL10.GL_TEXTURE_2D);
//				gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
//				gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureCoord);
//			}
//			gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
		}
		
		@Override
		public void onDrawFrame(GL10 gl) {
			// TODO Auto-generated method stub
			gl.glClearColor(0, 1, 1, 1);
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
			
			setupCamera(gl);
			
			//drawPoly(gl);
			drawPolyRed(gl);
			drawPolyBlue(gl);
		}
		
		void drawPoly(GL10 gl) {
			// 位置情報
			float positions[] = {
				// x, y, zの順で定義する
				0.0f, 1.0f, 0.0f, // 頂点１
				-1.0f, -1.0f, 0.0f, // 頂点２
				1.0f, -1.0f, 0.0f, // 頂点３
			};
			
			// バッファへ転送
			ByteBuffer vertices = ByteBuffer.allocateDirect(positions.length * 4);
			vertices.order(ByteOrder.nativeOrder());
			vertices.asFloatBuffer().put(positions);
			
			// 描画
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertices);
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
			gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
		}
		
		void drawPolyRed(GL10 gl) {
			// 位置情報
			final float Z = 1.0f;
			float positions[] = {
				0.0f, 1.0f, 0,  // 頂点１
				-1.0f, -1.0f, Z, // 頂点２
				1.0f, -1.0f, Z,  // 頂点３
			};
			// バッファへ転送
			ByteBuffer vertices = ByteBuffer.allocateDirect(positions.length * 4);
			vertices.order(ByteOrder.nativeOrder());
			vertices.asFloatBuffer().put(positions);
			
			// テクスチャ情報
			{
				// テクスチャ領域を確保
				{
					int[] textures = {
							0
					};
					gl.glGenTextures(1, textures, 0);
					texture = textures[0];
				}
				
				// テクスチャを読み込む
				{
					Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.neko);
					gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
					GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
					
					// テクスチャのフィルタを設定する
					// これを忘れると正常に読みkめない場合がある
					gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
					gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
				}
				
				// UV情報：頂点へのテクスチャ上の位置の対応
				{
					float coords[] = {
						// u vの順で定義する
						0.5f, 0,   // 頂点１
						0, 1,      // 頂点２
						1, 1,      // 頂点３
					};
					// バッファへ転送
					ByteBuffer textureCoord = ByteBuffer.allocateDirect(coords.length * 4);
					textureCoord.order(ByteOrder.nativeOrder());
					textureCoord.asFloatBuffer().put(coords);
					
					gl.glEnable(GL10.GL_TEXTURE_2D);
					gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
					gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureCoord);
				}
				gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);

			}
			// ポリゴンの色を赤にする
			{
				gl.glColor4f(1, 0, 0, 1);
			}
			// 描画
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertices);
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
			gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
		}
	
		void drawPolyBlue(GL10 gl) {
			// 位置情報
			final float Z = 1.0f;
			float positions[] = {
				0.0f, 1.0f, Z,  // 頂点１
				-1.0f, -1.0f, 0, // 頂点２
				1.0f, -1.0f, 0,  // 頂点３
			};
			// バッファへ転送
			ByteBuffer vertices = ByteBuffer.allocateDirect(positions.length * 4);
			vertices.order(ByteOrder.nativeOrder());
			vertices.asFloatBuffer().put(positions);
			
			// テクスチャ情報
			{
				// テクスチャ領域を確保
				{
					int[] textures = {
							0
					};
					gl.glGenTextures(1, textures, 0);
					texture = textures[0];
				}
				
				// テクスチャを読み込む
				{
					Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.neko);
					gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
					GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
					
					// テクスチャのフィルタを設定する
					// これを忘れると正常に読みkめない場合がある
					gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
					gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
				}
				
				// UV情報：頂点へのテクスチャ上の位置の対応
				{
					float coords[] = {
						// u vの順で定義する
						0.5f, 0,   // 頂点１
						0, 1,      // 頂点２
						1, 1,      // 頂点３
					};
					// バッファへ転送
					ByteBuffer textureCoord = ByteBuffer.allocateDirect(coords.length * 4);
					textureCoord.order(ByteOrder.nativeOrder());
					textureCoord.asFloatBuffer().put(coords);
					
					gl.glEnable(GL10.GL_TEXTURE_2D);
					gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
					gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureCoord);
				}
				gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);

			}
			// ポリゴンの色を青にする
			{
				gl.glColor4f(0, 0, 1, 1);
			}
			// 描画
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertices);
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
			gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
		}

		
		void setupCamera(GL10 gl) {
			gl.glMatrixMode(GL10.GL_PROJECTION);
			gl.glLoadIdentity();
			{
				final float fovY = 45.0f;
				final float aspect = (float)viewWidth/(float)viewHeight;
				final float near = 0.1f;
				final float far = 1000.0f;
				final float eyeX = -1.0f;
				final float eyeY = 5.0f;
				final float eyeZ = 5.0f;
				final float centerX = 0;
				final float centerY = 0;
				final float centerZ = 0;
				final float upX = 0;
				final float upY = 1;
				final float upZ = 0;
				GLU.gluPerspective(gl, fovY, aspect, near, far);
				GLU.gluLookAt(gl, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);	
			}
			gl.glMatrixMode(GL10.GL_MODELVIEW);
		}

    }

}
