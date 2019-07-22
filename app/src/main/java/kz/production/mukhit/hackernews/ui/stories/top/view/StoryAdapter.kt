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
import org.xmlpull.v1.XmlPullParser.COMMENT
import java.util.*
import kotlin.collections.ArrayList





class StoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val LOADING = 0
    private val STORY = 1
    private lateinit var storyList : ArrayList<Story>
    private lateinit var storyItemClickListener : StoryItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        when(getItemViewType(position)){
            STORY-> return StoryViewHolder(inflater.inflate(R.layout.item_story_list, parent, false))
            else -> return ProgressBarViewHolder(inflater.inflate(R.layout.item_loading_list, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return storyList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (storyList[position].id != (-1).toLong())
            STORY
        else
            LOADING
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position : Int) {

        when (holder) {
            is StoryViewHolder -> {
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
            is ProgressBarViewHolder->{

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

    inner class ProgressBarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    fun addNullData() {
        val story = Story()
        story.id = -1
        storyList.add(story)
        notifyItemInserted(itemCount - 1)
    }

    fun removeNullData(){
        storyList.removeAt(itemCount - 1)
        notifyItemRemoved(itemCount - 1)
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