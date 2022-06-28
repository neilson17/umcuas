package com.ubaya.s160419037_umc.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.s160419037_umc.R
import com.ubaya.s160419037_umc.model.News
import com.ubaya.s160419037_umc.viewmodel.NewsListViewModel
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {
    private lateinit var viewModel: NewsListViewModel
    private val newsListAdapter = NewsListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(NewsListViewModel::class.java)

        val list = listOf(News(1, "Mask Required", "Due to this COVID pandemic, you are required to use either one N95 mask or double layered medic 3ply mask when entering our facility.", "https://neilson.id/projectcollege/umc/image/mask.jpg", "April 6th 2022"), News(2,"Free Medicine Delivery", "Our delivery on medicine purchase is now free for customers in Surabaya! The fee is now borne by the government as a form of assistance due to this pandemic.", "https://neilson.id/projectcollege/umc/image/medicine1.jpg", "April 21th 2022"), News(3,"Healthcare Renovation", "Great News incoming! Our west building will be equipped with an elevator now and some new facility.", "https://neilson.id/projectcollege/umc/image/hospital.jpg", "May 1st 2022"), News(4,"Ramadhan Discount", "Get a 10% off discount on all medicines purchase on this week only until June 5th 2022! You have to make the purchase through Ubaya Medical Center application.", "none", "May 28th 2022"), News(5,"New Checkup Category", "Our hospital is now able to check your teeth with our new professional dentist! We make sure your teeth will look as good as diamond!", "none", "June 2nd 2022")
        )
        viewModel.addNews(list)

        viewModel.refresh()

        recViewNews.layoutManager = LinearLayoutManager(context)
        recViewNews.adapter = newsListAdapter

        observeViewModel()


        newsRefreshLayout.setOnRefreshListener {
//            var news = News("a", "b", "https://neilson.id/projectcollege/umc/image/mask.jpg", "May 28th 2022")
//            val list = listOf(news)
//            viewModel.addNews(list)
//            recViewNews.visibility = View.GONE
//            textErrorNews.visibility = View.GONE
//            progressLoadNews.visibility = View.VISIBLE
//            viewModel.refresh()
//            newsRefreshLayout.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.newsLiveData.observe(viewLifecycleOwner, Observer {
            newsListAdapter.updateNewsList(it)
            if (it.isEmpty()) {
                textErrorNews.visibility = View.VISIBLE
                progressLoadNews.visibility = View.VISIBLE
            }
            else {
                textErrorNews.visibility = View.GONE
                progressLoadNews.visibility = View.GONE
            }
        })
//        viewModel.newsLiveData.observe(viewLifecycleOwner){
//            newsListAdapter.updateNewsList(it)
//        }
//        viewModel.newsLoadErrorLiveData.observe(viewLifecycleOwner){
//            textErrorNews.visibility = if (it) View.VISIBLE else View.GONE
//        }
//        viewModel.loadingLiveData.observe(viewLifecycleOwner){
//            if (it){
//                progressLoadNews.visibility = View.VISIBLE
//                recViewNews.visibility = View.GONE
//            }
//            else {
//                progressLoadNews.visibility = View.GONE
//                recViewNews.visibility = View.VISIBLE
//            }
//        }
    }
}