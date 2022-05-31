package tubes.pam.syifa.myghibli

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import tubes.pam.syifa.myghibli.adapter.ListMovieAdapter
import tubes.pam.syifa.myghibli.databinding.ActivityMainBinding
import tubes.pam.syifa.myghibli.dataclass.ItemsItem
import tubes.pam.syifa.myghibli.dataclass.Movie
import tubes.pam.syifa.myghibli.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() { //halaman awal saat aplikasi dibuka, berisi list film
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Ghibli Movies"
        showLoading(false)

        val layoutManager = LinearLayoutManager(this)
        binding.rvMovie.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvMovie.addItemDecoration(itemDecoration)

        mainViewModel = ViewModelProvider(this@MainActivity).get(MainViewModel::class.java)

        mainViewModel.getMovie().observe(this, { movieItems ->
            setListMovieAdapter(movieItems)
        })
    }

    private fun setListMovieAdapter(movie: List<ItemsItem>) {
        val listMovieItem = ArrayList<ItemsItem>()
        for (zero in movie) {
            val  listAdapt= ItemsItem(zero.judul, zero.judulOri, zero.gambar)
            listMovieItem.add(listAdapt)
        }
        val adapter = ListMovieAdapter(listMovieItem)
        binding.rvMovie.adapter = adapter
        adapter.setOnItemClickCallback(object : ListMovieAdapter.OnItemClickCallback {
            override fun onItemClicked(zero: ItemsItem) {
                detailforMovie(zero)
            }
        })
        showLoading(false)
    }

    private fun detailforMovie(data: ItemsItem) {
        val detailforMovie = Intent(this@MainActivity, DetailActivity::class.java)
        detailforMovie.putExtra(DetailActivity.EXTRA_MOVIE, data)
        startActivity(detailforMovie)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                getDataMovieFromApi(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

    fun getDataMovieFromApi(data: String){
        if(data.isEmpty()) return
        showLoading(true)
        mainViewModel.setMovie(data)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}