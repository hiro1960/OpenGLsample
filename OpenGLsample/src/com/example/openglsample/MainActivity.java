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

	// �P�s�ǉ�
	// 2�s�ڒǉ�@windows
	// �R�s�ڒǉ�@Mac
	// 4�s�ڒǉ�@windows
	// 5�s�ڒǉ���windows
	// master�̕��ɏC��
	// master�ɂQ�s�ڂ̏C��
	
	/*
	 * OpenGL���Ǘ�����View
	 */
	GLSurfaceView glSurfaceView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// �`��p�̐�pView���쐬����
		glSurfaceView = new GLSurfaceView(this);
		// �����_���[��o�^����
		glSurfaceView.setRenderer(new SampleRender());
		// Activity�Ɋ֘A�Â���
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
     * ���������邾���̃����_���[
     */
    class SampleRender implements Renderer {
		@Override
		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			// TODO Auto-generated method stub
			
		}
    	
		@Override
		public void onSurfaceChanged(GL10 gl, int width, int height) {
			// TODO Auto-generated method stub
			gl.glViewport(0, 0, width, height); // ��ʑS����w�肷��
		}
		
		@Override
		public void onDrawFrame(GL10 gl) {
			// TODO Auto-generated method stub
			gl.glClearColor(0, 1, 1, 1);
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			
			drawPoly(gl);
		}
		
		void drawPoly(GL10 gl) {
			// �ʒu���
			float positions[] = {
				// x, y, z�̏��Œ�`����
				0.0f, 1.0f, 0.0f, // ���_�P
				-1.0f, -1.0f, 0.0f, // ���_�Q
				1.0f, -1.0f, 0.0f, // ���_�R
			};
			
			// �o�b�t�@�֓]��
			ByteBuffer vertices = ByteBuffer.allocateDirect(positions.length * 4);
			vertices.order(ByteOrder.nativeOrder());
			vertices.asFloatBuffer().put(positions);
			
			// �`��
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertices);
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
			gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
		}

    }

}
