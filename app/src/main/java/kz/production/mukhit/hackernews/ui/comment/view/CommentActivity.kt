package kz.production.mukhit.hackernews.ui.comment.view

import android.arch.lifecycle.MutableLiveData
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
import kotlinx.android.synthetic.main.activity_comment.*
import kotlinx.android.synthetic.main.fragment_top.*
import kz.production.mukhit.hackernews.R
import kz.production.mukhit.hackernews.data.model.Comment
import kz.production.mukhit.hackernews.data.model.Story
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
    private val LIMIT = 5

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

            if(currentStory?.kids!=null){
                currentCommentList.value = currentStory?.kids
                loadingState.value = STATE.LOADING

                val start = PAGE * LIMIT
                val end = (PAGE+1) * LIMIT
                val maximumPage = currentCommentList.value?.size!! / LIMIT
                loadingState.value = STATE.LOADING
                if(PAGE==maximumPage){
                    val l = currentCommentList.value?.subList(start, currentCommentList.value?.size!!)
                    presenter.onLoadComments(l)
                }
                else if(PAGE < maximumPage){
                    val l = currentCommentList.value?.subList(start,end)
                    presenter.onLoadComments(l)
                }
            }

        }

        swipyrefreshlayout.setOnRefreshListener(this)
        setupEndlessScrolListener()
    }

    override fun setupEndlessScrolListener() {
        val scrollListener = object : EndlessScrollListener(mLayoutManager){

            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {

                if(loadingState.value == STATE.NOT_LOADING) {
                    PAGE++
                    val start = PAGE * LIMIT
                    val end = (PAGE+1) * LIMIT
                    val maximumPage = currentCommentList.value?.size!! / LIMIT
                    loadingState.value = STATE.LOADING
                    if(PAGE==maximumPage){
                        val l = currentCommentList.value?.subList(start, currentCommentList.value?.size!!)
                        presenter.onLoadComments(l)
                    }
                    else if(PAGE < maximumPage){
                        val l = currentCommentList.value?.subList(start,end)
                        presenter.onLoadComments(l)
                    }
                }


            }
        }
        commentRecyclerView.addOnScrollListener(scrollListener as EndlessScrollListener)
    }

    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

    override fun onFragmentAttached() {}

    override fun onFragmentDetached(tag: String) {}

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
                }

            })

            loadingState.value = STATE.NOT_LOADING

        }
    }

    override fun onRefresh(direction: SwipyRefreshLayoutDirection?) {
        mAdapter.clear()
        PAGE = 0
        presenter.onLoadStory(currentStory?.id!!)

    }

    override fun onReplyClick(comment: Comment, position : Int) {
        if(comment.kids != null && comment.id != null && comment.kids.size>0) {
            presenter.onLoadReplies(comment.kids, position,comment.id)
        }
    }

    override fun onSetCommentReplies(replyList: List<Comment>?, position: Int,parentCommentId : Long) {
        replyList?.sortedWith(compareBy({ it.time }))
        mAdapter.addReply(position,parentCommentId,replyList)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    enum class STATE{
        LOADING,
        NOT_LOADING
    }

}