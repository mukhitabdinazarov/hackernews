package kz.production.mukhit.hackernews.ui.stories.top.view

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import kz.production.mukhit.hackernews.R
import kz.production.mukhit.hackernews.data.model.Story
import org.ocpsoft.prettytime.PrettyTime
import java.util.*
import kotlin.collections.ArrayList

class StoryAdapter : RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

    private lateinit var storyList : ArrayList<Story>
    private lateinit var storyItemClickListener : StoryItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): StoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_story_list, parent, false)

        return StoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return storyList.size
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position : Int) {

        storyList[position].let{

            holder.title.text = it.title
            holder.author.text = it.author
            holder.points.text = it.score.toString()
            holder.comments.text = "Show all (" + it.commentCount + ") comments"

            holder.comments.setOnClickListener {
                storyItemClickListener.onCommentClick(storyList[position])
            }

            holder.containerView.setOnClickListener {
                storyItemClickListener.onItemClick(storyList[position].url.toString())
            }

            if(it.time != null) {
                holder.date.text = PrettyTime().format(Date(it.time!! * 1000))
            }


        }
    }

    class StoryViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        val title = itemView.findViewById<TextView>(R.id.text_story_title)
        val points = itemView.findViewById<TextView>(R.id.text_story_points)
        val author = itemView.findViewById<TextView>(R.id.text_story_author)
        val comments = itemView.findViewById<TextView>(R.id.text_view_comments)
        val date = itemView.findViewById<TextView>(R.id.date)
        val containerView = itemView.findViewById<LinearLayout>(R.id.container_post)

    }

    fun setList(list : ArrayList<Story>){
        storyList = list
        notifyDataSetChanged()
    }

    fun addStoryItemClcikListener(storyItemClickListener : StoryItemClickListener){
        this.storyItemClickListener = storyItemClickListener
    }

    fun setStoryItem(item : Story?){
        if(item != null) {
            storyList.add(item)
            notifyItemInserted(storyList.size - 1)
        }
    }
}