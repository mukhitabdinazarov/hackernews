package kz.production.mukhit.hackernews.ui.comment.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kz.production.mukhit.hackernews.R
import kz.production.mukhit.hackernews.data.model.Comment
import org.ocpsoft.prettytime.PrettyTime
import java.util.*
import kotlin.collections.ArrayList

class CommentReplyAdapter internal constructor(private var replyList : ArrayList<Comment>): RecyclerView.Adapter<CommentReplyAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)

      return CommentViewHolder(inflater.inflate(R.layout.item_comment_list, parent, false))

    }

    override fun getItemCount(): Int {
        return replyList.size
    }

    override fun onBindViewHolder(viewHolder: CommentViewHolder, position: Int) {
        viewHolder.bind(replyList[position])
    }

    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val commentText = itemView.findViewById<TextView>(R.id.text_comment)
        val authorText = itemView.findViewById<TextView>(R.id.text_post_author)
        val dateText = itemView.findViewById<TextView>(R.id.text_post_date)
        val replayText = itemView.findViewById<TextView>(R.id.replayText)

        fun bind(comment : Comment){
            commentText.text = comment.text
            authorText.text = comment.author
            if(comment.time != null) {
                dateText.text = PrettyTime().format(Date(comment.time!! * 1000))
            }

            var result = 0
            if(comment.kids != null){
                result = comment.kids!!.size

            }
            replayText.visibility = View.GONE

        }
    }
}