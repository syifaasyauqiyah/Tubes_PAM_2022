package tubes.pam.syifa.myghibli

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import tubes.pam.syifa.myghibli.databinding.ActivityDetailBinding
import tubes.pam.syifa.myghibli.dataclass.ItemsItem
import tubes.pam.syifa.myghibli.viewmodel.DetailViewModel
//halaman detail dari film
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailviewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailviewModel = ViewModelProvider(this@DetailActivity).get(DetailViewModel::class.java)
        detailviewModel.isloading.observe(this,{
            showLoading(it)
        })

        val mov = intent.getParcelableExtra<ItemsItem>(EXTRA_MOVIE) as ItemsItem

        supportActionBar?.elevation = 0f
        supportActionBar?.title = mov.judul

        detailviewModel.setDetailMovie(mov.judul)
        detailviewModel.getDetailMovie().observe(this, {
            binding.apply {
                Glide.with(this@DetailActivity)
                    .load(it.gambar)
                    .into(binding.imgItemPhoto)
                judulOri.text = it.judulOri
                judulOriRom.text = it.judulOriRom
                deskripsi.text = it.deskripsi
                direktor.text = it.direktor
                produser.text = it.produser
                rilis.text = it.rilis
                skor.text = it.skor
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object{
        const val EXTRA_MOVIE = "extra_movie"
    }
}