package com.ubaya.s160419037_umc.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.s160419037_umc.R
import com.ubaya.s160419037_umc.model.News
import com.ubaya.s160419037_umc.util.loadImage
import kotlinx.android.synthetic.main.news_list_item.view.*

class NewsListAdapter(val newsList: ArrayList<News>) : RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {
    class NewsViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.news_list_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        with (holder.view){
            if (news.photo_url == "none"){
                progressLoadNewsList.visibility = View.GONE
                imageNewsList.visibility = View.GONE
            }
            else imageNewsList.loadImage(news.photo_url, progressLoadNewsList)

            textNewsTitleList.text = news.title
            textDateNewsList.text = news.date
            textNewsDescriptionList.text = news.description
        }
    }

    override fun getItemCount() = newsList.size

    fun updateNewsList(newList : List<News>){
        newsList.clear()
        newsList.addAll(newList)

        notifyDataSetChanged()
    }
}
