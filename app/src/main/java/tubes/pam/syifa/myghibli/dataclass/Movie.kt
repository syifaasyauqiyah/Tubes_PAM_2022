package tubes.pam.syifa.myghibli.dataclass

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
//dataclass berfungsi untuk menampung data dari JSON
@Parcelize
data class ResponseMovie(
    @field:SerializedName("films")
    val items: List<ItemsItem>
) : Parcelable

@Parcelize
data class Movie(
    @field:SerializedName("title")
    var judul : String? = null,

    @field:SerializedName("original_title")
    var judulOri: String? = null,

    @field:SerializedName("original_title_romanised")
    var judulOriRom: String? = "",

    @field:SerializedName("description")
    var deskripsi: String? = "",

    @field:SerializedName("director")
    var direktor: String? = "",

    @field:SerializedName("producer")
    var produser: String? = "",

    @field:SerializedName("release_date")
    var rilis: String? = "",

    @field:SerializedName("rt_score")
    var skor: String? = "",

    @field:SerializedName("image")
    var gambar: String = "",

    var id: Int = 0
) : Parcelable

@Parcelize
data class ItemsItem(
    @field:SerializedName("title")
    val judul: String,

    @field:SerializedName("original_title")
    val judulOri: String,

    @field:SerializedName("image")
    val gambar: String
) : Parcelable
