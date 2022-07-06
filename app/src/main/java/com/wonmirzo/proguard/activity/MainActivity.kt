package com.wonmirzo.proguard.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.wonmirzo.proguard.adapter.HomePostAdapter
import com.wonmirzo.proguard.databinding.ActivityMainBinding
import com.wonmirzo.proguard.helper.SpacesItemDecoration
import com.wonmirzo.proguard.listener.OnBottomReachedListener
import com.wonmirzo.proguard.network.RetrofitHttp
import com.wonmirzo.proguard.network.model.HomePost
import com.wonmirzo.proguard.network.services.PhotoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private lateinit var binding: ActivityMainBinding

    private lateinit var posts: ArrayList<HomePost>
    private val result = HashMap<String, String>()
    private lateinit var postAdapter: HomePostAdapter

    var page: Int = 1
    private val per_page = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        result["page"] = page.toString()
        result["per_page"] = per_page.toString()
        result["order_by"] = "latest"

        posts = ArrayList()
        binding.apply {
            recyclerView.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            recyclerView.setHasFixedSize(true)
            val decoration = SpacesItemDecoration(10)
            recyclerView.addItemDecoration(decoration)
        }
        apiPhotoList()
        refreshPostAdapter(posts)
    }

    private fun refreshPostAdapter(posts: ArrayList<HomePost>) {
        postAdapter = HomePostAdapter(posts, object : OnBottomReachedListener {
            override fun onBottomReached(position: Int) {
                if (page < 4) {
                    page++
                    result["page"] = page.toString()
                    apiPhotoList()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "You have reached all photos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
        binding.recyclerView.adapter = postAdapter
    }

    private fun apiPhotoList() {
        RetrofitHttp.createServiceWithAuth(PhotoService::class.java).listPost(result)
            .enqueue(object : Callback<List<HomePost>> {
                override fun onResponse(
                    call: Call<List<HomePost>>,
                    response: Response<List<HomePost>>
                ) {
                    if (!response.isSuccessful) {
                        return
                    }
                    posts.addAll(response.body()!!)
                    postAdapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<List<HomePost>>, t: Throwable) {
                    Log.e("@@@", "error ${t.message}")
                }
            })
    }
}