package com.glob.mytrips.view.placelist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.glob.mytrips.R
import com.glob.mytrips.adapters.PlaceAdapter
import com.glob.mytrips.adapters.PlaceListener
import com.glob.mytrips.models.CountryModel
import com.glob.mytrips.models.PlaceModel
import com.glob.mytrips.view.placelist.contacts.PlaceListContracts
import kotlinx.android.synthetic.main.fragment_place_list.*

class PlaceListFragment : Fragment(), PlaceListener,
    PlaceListContracts.ViewCountries {

    private lateinit var parentListener: OnItemListChanged
    private lateinit var myAdapter: PlaceAdapter
    private val presenter: PlaceListContracts.Presenter by lazy {
        PlaceListPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myAdapter = PlaceAdapter(listener = this)
        rvMyPlaces.apply {
            adapter = myAdapter
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(activity)
        }
    }

    fun setupInfo(places: List<PlaceModel>) {
        myAdapter.updateMyPlaces(places)
    }

    override fun setCountries(item: List<CountryModel>) {
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItemListChanged) {
            parentListener = context
        } else {
            throw ClassCastException(
                "$context must implements PlaceListFragment.OnItemListChanged"
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PlaceListFragment()
        const val MOVE_TO_DETAILS = 4
    }

    interface OnItemListChanged {
        fun onListChanged(moveTo: Int, onItem: Int)
    }

    override fun onItemClicked(openDetail: Boolean, onItem: Int) {
        parentListener.onListChanged(MOVE_TO_DETAILS, onItem)
    }
}