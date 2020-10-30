package com.glob.mytrips

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.ui.AppBarConfiguration
import com.glob.mytrips.app.BaseActivity
import com.glob.mytrips.contracts.MainMenuContract
import com.glob.mytrips.models.UserModel
import com.glob.mytrips.registers.UserInfoRegistry
import com.glob.mytrips.view.placelist.CountryListFragment
import com.glob.mytrips.view.placelist.PlaceListFragment
import com.glob.mytrips.view.placelist.StateListFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header.*

class MainActivity : BaseActivity(), MainMenuContract.View,
    PlaceListFragment.OnItemListChanged, CountryListFragment.OnCountryListChanged,
    StateListFragment.OnStateListChanged {

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var myPlacesFragment: CountryListFragment
    private lateinit var myDrawerToggle: ActionBarDrawerToggle

    private lateinit var userInfoTemporal: UserModel
    private var userId: Int = -1
    private var countryPosTemp: Int = 0
    private var statePosTemp: Int = 0

    private val presenter: MainMenuContract.Presenter by lazy {
        UserInfoRegistry(this).provide(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setHomeButtonEnabled(true)
        setupDrawer()
        openFragment()
        openConnection()
    }

    private fun setupDrawer() {
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_home, R.id.nav_gallery), drawerLayout
        )
        myDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        ) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                invalidateOptionsMenu()
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                invalidateOptionsMenu()
            }
        }
        drawerLayout.addDrawerListener(myDrawerToggle)
        myDrawerToggle.isDrawerIndicatorEnabled = true
        myDrawerToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (myDrawerToggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    private fun openFragment() {
        // fixme: 29/10/2020 remove it !!!!
        //myPlacesFragment = CountryListFragment.newInstance()
        //addFragmentView(myPlacesFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu_activity, menu)
        return true
    }

    private fun openConnection() {
        val userIdFake = 1
        presenter.getUserAccount(userIdFake)
    }

    override fun showLoader(action: Boolean) {
        if (action) {
            loaderView.visibility = View.VISIBLE
            sendMessage("start loader")
        } else {
            loaderView.visibility = View.GONE
            sendMessage("Finish loader")
        }
    }

    override fun openSettings() {
        sendMessage("Not available yet!")
    }

    override fun onMainInfoLoaded(userInfo: UserModel) {
        with(userInfo) {
            tvProfileName.text = String.format("$name $surname")
            tvProfileBio.text = bio
            tvProfileNickname.text = nickname
            userId = id
        }

        //userInfoTemporal = userInfo

        myPlacesFragment = CountryListFragment.newInstance(userId)
        addFragmentView(myPlacesFragment)

//        val myFragment = supportFragmentManager.findFragmentByTag(myPlacesFragment.tag)
//        myFragment?.let {
//            (it as CountryListFragment).setupInfo(userInfo)
//        }
    }

    override fun onMainInfoLoadedFail(message: String) {
        Log.e(TAG, "onMainInfoLoadedFail: $message")
        errorView.visibility = View.VISIBLE
    }

    override fun onListChanged(moveTo: Int, onItem: Int) {
        when (moveTo) {
            CountryListFragment.MOVE_TO_STATE -> {
                countryPosTemp = onItem
//                DataInfoUser.getInstance().countryPosAt = onItem
//                addFragmentView(StateListFragment.newInstance())
            }
            StateListFragment.MOVE_TO_PLACES -> {
                statePosTemp = onItem
//                DataInfoUser.getInstance().statePosAt = onItem
//                addFragmentView(PlaceListFragment.newInstance())
            }
            PlaceListFragment.MOVE_TO_DETAILS -> {
                //DataInfoUser.getInstance().placePosAt = onItem
//                val placeModel =
//                    userInfoTemporal.generalPlaces[countryPosTemp].states[statePosTemp].places[onItem]
//                val comeFrom =
//                    "${userInfoTemporal.generalPlaces[countryPosTemp].states[statePosTemp].name}," +
//                            " ${userInfoTemporal.generalPlaces[countryPosTemp].name}"
                //DetailActivity.launchActivity(this, onItem, comeFrom)
            }
            else -> CountryListFragment.newInstance(userId)
        }
        addNewList(moveTo)
    }

    private fun addNewList(toList: Int) {
        val myFragment = supportFragmentManager.fragments
        myFragment.let {
//            when (toList) {
//                2 -> (it[1] as StateListFragment).setupInfo(
//                    userInfoTemporal.generalPlaces[countryPosTemp].states
//                )
//                3 -> (it[2] as PlaceListFragment).setupInfo(
//                    userInfoTemporal.generalPlaces[countryPosTemp].states[statePosTemp].places
//                )
//                else -> {
//                    (it[0] as CountryListFragment).setupInfo(userInfoTemporal)
//                }
//            }
        }
    }
}