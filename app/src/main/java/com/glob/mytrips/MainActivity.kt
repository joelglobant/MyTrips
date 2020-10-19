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
import com.glob.mytrips.domain.dtos.UserDto
import com.glob.mytrips.view.DetailActivity
import com.glob.mytrips.view.placelist.CountryListFragment
import com.glob.mytrips.view.placelist.PlaceListFragment
import com.glob.mytrips.view.placelist.StateListFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header.*

class MainActivity : BaseActivity(), MainMenuContract.View,
    PlaceListFragment.OnItemListChanged, CountryListFragment.OnCountryListChanged,
    StateListFragment.OnStateListChanged {

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var myPlacesFragment: CountryListFragment
    private lateinit var myDrawerToggle: ActionBarDrawerToggle

    private lateinit var userInfoTemporal: UserDto
    private var countryPosTemp: Int = 0
    private var statePosTemp: Int = 0
    private var placePosTemp: Int = 0

    private val presenter: MainMenuContract.Presenter by lazy {
        UserInfoRegistry().provide(this)
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
        myPlacesFragment = CountryListFragment.newInstance()
        addFragmentView(myPlacesFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu_activity, menu)
        return true
    }

    private fun openConnection() {
        val userIdFake = 2
        presenter.getUserAccount(userIdFake)
    }

    override fun showLoader(action: Boolean) {
        if (action)
            sendMessage("start loader")
        else
            sendMessage("Finish loader")
    }

    override fun openSettings() {
        sendMessage("Not available yet!")
    }

    override fun onMainInfoLoaded(userInfo: UserDto) {
        with(userInfo) {
            tvProfileName.text = String.format("$name $surname")
            tvProfileBio.text = bio
            tvProfileNickname.text = nickName
        }
        userInfoTemporal = userInfo

        val myFragment = supportFragmentManager.findFragmentByTag(myPlacesFragment.tag)
        myFragment?.let {
            (it as CountryListFragment).setupInfo(userInfo)
        }
    }

    override fun onMainInfoLoadedFail(message: String) {
        Log.e(TAG, "onMainInfoLoadedFail: $message")
    }

    override fun loadCounties(idUser: Int) {
        //TODO("Not yet implemented")
    }

    enum class ShapeType {
        OVAL_FILL, OVAL_NOFILL, LINE
    }

    override fun onListChanged(moveTo: Int, onItem: Int) {
        when (moveTo) {
            CountryListFragment.MOVE_TO_STATE -> {
                countryPosTemp = onItem
                addFragmentView(StateListFragment.newInstance())
            }
            StateListFragment.MOVE_TO_PLACES -> {
                statePosTemp = onItem
                addFragmentView(PlaceListFragment.newInstance())
            }
            PlaceListFragment.MOVE_TO_DETAILS -> {
                val placeDto =
                    userInfoTemporal.generalPlaces[countryPosTemp].states[statePosTemp].places[onItem]
                val comeFrom =
                    "${userInfoTemporal.generalPlaces[countryPosTemp].states[statePosTemp].name}," +
                            " ${userInfoTemporal.generalPlaces[countryPosTemp].name}"
                DetailActivity.launchActivity(this, placeDto, comeFrom)
            }
            else -> CountryListFragment.newInstance()
        }
        addNewList(moveTo)
    }

    private fun addNewList(toList: Int) {
        val myFragment = supportFragmentManager.fragments
        myFragment.let {
            when (toList) {
                2 -> (it[1] as StateListFragment).setupInfo(
                    userInfoTemporal.generalPlaces[countryPosTemp].states
                )
                3 -> (it[2] as PlaceListFragment).setupInfo(
                    userInfoTemporal.generalPlaces[countryPosTemp].states[statePosTemp].places
                )
                else -> {
                    (it[0] as CountryListFragment).setupInfo(userInfoTemporal)
                }
            }
        }
    }

}