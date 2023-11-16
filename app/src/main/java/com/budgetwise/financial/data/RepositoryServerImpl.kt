package com.budgetwise.financial.data

import android.util.Log
import com.budgetwise.financial.domain.RepositoryServer
import javax.inject.Inject
import com.budgetwise.financial.domain.model.basedto.BaseDto
import com.budgetwise.financial.data.Resource.Error
import com.budgetwise.financial.data.Resource.Success

class RepositoryServerImpl @Inject constructor(
    private val apiServer: ApiServer
) : RepositoryServer {
    override suspend fun getDataDb(): Resource<BaseDto> {
        return try {
            val folder = apiServer.getDataDb()
            Log.d("DATADB", "dATA DB:${folder.loans.first().id}")
            Success(
                data = folder
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Error(e.message ?: "An unknown error")
        }
    }
}