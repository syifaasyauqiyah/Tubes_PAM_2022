package tubes.pam.syifa.myghibli.api

import retrofit2.Call
import retrofit2.http.*
import tubes.pam.syifa.myghibli.dataclass.ResponseMovie

interface ApiService { //interface ini berisi endpoint yang digunakan
    @GET("films")
    fun searchQueryGet(
        @Query("q") title: String
    ): Call<ResponseMovie>
}
