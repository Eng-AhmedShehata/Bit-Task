package com.ashehata.bittask.models.getProfile

import com.ashehata.bittask.models.getProfile.Counts

data class Data (

	val id : Int,
	val full_name : String,
	val profile_picture : String,
	val bio : String,
	val location : String,
	val counts : Counts
)