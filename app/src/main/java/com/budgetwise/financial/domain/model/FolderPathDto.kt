package com.budgetwise.financial.domain.model

import com.google.gson.annotations.SerializedName

data class FolderPathDto(
    @SerializedName("actualbackend")
    val folder: String
)
