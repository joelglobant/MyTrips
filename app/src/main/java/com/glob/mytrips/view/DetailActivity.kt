package com.glob.mytrips.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.glob.mytrips.R
import com.glob.mytrips.adapters.PhotoAdapter
import com.glob.mytrips.app.BaseActivity
import com.glob.mytrips.app.DataInfoUser
import com.glob.mytrips.contracts.DetailPlaceContract
import com.glob.mytrips.models.PlaceModel
import com.glob.mytrips.presenters.DetailPresenter
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity(), DetailPlaceContract.View {

    private val presenter: DetailPlaceContract.Presenter by lazy {
        DetailPresenter()
    }
    private lateinit var place: PlaceModel
    private var placeId: Int = -1
    private lateinit var comeFrom: String

    companion object {
        fun launchActivity(context: Context, idPlace: Int, from: String) {
            context.startActivity(Intent(context, DetailActivity::class.java).apply {
                putExtra("PLACE_POSITION", idPlace) // TODO: 19/10/2020 PlaceModel y parcelable
                putExtra("FROM", from)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        intent.extras?.let {
            placeId = it.getInt("PLACE_POSITION")
            comeFrom = it.getString("FROM", "N/A")
        }

        DataInfoUser.getInstance().getPlace(placeId)?.let {
            place = it
        }

        rvPhotos.apply {
            adapter = PhotoAdapter(place.photos, this.context)
            layoutManager = GridLayoutManager(this@DetailActivity, 1,
                LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
        setPlaceDetail(place)
        ratePlaceDetail.setIsIndicator(false)
        ratePlaceDetail.setOnClickListener {
            Log.i("TAG", "onCreate: woiedwoidniowenciowe")
        }
    }

    override fun setPlaceDetail(place: PlaceModel) {
        namePlaceDetail.text = place.name
        descPlaceDetail.text = place.description
        ratePlaceDetail.rating = place.rank?.toFloat() ?: 0.0f
        comeFromDetail.text = comeFrom

        // TODO: 15/10/2020 create binary option
        isFavouriteDetail.text = place.favorite.toString()
    }

}