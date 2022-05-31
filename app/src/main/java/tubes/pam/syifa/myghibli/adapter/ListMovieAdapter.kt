package tubes.pam.syifa.myghibli.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tubes.pam.syifa.myghibli.databinding.ItemRowMovieBinding
import tubes.pam.syifa.myghibli.dataclass.ItemsItem

//class ini akan menampilkan film sebagai list
class ListMovieAdapter(private val data : List<ItemsItem>) : RecyclerView.Adapter<ListMovieAdapter.ListViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val(title,original_title,image) = data[position]
        holder.apply {
            Glide.with(itemView.context)
                .load(image)
                .circleCrop()
                .into(binding.imgItemPhoto)
            binding.judul.text = title
            binding.judulOri.text = original_title
            itemView.setOnClickListener { onItemClickCallback?.onItemClicked(data[adapterPosition]) }
        }
    }

    override fun getItemCount(): Int = data.size

    class ListViewHolder(val binding: ItemRowMovieBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data : ItemsItem)
    }
}
