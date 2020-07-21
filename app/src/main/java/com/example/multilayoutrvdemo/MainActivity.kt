package com.example.multilayoutrvdemo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.multilayoutrvdemo.adapter.ApplicationAdapter
import com.example.multilayoutrvdemo.entity.ApplicationBean
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataList = ArrayList<ApplicationBean>()

        val bean1 = ApplicationBean(-1, "第一行代码", 1)

        val bean2 = ApplicationBean(R.mipmap.ic_launcher, "PI", 2)
        val bean3 = ApplicationBean(R.mipmap.ic_launcher, "DA", 2)
        val bean4 = ApplicationBean(R.mipmap.ic_launcher, "CG", 2)
        val bean5 = ApplicationBean(R.mipmap.ic_launcher, "HT", 2)

        val bean6 = ApplicationBean(R.mipmap.ic_launcher, "567", 3)
        val bean7 = ApplicationBean(R.mipmap.ic_launcher, "890", 3)

        dataList.add(bean1)

        dataList.add(bean2)
        dataList.add(bean3)
        dataList.add(bean4)
        dataList.add(bean5)
        dataList.add(bean6)

        dataList.add(bean7)

        val gridLayoutManager = GridLayoutManager(this, 4)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return when (dataList[position].itemType) {
                    1 -> {
                        4
                    }
                    2 -> {
                        1
                    }
                    3 -> {
                        2
                    }
                    else -> {0}
                }
            }
        }
        val adapter = ApplicationAdapter(this, dataList)
        main_rv.layoutManager = gridLayoutManager
        main_rv.adapter = adapter
    }

}