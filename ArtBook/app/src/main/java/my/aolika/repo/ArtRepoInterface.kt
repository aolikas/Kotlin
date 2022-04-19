package my.aolika.repo

import androidx.lifecycle.LiveData
import my.aolika.model.ImageResponse
import my.aolika.roomdb.Art
import my.aolika.utill.Resource

interface ArtRepoInterface {

    suspend fun insertArt(art: Art)

    suspend fun deleteArt(art : Art)

    fun getArt() : LiveData<List<Art>>

    suspend fun searchImage(imageString: String) : Resource<ImageResponse>
}