package com.smart.videored.middleware.hw.photomovie.segment.strategy;

import com.smart.videored.middleware.hw.photomovie.PhotoMovie;
import com.smart.videored.middleware.hw.photomovie.model.PhotoData;
import com.smart.videored.middleware.hw.photomovie.segment.MovieSegment;

import java.util.List;

/**
 * Created by yellowcat on 2015/6/12.
 */
public interface RetryStrategy {
    List<PhotoData> getAvailableData(PhotoMovie photoMovie, MovieSegment movieSegment);
}
