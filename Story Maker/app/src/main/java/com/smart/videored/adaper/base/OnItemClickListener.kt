package com.smart.videored.adaper.base

interface OnItemClickListener<T> {
    fun onClick(item:T, position: Int)
}