package tubes.pam.syifa.myghibli.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Response
import tubes.pam.syifa.myghibli.api.ApiConfig
import tubes.pam.syifa.myghibli.dataclass.ItemsItem
import tubes.pam.syifa.myghibli.dataclass.ResponseMovie

class MainViewModel : ViewModel() { //kelas ini akan menyimpan dan mengelola data dari dataclass

    private val listMovie = MutableLiveData<List<ItemsItem>>()

    fun setMovie(movie: String) {
        val asyncClient = ApiConfig.getApiService().searchQueryGet(movie)
        asyncClient.enqueue(object : retrofit2.Callback<ResponseMovie> { //enqueue berfungsi untuk menjalankan request asynchronus
            override fun onResponse(
                call: Call<ResponseMovie>,
                response: Response<ResponseMovie>
            ) {
                if(response.isSuccessful) {
                    val listItemMovie = ArrayList<ItemsItem>()
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val listItem = responseBody.items
                        for (zero in listItem) {
                            val listAdapter = ItemsItem(zero.judul, zero.judulOri, zero.gambar)
                            listItemMovie.add(listAdapter)
                        }
                        listMovie.postValue(listItemMovie)
                    }
                }
                else {
                    Log.e(TAG,"onFailure: ${response.message()}" )
                }
            }
            override fun onFailure(call: Call<ResponseMovie>, t: Throwable) {
            }
        })
    }

    fun getMovie(): LiveData<List<ItemsItem>> {
        return listMovie
    }

    companion object{
        private val TAG = MainViewModel::class.java.simpleName
    }
}
