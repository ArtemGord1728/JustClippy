package com.smart.videored.model

import com.smart.videored.middleware.hw.photomovie.PhotoMovieFactory.PhotoMovieType

class TransferItem(@JvmField var imgRes: Int, @JvmField var name: String, @JvmField var type: PhotoMovieType)