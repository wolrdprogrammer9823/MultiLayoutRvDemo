package com.example.multilayoutrvdemo.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.multilayoutrvdemo.R
import com.example.multilayoutrvdemo.entity.ApplicationBean
import kotlinx.android.synthetic.main.layout_one.view.*
import kotlinx.android.synthetic.main.layout_three.view.*
import kotlinx.android.synthetic.main.layout_two.view.*

class ApplicationAdapter(context: Context, dataSet: List<ApplicationBean>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mContext = context
    private var mDataSet = dataSet
    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_ONE -> {
                ViewHolderOne(layoutInflater.inflate(R.layout.layout_one, parent, false))
            }

            TYPE_TWO -> {
                ViewHolderTwo(layoutInflater.inflate(R.layout.layout_two, parent, false))
            }

            TYPE_THREE -> {
                ViewHolderThree(layoutInflater.inflate(R.layout.layout_three, parent, false))
            }

            else -> {
                ViewHolderOne(layoutInflater.inflate(R.layout.layout_one, parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_ONE -> {

                val mHolder = holder as ViewHolderOne
                mHolder.itemView.title.text = mDataSet[position].content
            }

            TYPE_TWO -> {

                val mHolder = holder as ViewHolderTwo
                mHolder.itemView.title_two.text = mDataSet[position].content
                setTextViewDrawable(
                    mContext,
                    mHolder.itemView.title_two,
                    mDataSet[position].resId,
                    0
                )
            }

            TYPE_THREE -> {

                val mHolder = holder as ViewHolderThree
                mHolder.itemView.title_three.text = mDataSet[position].content
                setTextViewDrawable(
                    mContext,
                    mHolder.itemView.title_three,
                    mDataSet[position].resId,
                    1
                )
            }
        }
    }

    override fun getItemCount(): Int = mDataSet.size

    override fun getItemViewType(position: Int): Int {
        return when (mDataSet[position].itemType) {
            TYPE_ONE -> {
                TYPE_ONE
            }

            TYPE_TWO -> {
                TYPE_TWO
            }

            TYPE_THREE -> {
                TYPE_THREE
            }

            else -> {
                0
            }
        }
    }

    class ViewHolderOne(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHolderTwo(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHolderThree(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        private const val TYPE_ONE = 0x1
        private const val TYPE_TWO = 0x2
        private const val TYPE_THREE = 0x3
    }

    private fun setTextViewDrawable(
        context: Context,
        textView: AppCompatTextView,
        resId: Int,
        drawablePosition: Int
    ) {
        val drawable = context.resources.getDrawable(resId)
        when (drawablePosition) {
            0 -> {
                //左
                textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
            }
            1 -> {
                //上
                textView.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null)
            }
            2 -> {
                //右
                textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
            }
            3 -> {
                //下
                textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawable)
            }
        }
        textView.compoundDrawablePadding = 2
    }
}