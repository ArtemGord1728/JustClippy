package com.smart.videored.model

import com.smart.videored.middleware.hw.photomovie.moviefilter.*

class FilterItem(var imgRes: Int, var name: String, var type: FilterType) {
    fun initFilter(): IMovieFilter? {
        return when (type) {
            FilterType.GRAY -> GrayMovieFilter()
            FilterType.SNOW -> SnowMovieFilter()
            FilterType.CAMEO -> CameoMovieFilter()
            FilterType.KUWAHARA -> KuwaharaMovieFilter()
            FilterType.LUT1 -> LutMovieFilter(LutMovieFilter.LutType.A)
            FilterType.LUT2 -> LutMovieFilter(LutMovieFilter.LutType.B)
            FilterType.LUT3 -> LutMovieFilter(LutMovieFilter.LutType.C)
            FilterType.LUT4 -> LutMovieFilter(LutMovieFilter.LutType.D)
            FilterType.LUT5 -> LutMovieFilter(LutMovieFilter.LutType.E)
            else -> null
        }
    }
}