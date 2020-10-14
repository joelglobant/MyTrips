package com.glob.mytrips

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.glob.mytrips.contracts.MainMenuContract
import com.glob.mytrips.domain.dtos.UserDto
import com.glob.mytrips.view.placelist.PlaceListFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header.*

class MainActivity : AppCompatActivity(), MainMenuContract.View {

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var myPlacesFragment: PlaceListFragment
    private lateinit var myDrawerToggle: ActionBarDrawerToggle

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
        myDrawerToggle = object : ActionBarDrawerToggle(this,
            drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

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
        val firstTime = 1
        myPlacesFragment = PlaceListFragment.newInstance(firstTime)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.apply {
            add(R.id.fragmentContainer, myPlacesFragment, myPlacesFragment::class.java.simpleName)
            addToBackStack(myPlacesFragment.tag)
        }
        transaction.commit()
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
            //nickName
        }

        val myFragment = supportFragmentManager.findFragmentByTag(myPlacesFragment.tag)
        myFragment?.let {
            (it as PlaceListFragment).setupInfo(userInfo)
        }
    }

    override fun onMainInfoLoadedFail(message: String) {
        Log.e(TAG, "onMainInfoLoadedFail: message")
    }

    override fun loadCounties(idUser: Int) {
        //TODO("Not yet implemented")
    }

    private fun sendMessage(msg: String) {
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//            .setAction("Action", null).show()
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    enum class ShapeType {
        OVAL_FILL, OVAL_NOFILL, LINE
    }

}