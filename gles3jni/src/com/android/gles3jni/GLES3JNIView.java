/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.gles3jni;

import android.content.Context;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.opengles.GL10;

class GLES3JNIView extends GLSurfaceView {
    private static final String TAG = "GLES3JNI";
    private static final boolean DEBUG = true;

    public GLES3JNIView(Context context) {
        super(context);
        // Pick an EGLConfig with RGB8 color, 16-bit depth, no stencil,
        // supporting OpenGL ES 2.0 or later backwards-compatible versions.
        setEGLConfigChooser(8, 8, 8, 0, 16, 0);
        setEGLContextClientVersion(2);
        setRenderer(new Renderer());
    }

    private static class Renderer implements GLSurfaceView.Renderer {
        public void onDrawFrame(GL10 gl) {
            //GLES3JNILib.step();
    			float[] fM  = new float[16];
    			com.baofeng.mojing.MojingSDK.getLastHeadView(fM);
            com.baofeng.mojing.MojingSDK3288.RenderFrame(fM);
        }

        public void onSurfaceChanged(GL10 gl, int width, int height) {
            //GLES3JNILib.resize(width, height);
			float[] modelView = { -0.5f, 0.0f, 0.0f, // leftCameraPos
					0.5f, 0.0f, 0.0f, // rightCameraPos
					0.0f, 0.0f, -1.0f, // lookAt
					0.0f, 1.0f, 0.0f // upVec
			};
			float[] perspective = { 1.5707963268f, 1.0f, 0.1f, 100.0f, // left(fov,
																		// w/h,
																		// near,
																		// far)
					1.5707963268f, 1.0f, 0.1f, 100.0f // right
			};

			com.baofeng.mojing.MojingSDK3288.RenderInit(width, height, 0, modelView, perspective);

        }

        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            //GLES3JNILib.init();
        }
    }
}
