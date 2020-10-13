package com.glob.mytrips

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.glob.mytrips.contracts.MainMenuContract
import com.glob.mytrips.domain.dtos.UserDto
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainMenuContract.View {

    private val TAG = MainActivity::class.java.simpleName

    private val presenter: MainMenuContract.Presenter by lazy {
        UserInfoRegistry().provide(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvTest.setOnClickListener {
            it.isEnabled = !it.isEnabled
        }

        btnTest.setOnClickListener {
            Toast.makeText(this, "Made me a long click!", Toast.LENGTH_LONG).show()
            openConnection()
        }
    }

    private fun openConnection() {
        val userIdFake = 2
        Log.i(TAG, "openConnection: ")
        presenter.getUserAccount(userIdFake)
    }

    override fun showLoader(action: Boolean) {
        if (action)
            Toast.makeText(this, "start loader", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(this, "Finish loader", Toast.LENGTH_SHORT).show()
    }

    override fun openSettings() {
        //TODO("Not yet implemented")
    }

    override fun onMainInfoLoaded(userInfo: UserDto) {
        Log.i(TAG, "onMainInfoLoaded: my onfo here: $userInfo")
        //TODO("Not yet implemented")
    }

    override fun onMainInfoLoadedFail(message: String) {
        Log.e(TAG, "onMainInfoLoadedFail: message")
    }

    override fun loadCounties(idUser: Int) {
        //TODO("Not yet implemented")
    }

//    override fun loadStates(idCounty: Int) {
//        //TODO("Not yet implemented")
//    }
//
//    override fun loadPlaces(idState: Int) {
//        //TODO("Not yet implemented")
//    }
//
//    override fun openPlace(idPlace: Int) {
//        //TODO("Not yet implemented")
//    }


}