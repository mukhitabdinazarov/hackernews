package kz.production.mukhit.hackernews.ui.comment.view

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import kz.production.mukhit.hackernews.R
import kz.production.mukhit.hackernews.data.model.Comment
import kz.production.mukhit.hackernews.data.model.Story
import kz.production.mukhit.hackernews.ui.stories.top.view.StoryAdapter
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class CommentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val STORY = 0
    private val COMMENT = 1
    private lateinit var story : Story
    private lateinit var commentList : ArrayList<Comment>
    private lateinit var replayMap : HashMap<Long,List<Comment>>
    private lateinit var replyClickListener : OnReplyClcikListener

    override fun onCreateViewHolder(parent : ViewGroup, position : Int) : RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        when(getItemViewType(position)){
            STORY-> return StoryViewHolder(inflater.inflate(R.layout.item_story_list, parent, false))
            else -> return CommentViewHolder(inflater.inflate(R.layout.item_comment_list, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return commentList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        when(position){
            0-> return 0
            else-> return 1

        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position : Int) {
        when (viewHolder) {
            is StoryViewHolder -> viewHolder.bind(story)
            is CommentViewHolder ->{
                viewHolder.bind(commentList[position-1],position,replyClickListener)

                if(replayMap.containsKey(commentList[position-1].id)){
                    viewHolder.showReplayView(replayMap[commentList[position - 1].id])
                }
                else{
                    viewHolder.hideReplyView()
                }
            }

        }
    }

    inner class StoryViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        val title = itemView.findViewById<TextView>(R.id.text_story_title)
        val points = itemView.findViewById<TextView>(R.id.text_story_points)
        val author = itemView.findViewById<TextView>(R.id.text_story_author)
        val comments = itemView.findViewById<TextView>(R.id.text_view_comments)
        val date = itemView.findViewById<TextView>(R.id.date)
        val containerView = itemView.findViewById<LinearLayout>(R.id.container_post)

        fun bind(story : Story){
            title.text = story.title
            points.text = story.score.toString()
            author.text = story.author
            points.text = story.score.toString()
            if(story.time != null) {
                date.text = PrettyTime().format(Date(story.time!! * 1000))
            }
            comments.visibility = View.GONE
        }

    }

    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val commentText = itemView.findViewById<TextView>(R.id.text_comment)
        val authorText = itemView.findViewById<TextView>(R.id.text_post_author)
        val dateText = itemView.findViewById<TextView>(R.id.text_post_date)
        val replayText = itemView.findViewById<TextView>(R.id.replayText)
        val replayRecyclerView = itemView.findViewById<RecyclerView>(R.id.replayRecyclerView)

        fun bind(comment : Comment,position : Int, replyClickListener : OnReplyClcikListener){
            commentText.text = comment.text
            authorText.text = comment.author
            if(comment.time != null) {
                dateText.text = PrettyTime().format(Date(comment.time!! * 1000))
            }

            var result = 0
            if(comment.kids != null){
                result = comment.kids!!.size

            }

            replayText.setOnClickListener {
                replyClickListener.onReplyClick(comment,position)
            }

            replayText.text = result.toString() + " replies"
        }

        fun showReplayView(replayList : List<Comment>?){
            val list = ArrayList<Comment>()
            if(replayList!=null) {
                list.addAll(replayList)
            }
            replayRecyclerView.visibility = View.VISIBLE
            replayRecyclerView.isNestedScrollingEnabled = false
            val adapter = CommentReplyAdapter(list)
            replayRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
            replayRecyclerView.adapter = adapter
        }

        fun hideReplyView(){
            replayRecyclerView.visibility = View.GONE
        }
    }

    fun setStory(story: Story){
        this.story = story
        notifyDataSetChanged()
    }

    fun setMap(map : HashMap<Long,List<Comment>>){
        this.replayMap = map
        notifyDataSetChanged()
    }
    fun setCommentList(list : ArrayList<Comment>){
        commentList = list
        notifyDataSetChanged()
    }

    fun addItem(comment : Comment){
        commentList.add(comment)
        notifyDataSetChanged()
    }

    fun addReplyClickListener(replyClickListener : OnReplyClcikListener){
        this.replyClickListener = replyClickListener
    }

    fun addReply(position: Int,parentCommentId : Long,replyList : List<Comment>?){
        if(replyList!=null) {
            replayMap.put(parentCommentId,replyList)
            notifyItemChanged(position)
        }
    }

    fun clear(){
        commentList.clear()
        replayMap.clear()
        notifyDataSetChanged()
    }

}