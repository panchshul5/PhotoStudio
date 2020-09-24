package com.bignerdranch.android.photostudio

import android.app.Application
import androidx.lifecycle.*
import com.bignerdranch.android.photogallery.QueryPreferences

class PhotoStudioViewModel(private val app: Application) : AndroidViewModel(app) {

    val galleryItemLiveData: LiveData<List<GalleryItem>>

    private val flickrFetchr = FlickrFetchr()
    private val mutableSearchTerm = MutableLiveData<String>()

    val searchTerm: String
        get() = mutableSearchTerm.value ?: ""

    init {
        mutableSearchTerm.value = QueryPreferences.getStoredQuery(app)

        galleryItemLiveData =
            Transformations.switchMap(mutableSearchTerm) { searchTerm ->
                if (searchTerm.isBlank()) {
                    flickrFetchr.fetchPhotos()
                } else {
                    flickrFetchr.searchPhotos(searchTerm)
                }
            }
    }

    fun fetchPhotos(query: String = "") {
        QueryPreferences.setStoredQuery(app, query)
        mutableSearchTerm.value = query
    }

}