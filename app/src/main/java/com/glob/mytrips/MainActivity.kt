package com.glob.mytrips

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.ui.AppBarConfiguration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.glob.mytrips.app.BaseActivity
import com.glob.mytrips.contracts.MainMenuContract
import com.glob.mytrips.models.UserModel
import com.glob.mytrips.registers.UserInfoRegistry
import com.glob.mytrips.view.DetailActivity
import com.glob.mytrips.view.placelist.CountryListFragment
import com.glob.mytrips.view.placelist.PlaceListFragment
import com.glob.mytrips.view.placelist.StateListFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.layout_error.view.*
import kotlinx.android.synthetic.main.nav_header.*

class MainActivity : BaseActivity(), MainMenuContract.View,
    PlaceListFragment.OnItemListChanged, CountryListFragment.OnCountryListChanged,
    StateListFragment.OnStateListChanged {

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var myPlacesFragment: CountryListFragment
    private lateinit var myDrawerToggle: ActionBarDrawerToggle
    private var userId: Int = -1
    private var loaderSetup = false

    private val presenter: MainMenuContract.Presenter by lazy {
        UserInfoRegistry(this).provideUser(this)
//        UserInfoRegistry(this).provideMainPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setHomeButtonEnabled(true)
        setupDrawer()
        setupLoader()
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

    private fun setupLoader() {
//        loaderView.swiperefresh.setColorSchemeColors(
//            resources.getColor(R.color.blueVanish), resources.getColor(R.color.colorPrimary),
//            resources.getColor(R.color.colorAccent), resources.getColor(R.color.green)
//        )
//
//        loaderView.swiperefresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
//            override fun onRefresh() {
//                Log.e(javaClass.simpleName, "refresh")
//                Handler().postDelayed({
//                    loaderView.swiperefresh.isRefreshing = false
//                }, 300)
//                presenter.getUserAccount(userId)
//            }
//        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (myDrawerToggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu_activity, menu)
        return true
    }

    private fun openConnection() {
        val userIdFake = 1 // TODO: 05/11/2020 this value need come from preferences or loginActivity!
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
        myPlacesFragment = CountryListFragment.newInstance(userId)
        addFragmentView(myPlacesFragment)
    }

    override fun onMainInfoLoadedFail(message: String) {
        Log.e(TAG, "onMainInfoLoadedFail: $message")
        errorView.visibility = View.VISIBLE
        if (!loaderSetup) {
            //setUpLoader()
            loaderSetup = true
        }
    }

    override fun onListChanged(moveTo: Int, idSelected: Int) {
        when (moveTo) {
            CountryListFragment.MOVE_TO_STATE -> {
                addFragmentView(StateListFragment.newInstance(idSelected))
            }
            StateListFragment.MOVE_TO_PLACES -> {
                addFragmentView(PlaceListFragment.newInstance(idSelected))
            }
            PlaceListFragment.MOVE_TO_DETAILS -> {
                DetailActivity.launchActivity(this, idSelected)
            }
            else -> sendMessage("something was wrong!! \n :/ ")
        }
    }

}