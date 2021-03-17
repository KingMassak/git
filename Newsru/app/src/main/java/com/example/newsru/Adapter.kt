package com.example.newsru

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class Adapter(val newsList: List<News>):RecyclerView.Adapter<Adapter.NewsViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news, parent, false)
            val imageView=itemView.findViewById<ImageButton>(R.id.imageView)
            val browse=itemView.findViewById<WebView>(R.id.browse)
            val newsurl=itemView.findViewById<TextView>(R.id.newsTime)

            
            imageView.setOnClickListener {
                browse.settings.javaScriptEnabled=true
            browse.loadUrl(newsurl.text as String)
           }

            return NewsViewHolder(itemView)
        }
        override fun getItemCount() = newsList.size
        override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
            return holder.bind(newsList[position])
        }
        class NewsViewHolder(view:View):RecyclerView.ViewHolder(view){
            private val image:ImageButton=view.findViewById(R.id.imageView)
            private val title:TextView=view.findViewById(R.id.newsTitle)
            private val description:TextView=view.findViewById(R.id.newsDescription)
            private val time_url:TextView=view.findViewById(R.id.newsTime)
        fun bind (news:News)
        {
            Picasso.get().load(news.urlToImage).into(image)
            title.text =news.title
            description.text=news.description
            time_url.text=news.url
        }
    }

}
