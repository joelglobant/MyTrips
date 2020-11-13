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
import com.glob.mytrips.contracts.PlaceContract
import com.glob.mytrips.models.PlaceModel
import com.glob.mytrips.registers.UserInfoRegistry
import kotlinx.android.synthetic.main.fragment_place_list.*

class PlaceListFragment : Fragment(), PlaceListener,
    PlaceContract.View {

    private lateinit var parentListener: OnItemListChanged
    private lateinit var myAdapter: PlaceAdapter
    private var idPlace: Int = -1
    private val presenter: PlaceContract.Presenter by lazy {
        UserInfoRegistry(activity!!).providePlaceList(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idPlace = it.getInt(STATE_ID)
        }
        presenter.getPlaces(idPlace)
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
        fun newInstance(idState: Int) =
            PlaceListFragment().apply {
                arguments = Bundle().apply {
                    putInt(STATE_ID, idState)
                }
            }
        const val MOVE_TO_DETAILS = 4
        private const val STATE_ID = "state_id"
    }

    interface OnItemListChanged {
        fun onListChanged(moveTo: Int, idSelected: Int)
        fun showLoader(action: Boolean)
    }

    override fun onItemClicked(idSelected: Int) {
        parentListener.onListChanged(MOVE_TO_DETAILS, idSelected)
    }

    override fun showLoader(action: Boolean) {
        parentListener.showLoader(action)
    }

    override fun onPlacesLoaded(places: List<PlaceModel>) {
        myAdapter.updateMyPlaces(places)
    }

    override fun onPlacesLoadedFail(message: String) {
        TODO("Not yet implemented")
    }
}