package com.example.newsru

     data class Result(
        val articles:List<News>
     )
     data class News(
         val title:String,
         val description:String,
         val url:String,
         val urlToImage:String,
         val publishedAt:String?
     )



