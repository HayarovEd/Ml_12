package com.budgetwise.financial.data

import com.budgetwise.financial.domain.model.basedto.BaseDto
import retrofit2.http.GET

interface ApiServer {
    @GET ("528/db.json")
    suspend fun getDataDb () : BaseDto
}