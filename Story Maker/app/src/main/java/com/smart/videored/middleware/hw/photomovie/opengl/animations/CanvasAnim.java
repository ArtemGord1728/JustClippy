package com.smart.videored.middleware.hw.photomovie.opengl.animations;


import com.smart.videored.middleware.hw.photomovie.opengl.GLESCanvas;

public abstract class CanvasAnim extends Animation {

    public abstract int getCanvasSaveFlags();

    public abstract void apply(GLESCanvas canvas);
}
