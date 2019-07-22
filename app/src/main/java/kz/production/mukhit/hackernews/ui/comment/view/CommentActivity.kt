package kz.production.mukhit.hackernews.ui.comment.view

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_comment.*
import kotlinx.android.synthetic.main.fragment_top.*
import kz.production.mukhit.hackernews.R
import kz.production.mukhit.hackernews.data.model.Comment
import kz.production.mukhit.hackernews.data.model.Story
import kz.production.mukhit.hackernews.data.remote.entity.CommentEntity
import kz.production.mukhit.hackernews.ui.base.view.BaseActivity
import kz.production.mukhit.hackernews.ui.comment.interactor.CommentMvpInteractor
import kz.production.mukhit.hackernews.ui.comment.presenter.CommentMvpPresenter
import kz.production.mukhit.hackernews.ui.stories.top.view.TopFragment
import kz.production.mukhit.hackernews.utils.EndlessScrollListener
import javax.inject.Inject



class CommentActivity : BaseActivity(),CommentMvpView, HasSupportFragmentInjector, OnReplyClcikListener, SwipyRefreshLayout.OnRefreshListener {

    @Inject
    internal lateinit var presenter: CommentMvpPresenter<CommentMvpView, CommentMvpInteractor>

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    internal lateinit var mAdapter: CommentAdapter

    @Inject
    lateinit var mLayoutManager: LinearLayoutManager

    private var PAGE = 0
    private var LIMIT = 5

    val currentCommentList : MutableLiveData<List<Long>> = MutableLiveData()
    val loadingState : MutableLiveData<STATE> = MutableLiveData()

    private var currentStory: Story? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
        presenter.onAttach(this)

