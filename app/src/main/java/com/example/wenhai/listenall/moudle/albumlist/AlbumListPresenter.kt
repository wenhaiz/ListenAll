package com.example.wenhai.listenall.moudle.albumlist

import com.example.wenhai.listenall.data.LoadAlbumCallback
import com.example.wenhai.listenall.data.MusicRepository
import com.example.wenhai.listenall.data.bean.Album

internal class AlbumListPresenter(val view: AlbumListContract.View) : AlbumListContract.Presenter {
    companion object {
        const val TAG = "AlbumListPresenter"
    }

    private val musicRepository: MusicRepository = MusicRepository.INSTANCE

    init {
        view.setPresenter(this)
    }

    override fun loadNewAlbums(page: Int) {
        musicRepository.loadNewAlbum(page, object : LoadAlbumCallback {
            override fun onStart() {
                view.onLoading()
            }

            override fun onSuccess(albumList: List<Album>) {
                view.setNewAlbums(albumList)
            }

            override fun onFailure(msg: String) {
                view.onFailure(msg)
            }
        })
    }
}