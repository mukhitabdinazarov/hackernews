package kz.production.mukhit.hackernews.data.remote.repository.comment

import io.reactivex.Observable
import kz.production.mukhit.hackernews.data.remote.entity.CommentEntity
import kz.production.mukhit.hackernews.data.remote.entity.StoryEntity

interface CommentRepo {

    fun loadComments(parentId : Long): Observable<List<CommentEntity>>

    fun loadComments(parentId : Long,limit : Int,offset : Int): Observable<List<CommentEntity>>

    fun insert(commentEntity : CommentEntity): Observable<Boolean>

    fun update(commentEntity : CommentEntity): Observable<Boolean>

}