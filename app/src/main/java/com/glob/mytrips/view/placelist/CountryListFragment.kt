package com.glob.mytrips.view.placelist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.glob.mytrips.R
import com.glob.mytrips.adapters.CountryAdapter
import com.glob.mytrips.adapters.PlaceListener
import com.glob.mytrips.models.UserModel
import kotlinx.android.synthetic.main.fragment_place_list.*


class CountryListFragment : Fragment(), PlaceListener {

    private lateinit var myAdapter: CountryAdapter
    private lateinit var parentListener: OnCountryListChanged

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myAdapter = CountryAdapter(listener = this)
        rvMyPlaces.apply {
            adapter = myAdapter
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(activity)
        }
    }

    fun setupInfo(user: UserModel) {
        myAdapter.updateMyCountries(user.generalPlaces)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnCountryListChanged) {
            parentListener = context
        } else {
            throw ClassCastException(
                "$context must implements PlaceListFragment.OnItemListChanged"
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CountryListFragment()
        const val MOVE_TO_STATE = 2
    }

    interface OnCountryListChanged {
        fun onListChanged(moveTo: Int, onItem: Int)
    }

    override fun onItemClicked( openDetail: Boolean, onItem: Int) {
        parentListener.onListChanged(MOVE_TO_STATE, onItem)
    }
}