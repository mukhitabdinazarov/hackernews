package kz.production.mukhit.hackernews.ui.comment.view

import kz.production.mukhit.hackernews.data.model.Comment
import java.text.FieldPosition

interface OnReplyClcikListener {

    fun onReplyClick(comment : Comment,position: Int)
}