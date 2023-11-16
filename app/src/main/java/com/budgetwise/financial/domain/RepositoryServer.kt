package com.budgetwise.financial.domain

import com.budgetwise.financial.data.Resource
import com.budgetwise.financial.domain.model.basedto.BaseDto

interface RepositoryServer {
    suspend fun getDataDb() : Resource<BaseDto>
}