package com.glob.mytrips.view.placelist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.glob.mytrips.R
import com.glob.mytrips.adapters.CountryAdapter
import com.glob.mytrips.adapters.PlaceListener
import com.glob.mytrips.contracts.CountryListContract
import com.glob.mytrips.models.CountryModel
import com.glob.mytrips.registers.UserInfoRegistry
import kotlinx.android.synthetic.main.fragment_place_list.*

class CountryListFragment : Fragment(), PlaceListener, CountryListContract.View {

    private lateinit var myAdapter: CountryAdapter
    private lateinit var parentListener: OnCountryListChanged
    private var myIdUser : Int = -1
    private val presenter: CountryListContract.Presenter by lazy {
        UserInfoRegistry(activity!!).provideCountryList(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            myIdUser = it.getInt(USER)
        }
        presenter.getCountries(myIdUser)
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

    override fun onItemClicked(idSelected: Int) {
        parentListener.onListChanged(MOVE_TO_STATE, idSelected)
    }

    override fun showLoader(action: Boolean) {
        parentListener.showLoader(action)
        Toast.makeText(activity, "gettting countries.!! ",Toast.LENGTH_LONG).show()
    }

    override fun onCountryLoaded(countries: List<CountryModel>) {
        myAdapter.updateMyCountries(countries)
    }

    override fun onCountryLoadedFail(message: String) {
        //TODO("Not yet implemented")
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance(user: Int) = CountryListFragment().apply {
            arguments = Bundle().apply {
                putInt(USER, user)
            }
        }

        const val MOVE_TO_STATE = 2
        private const val USER = "my_user"
    }

    interface OnCountryListChanged {
        fun onListChanged(moveTo: Int, idSelected: Int)
        fun showLoader(action: Boolean)
    }
}