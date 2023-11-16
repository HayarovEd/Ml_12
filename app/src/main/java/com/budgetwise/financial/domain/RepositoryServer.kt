package com.budgetwise.financial.domain

import com.financialtracker.app.data.Resource
import com.budgetwise.financial.domain.model.basedto.BaseDto

interface RepositoryServer {
    suspend fun getDataDb() : Resource<BaseDto>
}