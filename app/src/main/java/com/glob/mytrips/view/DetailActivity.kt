package com.glob.mytrips.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.glob.mytrips.R
import com.glob.mytrips.adapters.PhotoAdapter
import com.glob.mytrips.app.BaseActivity
import com.glob.mytrips.contracts.DetailPlaceContract
import com.glob.mytrips.models.PhotoModel
import com.glob.mytrips.models.PlaceModel
import com.glob.mytrips.registers.UserInfoRegistry
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity(), DetailPlaceContract.View {

    private val presenter: DetailPlaceContract.Presenter by lazy {
        UserInfoRegistry(this).provideDetail(this)
    }

    private lateinit var place: PlaceModel
    private var idPlace: Int = -1
    private val photoRecycler: RecyclerView by lazy {
        this.findViewById<RecyclerView>(R.id.rvPhotos)
    }
    private lateinit var photoAdapter: PhotoAdapter

    companion object {
        const val POSITION = "PLACE_POSITION"
        const val COME_FROM = "FROM"
        fun launchActivity(context: Context, idPlace: Int) {
            context.startActivity(Intent(context, DetailActivity::class.java).apply {
                putExtra(POSITION, idPlace)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        intent.extras?.let {
            idPlace = it.getInt(POSITION)
        }
        setupRecycler()
        ratePlaceDetail.setIsIndicator(false)
    }

    override fun onResume() {
        super.onResume()
        if (idPlace >= 0)
            presenter.getCompletePlace(idPlace)
        else
            showError()
    }

    private fun setupRecycler() {
        photoAdapter = PhotoAdapter(arrayListOf(), this)
        photoRecycler.apply {
            adapter = photoAdapter
            layoutManager = GridLayoutManager(
                this@DetailActivity, 1,
                LinearLayoutManager.HORIZONTAL, false
            )
            setHasFixedSize(true)
        }
    }

    override fun setPlaceDetail(place: PlaceModel) {
        this.place = place
        namePlaceDetail.text = place.name
        descPlaceDetail.text = place.description
        ratePlaceDetail.rating = place.rank?.toFloat() ?: 0.0f
        // TODO: 15/10/2020 create binary option
        isFavouriteDetail.text = place.favorite.toString()
    }

    override fun setPhotos(photos: List<PhotoModel>) {
        photoAdapter.updatePhotos(photos)
    }

    override fun showLoader(action: Boolean) {
        loaderView.visibility = if (action) View.VISIBLE else View.GONE
    }

    override fun showError() {
        errorView.visibility = View.VISIBLE
    }
}