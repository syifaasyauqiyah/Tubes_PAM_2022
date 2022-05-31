package tubes.pam.syifa.myghibli.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import tubes.pam.syifa.myghibli.dataclass.Movie
import kotlin.Exception


class DetailViewModel : ViewModel() { //kelas ini akan menyimpan dan mengelola data dari dataclass
    private val _isLoading = MutableLiveData<Boolean>()
    val isloading: LiveData<Boolean> = _isLoading

    private val _detailMovies = MutableLiveData<Movie>()
    val detailMovies : LiveData<Movie> = _detailMovies

    fun setDetailMovie(movie: String){
        _isLoading.value = true
        val asyncClient = AsyncHttpClient()
        asyncClient.get(movie, object : AsyncHttpResponseHandler() { //get digunakna untuk mengambil data
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                val result = String(responseBody)
                Log.d(TAG, result)
                try {
                    val jsonObject = JSONObject(result)
                    val judul = jsonObject.getString("title")
                    val judulOri = jsonObject.getString("original_title")
                    val judulOriRom = jsonObject.getString("original_title_romanised")
                    val deskripsi = jsonObject.getString("description")
                    val direktor = jsonObject.getString("director")
                    val produser = jsonObject.getString("producer")
                    val rilis = jsonObject.getString("release_date")
                    val skor = jsonObject.getString("rt_score")
                    val gambar = jsonObject.getString("image")

                    val newInstance = Movie(judul, judulOri, judulOriRom, deskripsi, direktor, produser, rilis, skor, gambar)
                    _detailMovies.postValue(newInstance)
                    _isLoading.value = false
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                _isLoading.value = false
                when (statusCode){
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Log.d("onFailure: ", error?.message.toString())
            }
        })
    }

    fun getDetailMovie(): LiveData<Movie> {
        return _detailMovies
    }

    companion object{
        private val TAG = DetailViewModel::class.java.simpleName
    }
}