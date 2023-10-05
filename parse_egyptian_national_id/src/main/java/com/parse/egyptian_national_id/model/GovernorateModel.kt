package com.parse.egyptian_national_id.model

import com.google.gson.annotations.SerializedName
data class GovernorateModel(

	@field:SerializedName("GovernorateModel")
	val governorateModel: List<GovernorateModelItem?>? = null
)

data class GovernorateModelItem(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("birth_governorate")
	val birthGovernorate: String? = null
)
