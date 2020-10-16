package com.glob.mytrips.adapters

import com.glob.mytrips.domain.dtos.base.PlaceReference

interface PlaceListener {
    fun onItemClicked(place: PlaceReference, openDetail: Boolean)
}