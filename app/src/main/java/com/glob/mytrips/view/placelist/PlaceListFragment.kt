package com.glob.mytrips.view.placelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.glob.mytrips.R
import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.domain.dtos.PlaceDto
import com.glob.mytrips.domain.dtos.StateDto
import com.glob.mytrips.domain.dtos.base.PlaceReference
import com.glob.mytrips.view.placelist.contacts.PlaceListContracts

class PlaceListFragment : Fragment(),
    PlaceListContracts.ViewCountries,
    PlaceListContracts.ViewStates,
    PlaceListContracts.ViewPlaces {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val presenter: PlaceListContracts.Presenter by lazy {
        PlaceListPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_place_list, container, false)
    }

    override fun setCountries(item: List<CountryDto>) {

    }

    override fun setStates(item: List<StateDto>) {
        TODO("Not yet implemented")
    }

    override fun setPlaces(item: List<PlaceDto>) {
        TODO("Not yet implemented")
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, places: List<PlaceReference>) =
            PlaceListFragment().apply {
                arguments = Bundle().apply {
                    //Bundle().putParcelableArrayList("l",places)
                    //putBundle()
                    //putString(ARG_PARAM1, param1)
                }
            }

    }
}