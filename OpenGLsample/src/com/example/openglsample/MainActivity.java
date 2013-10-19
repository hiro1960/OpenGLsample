package com.example.openglsample;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	// １行追加
	// 2行目追加@windows
	// ３行目追加@Mac
	// 4行目追加@windows
	// 5行目追加＠windows
	// masterの方に修正
	// masterに２行目の修正
	
	/*
	 * OpenGLを管理するView
	 */
	GLSurfaceView glSurfaceView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 描画用の専用Viewを作成する
		glSurfaceView = new GLSurfaceView(this);
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
		@Override
		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			// TODO Auto-generated method stub
			
		}
    	
		@Override
		public void onSurfaceChanged(GL10 gl, int width, int height) {
			// TODO Auto-generated method stub
			gl.glViewport(0, 0, width, height); // 画面全域を指定する
		}
		
		@Override
		public void onDrawFrame(GL10 gl) {
			// TODO Auto-generated method stub
			gl.glClearColor(0, 1, 1, 1);
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			
			drawPoly(gl);
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

    }

}
