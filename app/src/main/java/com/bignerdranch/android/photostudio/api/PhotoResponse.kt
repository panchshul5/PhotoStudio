package com.bignerdranch.android.photostudio.api

import com.bignerdranch.android.photostudio.GalleryItem
import com.google.gson.annotations.SerializedName

class PhotoResponse {
    @SerializedName("photo")
    lateinit var galleryItems: List<GalleryItem>

}