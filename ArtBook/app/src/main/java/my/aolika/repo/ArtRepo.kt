package my.aolika.repo

import androidx.lifecycle.LiveData
import my.aolika.api.RetrofitApi
import my.aolika.model.ImageResponse
import my.aolika.roomdb.Art
import my.aolika.roomdb.ArtDao
import my.aolika.utill.Resource

import java.lang.Exception

import javax.inject.Inject

class ArtRepo @Inject constructor(
    private val artDao: ArtDao,
    private val retrofitApi: RetrofitApi
) : ArtRepoInterface {

    override suspend fun insertArt(art: Art) {
        artDao.insertArt(art)
    }

    override suspend fun deleteArt(art: Art) {
        artDao.deleteArt(art)
    }

    override fun getArt(): LiveData<List<Art>> {
        return artDao.observeArt()
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return try {
            val response = retrofitApi.imageSearch(imageString)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error", null)
            } else {
                Resource.error("Error", null)
            }
        } catch (e: Exception) {
            Resource.error("No data", null)
        }
    }
}