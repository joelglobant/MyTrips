package com.glob.mytrips.utils.custom

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.glob.mytrips.R


class PlaceItemView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    init {
        //inflating our view
        inflate(context, R.layout.place_item_view, this)

        //get the reference of those elements we gave ids
        val textView: TextView = findViewById(R.id.placeId)
        val cardView: CardView = findViewById(R.id.cardViewShadow)
        val textColor: Int

        //recover our custom attr
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.placeItemView)
        textView.text = attributes.getText(R.styleable.placeItemView_title)


        //val params = textView.layoutParams

        //params.horizontalBias = 0.2f // here is one modification for example. modify anything else you want :)
        //myView.setLayoutParams(params)
        val constraintSet = ConstraintSet()
        constraintSet.clone(this)
//for example lets change the vertical bias of tvDownVoteIcon
        //val biasedValue = 0.2f
        //constraintSet.setVerticalBias(R.id.tvDownVoteIcon, biasedValue)
//you can do any other modification and then apply
        //constraintSet.applyTo(findViewById<View>(R.id.activity_constraint) as ConstraintLayout)

        val direction = Direction.values()[attributes.getInt(R.styleable.placeItemView_position, 0)]

        //left -> false
//        val biasedValue = `when (attributes.getInt(R.styleable.placeItemView_position, -1)) {
        val biasedValue = when (direction) {
            Direction.LEFT -> 0.1f //--> left
            Direction.RIGHT -> 0.9f //--> right
            else -> 0.5f //--> middle
        }
        attributes.recycle()

        println("MyM $biasedValue")
        //TransitionManager.beginDelayedTransition()

        constraintSet.connect(
            R.id.cardViewShadow,
            ConstraintSet.END,
            R.id.itemPlace,
            ConstraintSet.END,
            0
        )
        //constraintSet.connect(R.id.cardViewShadow,ConstraintSet.TOP,R.id.check_answer1,ConstraintSet.TOP,0);
        constraintSet.connect(
            R.id.cardViewShadow,
            ConstraintSet.START,
            R.id.itemPlace,
            ConstraintSet.START,
            0
        )

        constraintSet.connect(
            R.id.cardViewShadow,
            ConstraintSet.TOP,
            R.id.itemPlace,
            ConstraintSet.TOP,
            0
        )

        constraintSet.setHorizontalBias(R.id.cardViewShadow, biasedValue)

        constraintSet.applyTo(this)

        textView.setTextColor(Color.RED)


//        constraintSet.clone(context, R.id.activity_constraint)
//        constraintSet.setHorizontalBias(R.id.game_right, biasedValue)
//        constraintSet.applyTo(findViewById<View>(R.id.activity_constraint) as ConstraintLayout)
//
    }

    enum class Direction {
        LEFT,
        RIGHT,
        CENTER
    }

}