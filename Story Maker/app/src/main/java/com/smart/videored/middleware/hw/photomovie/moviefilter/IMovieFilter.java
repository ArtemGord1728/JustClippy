package com.smart.videored.middleware.hw.photomovie.moviefilter;

import com.smart.videored.middleware.hw.photomovie.PhotoMovie;
import com.smart.videored.middleware.hw.photomovie.opengl.FboTexture;


public interface IMovieFilter {
    void doFilter(PhotoMovie photoMovie,int elapsedTime, FboTexture inputTexture, FboTexture outputTexture);
    void release();
}
