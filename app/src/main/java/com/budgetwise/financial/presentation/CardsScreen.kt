package com.financialtracker.app.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.financialtracker.app.R
import com.budgetwise.financial.domain.model.TypeCard
import com.budgetwise.financial.domain.model.basedto.BaseState
import com.budgetwise.financial.domain.model.basedto.CardsCredit
import com.budgetwise.financial.domain.model.basedto.CardsDebit
import com.budgetwise.financial.domain.model.basedto.CardsInstallment
import com.financialtracker.app.ui.theme.baseBackground
import com.financialtracker.app.ui.theme.green
import com.financialtracker.app.ui.theme.white

@Composable
fun CardsScreen(
    modifier: Modifier = Modifier,
    valuePaddings: PaddingValues,
    creditCards: List<CardsCredit>,
    debitCards: List<CardsDebit>,
    installmentCards: List<CardsInstallment>,
    typeCard: TypeCard,
    onEvent: (MainEvent) -> Unit,
    baseState: BaseState,
    creditCardloanLazyState: LazyListState,
    debitCardLazyState: LazyListState,
    instalmentCardLazyState: LazyListState,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = baseBackground)
            .padding(valuePaddings),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!creditCards.isNullOrEmpty()) {
                Button(
                    modifier = modifier
                        .weight(1f),
                    onClick = { onEvent(MainEvent.OnChangeTypeCard(TypeCard.CardCredit)) },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = baseBackground
                    ),
                    shape = RoundedCornerShape(5.dp),
                    contentPadding = PaddingValues(8.dp),
                    border = BorderStroke(
                        width = 1.dp,
                        color = if (typeCard is TypeCard.CardCredit) green else baseBackground
                    )
                ) {
                    Text(
                        color = white,
                        fontStyle = FontStyle(R.font.gotham),
                        fontSize = 13.sp,
                        fontWeight = FontWeight(500),
                        text = stringResource(id = R.string.credit),
                        textAlign = TextAlign.Center
                    )
                }
            }
            if (!debitCards.isNullOrEmpty()) {
                Button(
                    modifier = modifier
                        .weight(1f),
                    onClick = { onEvent(MainEvent.OnChangeTypeCard(TypeCard.CardDebit)) },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = baseBackground
                    ),
                    shape = RoundedCornerShape(5.dp),
                    contentPadding = PaddingValues(8.dp),
                    border = BorderStroke(
                        width = 1.dp,
                        color = if (typeCard is TypeCard.CardDebit) green else baseBackground
                    )
                ) {
                    Text(
                        color = white,
                        fontStyle = FontStyle(R.font.gotham),
                        fontSize = 13.sp,
                        fontWeight = FontWeight(500),
                        text = stringResource(id = R.string.debit),
                        textAlign = TextAlign.Center
                    )
                }
            }
            if (!installmentCards.isNullOrEmpty()) {
                Button(
                    modifier = modifier
                        .weight(1f),
                    onClick = { onEvent(MainEvent.OnChangeTypeCard(TypeCard.CardInstallment)) },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = baseBackground
                    ),
                    shape = RoundedCornerShape(5.dp),
                    contentPadding = PaddingValues(vertical = 8.dp),
                    border = BorderStroke(
                        width = 1.dp,
                        color = if (typeCard is TypeCard.CardInstallment) green else baseBackground
                    )
                ) {
                    Text(
                        color = white,//if (typeCard is TypeCard.CardInstallment) white else cardText,
                        fontStyle = FontStyle(R.font.gotham),
                        fontSize = 13.sp,
                        fontWeight = FontWeight(500),
                        text = stringResource(id = R.string.installment),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        Spacer(modifier = modifier.height(24.dp))
        when (typeCard) {
            TypeCard.CardCredit -> {
                LazyColumn(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    state = creditCardloanLazyState
                ) {
                    items(creditCards) { card ->
                        ItemCreditCard(
                            card = card,
                            onEvent = onEvent,
                            baseState = baseState
                        )
                    }
                }
            }

            TypeCard.CardDebit -> {
                LazyColumn(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    state = debitCardLazyState
                ) {
                    items(debitCards) { card ->
                        ItemDebitCard(
                            card = card,
                            onEvent = onEvent,
                            baseState = baseState,
                        )
                    }
                }
            }

            TypeCard.CardInstallment -> {
                LazyColumn(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    state = instalmentCardLazyState
                ) {
                    items(installmentCards) { card ->
                        ItemInstallmentCard(
                            card = card,
                            baseState = baseState,
                            onEvent = onEvent
                        )
                    }
                }
            }
        }

    }
}
