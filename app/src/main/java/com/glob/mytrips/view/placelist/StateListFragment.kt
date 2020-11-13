package com.glob.mytrips.view.placelist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.glob.mytrips.R
import com.glob.mytrips.adapters.PlaceListener
import com.glob.mytrips.adapters.StateAdapter
import com.glob.mytrips.contracts.StateListContract
import com.glob.mytrips.models.StateModel
import com.glob.mytrips.registers.UserInfoRegistry
import kotlinx.android.synthetic.main.fragment_place_list.*

class StateListFragment : Fragment(), PlaceListener, StateListContract.View {

    private lateinit var parentListener: OnStateListChanged
    private lateinit var myAdapter: StateAdapter
    private var idCountry: Int = -1
    private val presenter: StateListContract.Presenter by lazy {
        UserInfoRegistry(activity!!).provideStateList(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idCountry = it.getInt(COUNTRY_ID)
        }
        presenter.getStates(idCountry)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        fun newInstance(idCountry: Int) = StateListFragment().apply {
            arguments = Bundle().apply {
                putInt(COUNTRY_ID, idCountry)
            }
        }

        const val MOVE_TO_PLACES = 3
        private const val COUNTRY_ID = "country_id"
    }

    interface OnStateListChanged {
        fun onListChanged(moveTo: Int, onItem: Int)
        fun showLoader(action: Boolean)
    }

    override fun onItemClicked(idSelected: Int) {
        parentListener.onListChanged(MOVE_TO_PLACES, idSelected)
    }

    override fun showLoader(action: Boolean) {
        parentListener.showLoader(action)
    }

    override fun onStatesLoaded(states: List<StateModel>) {
        myAdapter.updateMyStates(states)
    }

    override fun onStatesLoadedFail(message: String) {
        //TODO("Not yet implemented")
    }
}