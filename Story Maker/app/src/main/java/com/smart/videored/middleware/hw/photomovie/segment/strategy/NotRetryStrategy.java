package com.smart.videored.middleware.hw.photomovie.segment.strategy;

import com.smart.videored.middleware.hw.photomovie.PhotoMovie;
import com.smart.videored.middleware.hw.photomovie.model.PhotoData;
import com.smart.videored.middleware.hw.photomovie.segment.MovieSegment;

import java.util.List;

/**
 * Created by Administrator on 2015/6/12.
 */
public class NotRetryStrategy implements RetryStrategy {
    @Override
    public List<PhotoData> getAvailableData(PhotoMovie photoMovie, MovieSegment movieSegment) {
        return movieSegment==null?null:movieSegment.getAllocatedPhotos();
    }
}