        setUp()
    }

    override fun setUp() {

        if(intent.hasExtra("story")){
            val story = intent.getSerializableExtra("story") as Story
            currentStory = story
            mAdapter.setStory(story)
            mAdapter.setCommentList(ArrayList<Comment>())
            mAdapter.setMap(HashMap())
            mAdapter.addReplyClickListener(this)

            commentRecyclerView.layoutManager = mLayoutManager
            commentRecyclerView.adapter=mAdapter

            if(isNetworkAvailable()) {
                if (currentStory?.kids != null) {
                    currentCommentList.value = currentStory?.kids

                    LIMIT = 5
                    val start = PAGE * LIMIT
                    val end = (PAGE + 1) * LIMIT
                    val maximumPage = currentCommentList.value?.size!! / LIMIT
                    loadingState.value = STATE.LOADING
                    if (PAGE == maximumPage) {
                        val l = currentCommentList.value?.subList(start, currentCommentList.value?.size!!)
                        presenter.onLoadComments(l)
                    } else if (PAGE < maximumPage) {
                        val l = currentCommentList.value?.subList(start, end)
                        presenter.onLoadComments(l)
                    }
                }
            }
            else{
                //loading comments from room db
                loadingState.value = STATE.REMOTE
                presenter.getRemoteComments(story.id,LIMIT,LIMIT*PAGE)
            }
        }

        swipyrefreshlayout.setOnRefreshListener(this)
        setupEndlessScrolListener()


    }

    override fun setupEndlessScrolListener() {
        val scrollListener = object : EndlessScrollListener(mLayoutManager){

            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {

                if(isNetworkAvailable()) {
                    if (loadingState.value == STATE.NOT_LOADING) {
                        PAGE++
                        val start = PAGE * LIMIT
                        val end = (PAGE + 1) * LIMIT
                        val maximumPage = currentCommentList.value?.size!! / LIMIT
                        loadingState.value = STATE.LOADING
                        if (PAGE == maximumPage) {
                            val l = currentCommentList.value?.subList(start, currentCommentList.value?.size!!)
                            presenter.onLoadComments(l)
                        } else if (PAGE < maximumPage) {
                            val l = currentCommentList.value?.subList(start, end)
                            presenter.onLoadComments(l)
                        }
                    }
                }
                else{
                    loadingState.value = STATE.REMOTE
                    PAGE++
                    presenter.getRemoteComments(currentStory?.id,LIMIT,LIMIT*PAGE)
                }
            }
        }
        commentRecyclerView.addOnScrollListener(scrollListener as EndlessScrollListener)
    }

    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

    override fun onFragmentAttached() {}

    override fun onFragmentDetached(tag: String) {}

    override fun onRefresh(direction: SwipyRefreshLayoutDirection?) {
        mAdapter.clear()
        PAGE = 0
        presenter.onLoadStory(currentStory?.id!!)

    }

    override fun setStory(story: Story) {
        mAdapter.setStory(story)

        if(story.kids!=null){
            loadingState.value = STATE.LOADING
            presenter.onLoadComments(story.kids!!)
        }
    }

    override fun onSetComment(commentList : List<Comment>?) {
        swipyrefreshlayout.isRefreshing = false
        if(commentList!=null) {
            commentList.sortedWith(compareBy({ it.time }))
            commentRecyclerView.post(Runnable {

                for (comment in commentList){
                    mAdapter.addItem(comment)

                    val commentEntity = CommentEntity()

                    commentEntity.parentId = currentStory?.id
                    commentEntity.id = comment.id
                    commentEntity.author = comment.author
                    commentEntity.text = comment.text
                    commentEntity.time = comment.time
                    commentEntity.type = comment.type

                    Log.d("myTag",comment.kids?.size.toString())
                    if(comment.kids == null){
                        commentEntity.replyCounts = 0
                    } else{
                        commentEntity.replyCounts = comment.kids?.size
                    }

                    presenter.insertComment(commentEntity)

                }

            })

            loadingState.value = STATE.NOT_LOADING

        }
    }

    override fun onSetRemoteComments(commentList: List<CommentEntity>) {
        swipyrefreshlayout.isRefreshing = false
        commentRecyclerView.post ({
            for (commentEntity in commentList){

                val comment = Comment()

                comment.id = commentEntity.id
                comment.author = commentEntity.author
                comment.text = commentEntity.text
                comment.time = commentEntity.time
                comment.type = commentEntity.type
                comment.parent = currentStory?.id

                //comment kids
                val list = ArrayList<Long>()
                for(i in 0..commentEntity.replyCounts!!-1){
                    list.add(-1)
                }

                comment.kids = list

                mAdapter.addItem(comment)


            }
        })
        loadingState.value = STATE.NOT_LOADING
    }

    override fun onReplyClick(comment: Comment, position : Int) {
        if(isNetworkAvailable()) {
            if (comment.kids != null && comment.id != null && comment.kids?.size!! > 0) {
                presenter.onLoadReplies(comment.kids!!, position, comment.id!!)
            }
        }
        else{
            presenter.getRemoteReplies(comment.id,position)
        }
    }

    override fun onSetCommentReplies(replyList: List<Comment>?, position: Int,parentCommentId : Long) {
        replyList?.sortedWith(compareBy({ it.time }))
        mAdapter.addReply(position,parentCommentId,replyList)

        for (comment in replyList as ArrayList){
            val commentEntity = CommentEntity()

            commentEntity.parentId = parentCommentId
            commentEntity.id = comment.id
            commentEntity.author = comment.author
            commentEntity.text = comment.text
            commentEntity.time = comment.time
            commentEntity.type = comment.type

            presenter.insertComment(commentEntity)
        }
    }

    override fun onSetRemoteReplies(replyList: List<CommentEntity>?, position: Int, commentId: Long) {
        val replyListCopy = ArrayList<Comment>()

        for(reply in replyList as ArrayList){
            val comment = Comment()
            comment.id = reply.id
            comment.parent = commentId
            comment.time = reply.time
            comment.text = reply.text
            comment.author = reply.author
            comment.type = reply.type

            replyListCopy.add(comment)
        }

        mAdapter.addReply(position,commentId,replyListCopy)

    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    enum class STATE{
        LOADING,
        NOT_LOADING,
        REMOTE
    }

}