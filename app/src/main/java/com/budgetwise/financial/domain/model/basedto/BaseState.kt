package com.budgetwise.financial.domain.model.basedto

import com.budgetwise.financial.domain.model.TypeCard

sealed class BaseState{
    object Loans: BaseState()
    class Cards(val typeCard: TypeCard): BaseState()
    class WebPrimary (
        val url: String,
        val offerName: String
    ): BaseState()
    object Credits: BaseState()
}
