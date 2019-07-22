package kz.production.mukhit.hackernews.data.remote.repository.comment

import io.reactivex.Observable
import kz.production.mukhit.hackernews.data.remote.dao.CommentDao
import kz.production.mukhit.hackernews.data.remote.entity.CommentEntity
import javax.inject.Inject

class CommentRepository @Inject internal constructor(private val commentDao: CommentDao) : CommentRepo {

    override fun loadComments(parentId : Long) = Observable.fromCallable({ commentDao.getComments(parentId) })

    override fun loadComments(parentId : Long,limit : Int, offset : Int) = Observable.fromCallable({ commentDao.getComments(parentId,limit, offset) })

    override fun insert(commentEntity: CommentEntity) = Observable.fromCallable({
        commentDao.insert(commentEntity)
        return@fromCallable true
    })

    override fun update(commentEntity: CommentEntity) = Observable.fromCallable({
        commentDao.update(commentEntity)
        return@fromCallable true
    })

}