package com.glob.mytrips.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.glob.mytrips.R
import com.glob.mytrips.adapters.PhotoAdapter
import com.glob.mytrips.app.BaseActivity
import com.glob.mytrips.contracts.DetailPlaceContract
import com.glob.mytrips.models.PlaceModel
import com.glob.mytrips.presenters.DetailPresenter
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity(), DetailPlaceContract.View {

    private val presenter: DetailPlaceContract.Presenter by lazy {
        DetailPresenter(this)
    }
    private lateinit var place: PlaceModel
    private var placePosition: Int = -1
    private lateinit var comeFrom: String

    companion object {
        const val POSITON = "PLACE_POSITION"
        const val COME_FROM = "FROM"
        fun launchActivity(context: Context, placePosition: Int, from: String) {
            context.startActivity(Intent(context, DetailActivity::class.java).apply {
                putExtra(POSITON, placePosition)
                putExtra(COME_FROM, from)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        intent.extras?.let {
            placePosition = it.getInt(POSITON)
            comeFrom = it.getString(COME_FROM, "N/A")
        }

        ratePlaceDetail.setIsIndicator(false)
    }

    override fun onResume() {
        super.onResume()
        if (placePosition >= 0)
            presenter.getPlace(placePosition)
        else
            showError()
    }

    override fun setPlaceDetail(place: PlaceModel) {
        this.place = place
        namePlaceDetail.text = place.name
        descPlaceDetail.text = place.description
        ratePlaceDetail.rating = place.rank?.toFloat() ?: 0.0f
        comeFromDetail.text = comeFrom
        // TODO: 15/10/2020 create binary option
        isFavouriteDetail.text = place.favorite.toString()
        rvPhotos.apply {
            adapter = PhotoAdapter(place.photos, this.context)
            layoutManager = GridLayoutManager(
                this@DetailActivity, 1,
                LinearLayoutManager.HORIZONTAL, false
            )
            setHasFixedSize(true)
        }
    }

    override fun showLoader(action: Boolean) {
        loaderView.visibility = if (action) View.VISIBLE else View.GONE
    }

    override fun showError() {
        errorView.visibility = View.VISIBLE
    }
}