package com.glob.mytrips.view.placelist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.glob.mytrips.R
import com.glob.mytrips.adapters.CountryAdapter
import com.glob.mytrips.adapters.PlaceAdapter
import com.glob.mytrips.adapters.PlaceListener
import com.glob.mytrips.adapters.StateAdapter
import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.domain.dtos.PlaceDto
import com.glob.mytrips.domain.dtos.StateDto
import com.glob.mytrips.domain.dtos.UserDto
import com.glob.mytrips.domain.dtos.base.PlaceReference
import com.glob.mytrips.view.placelist.contacts.PlaceListContracts
import kotlinx.android.synthetic.main.fragment_place_list.*

class PlaceListFragment : Fragment(), PlaceListener,
    PlaceListContracts.ViewCountries {

    // TODO: Rename and change types of parameters
    private var fragmentType: Int = 0
    private lateinit var myAdapter: RecyclerView.Adapter<*>
    private val presenter: PlaceListContracts.Presenter by lazy {
        PlaceListPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fragmentType = it.getInt(TYPE_OF_PLACE, 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_place_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myAdapter = when (fragmentType) {
            COUNTRY -> CountryAdapter(listener = this)
            STATE -> StateAdapter(listener = this)
            else -> PlaceAdapter(listener = this)
        }

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

    fun setupInfo(user: UserDto) {
        presenter.setPlaces(user.generalPlaces)
        when (fragmentType) {
            COUNTRY -> (myAdapter as CountryAdapter).updateMyCountries(user.generalPlaces)
            STATE -> (myAdapter as StateAdapter).updateMyStates(user.generalPlaces.first().states) // TODO: 13/10/2020 NOT ALLOWED
            else -> (myAdapter as PlaceAdapter).updateMyPlaces(user.generalPlaces.first().states.first().places)// TODO: 13/10/2020 NOT ALLOWED
        }
    }

    override fun setCountries(item: List<CountryDto>) {
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    companion object {
        @JvmStatic
        fun newInstance(typeView: Int) =
            PlaceListFragment().apply {
                arguments = Bundle().apply {
                    putInt(TYPE_OF_PLACE, typeView)
                }
            }

        private const val COUNTRY = 1
        private const val STATE = 2
        private const val PLACE = 3
        private const val TYPE_OF_PLACE = "TYPE_PLACE"
    }

    override fun onItemClicked(place: PlaceReference) {
        myAdapter.notifyDataSetChanged()
        Toast.makeText(activity, "Click on ${place.name()}", Toast.LENGTH_SHORT).show()
    }
}