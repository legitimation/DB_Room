package com.example.sqlite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var helper: RoomHelper
    lateinit var memoAdapter: RecyclerAdapter
    val memoList = mutableListOf<RoomMemo>()
    lateinit var memoDAO:RoomMemoDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helper = Room.databaseBuilder(this, RoomHelper::class.java, "room_db")
            .allowMainThreadQueries()
            .build()

        memoDAO = helper.roomMemoDao()

        memoAdapter = RecyclerAdapter(memoList)

        refreshAdapter()

        with(binding){
            recyclerMemo.adapter = memoAdapter
            recyclerMemo.layoutManager = LinearLayoutManager(this@MainActivity)

            buttonSave.setOnClickListener{
                val content = editMemo.text.toString()
                if(content.isNotEmpty()){
                    val datetime = System.currentTimeMillis()
                    val memo = RoomMemo(content, datetime)

                    memoDAO.insert(memo)
                    refreshAdapter()

                }
            }
        }
    }

    fun refreshAdapter(){
        memoList.clear()
        memoList.addAll(memoDAO.getAll())
        memoAdapter.notifyDataSetChanged()
    }
}