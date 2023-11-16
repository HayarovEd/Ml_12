package com.budgetwise.financial.presentation

import com.budgetwise.financial.domain.model.StatusApplication
import com.budgetwise.financial.domain.model.basedto.BaseDto
import com.budgetwise.financial.domain.model.basedto.CardsCredit
import com.budgetwise.financial.domain.model.basedto.CardsDebit
import com.budgetwise.financial.domain.model.basedto.CardsInstallment


data class MainState(
    val instanceIdMyTracker: String? = null,
    val fireBaseToken: String? = null,
    val gaid: String? = null,
    val message: String = "",
    val affsub1Unswer: String = "",
    val affsub2Unswer: String = "",
    val affsub2UnswerAF: String = "",
    val affsub2UnswerMT: String = "",
    val affsub2UnswerEmpty: String = "",
    val affsub3Unswer: String = "",
    val affsub5Unswer: String = "",
    val dbData: BaseDto? = null,
    val statusApplication: StatusApplication = StatusApplication.Splash/*Web(url = "https://ya.ru/", offerName = "Offer")*/,
    val creditCards: List<CardsCredit> = emptyList(),
    val debitCards: List<CardsDebit> = emptyList(),
    val installmentCards: List<CardsInstallment> = emptyList(),
)
