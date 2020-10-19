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
import com.glob.mytrips.adapters.StateAdapter
import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.domain.dtos.PlaceDto
import com.glob.mytrips.domain.dtos.StateDto
import com.glob.mytrips.domain.dtos.base.PlaceReference
import kotlinx.android.synthetic.main.fragment_place_list.*


class StateListFragment : Fragment(), PlaceListener {


    // TODO: Rename and change types of parameters
    private lateinit var parentListener: OnStateListChanged
    private lateinit var myAdapter: StateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_place_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myAdapter = StateAdapter(listener = this)
        rvMyPlaces.apply {
            adapter = myAdapter
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun changeAdapter(place: PlaceReference) {
        rvMyPlaces.adapter = when (place) {
            is CountryDto -> StateAdapter(listOf(place as StateDto), this)
            is StateDto -> PlaceAdapter(listOf(place as PlaceDto), this)
            else -> null
        }
    }

    fun setupInfo(states: List<StateDto>) {
        myAdapter.updateMyStates(states)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnStateListChanged) {
            parentListener = context
        } else {
            throw ClassCastException(
                "$context must implements PlaceListFragment.OnItemListChanged"
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = StateListFragment()
        const val MOVE_TO_PLACES = 3
    }

    interface OnStateListChanged {
        fun onListChanged(moveTo: Int, onItem: Int)
    }

    override fun onItemClicked(openDetail: Boolean, onItem: Int) {
        parentListener.onListChanged(MOVE_TO_PLACES, onItem)
    }
}