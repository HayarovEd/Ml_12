package com.budgetwise.financial.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.budgetwise.financial.R.drawable
import com.budgetwise.financial.R
import com.budgetwise.financial.R.string
import com.budgetwise.financial.data.VALUE_ONE
import com.budgetwise.financial.domain.model.basedto.BaseDto
import com.budgetwise.financial.domain.model.basedto.BaseState
import com.budgetwise.financial.domain.model.basedto.BaseState.Cards
import com.budgetwise.financial.domain.model.basedto.BaseState.Credits
import com.budgetwise.financial.domain.model.basedto.BaseState.Loans
import com.budgetwise.financial.domain.model.basedto.CardsCredit
import com.budgetwise.financial.domain.model.basedto.CardsDebit
import com.budgetwise.financial.domain.model.basedto.CardsInstallment
import com.budgetwise.financial.ui.theme.blue
import com.budgetwise.financial.ui.theme.grey
import com.budgetwise.financial.ui.theme.white


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectScreen(
    modifier: Modifier = Modifier,
    db: BaseDto,
    baseState: BaseState,
    creditCards: List<CardsCredit>,
    debitCards: List<CardsDebit>,
    installmentCards: List<CardsInstallment>,
    onEvent: (MainEvent) -> Unit,
    onClickPrimary: () -> Unit,
    onClickLoans: () -> Unit,
    onClickCards: () -> Unit,
    onClickCredits: () -> Unit,
    onClickRules: () -> Unit,
    loanLazyState: LazyListState,
    creditLazyState: LazyListState,
    creditCardloanLazyState: LazyListState,
    debitCardLazyState: LazyListState,
    instalmentCardLazyState: LazyListState,
) {
    val title = when (baseState) {
        is Cards -> stringResource(id = string.cards)
        Credits -> stringResource(id = string.credits)
        Loans -> stringResource(id = string.loans)
        is BaseState.WebPrimary -> db.appConfig.namePrimary?: ""
    }

    val columnHeight = remember { mutableStateOf( 0.dp) }
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        /*topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            color = white,
                            fontStyle = FontStyle(R.font.gotham),
                            fontSize = 20.sp,
                            fontWeight = FontWeight(400),
                            text = title
                        )
                        *//*IconButton(onClick = onClickRules) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.info),
                                tint = black,
                                contentDescription = "")
                        }*//*
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = baseBackground
                )
            )
        },*/
        bottomBar = {
            BottomAppBar(
                containerColor = white,
                modifier = modifier
                    //.clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    if (!db.appConfig.showedIconPrimary.isNullOrEmpty()
                        && db.appConfig.showedIconPrimary == VALUE_ONE
                        && !db.appConfig.namePrimary.isNullOrEmpty()
                        && !db.appConfig.urlPrimary.isNullOrEmpty()) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            IconButton(
                                modifier = modifier.size(72.dp),
                                onClick = onClickPrimary) {
                                Image(
                                    imageVector = ImageVector.vectorResource(drawable.money_1),
                                    contentDescription = "")
                            }
                            /*Text(
                                color = if (baseState is Loans) green else lightGray,
                                fontStyle = FontStyle(R.font.onest_400),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Normal,
                                text = db.appConfig.namePrimary
                            )*/
                        }
                    }
                    if (!db.loans.isNullOrEmpty()) {
                        ItemBottomBar(
                            color = if (baseState is Loans) blue else grey,
                            content = stringResource(id = string.loans),
                            icon = ImageVector.vectorResource(id = drawable.loans),
                            columnHeight  = columnHeight,
                            onClick = onClickLoans
                        )
                    }
                    if (!db.cards.isNullOrEmpty()) {
                        ItemBottomBar(
                            color = if (baseState is Cards) blue else grey,
                            icon = ImageVector.vectorResource(id = drawable.cards),
                            content = stringResource(id = string.cards),
                            onClick = onClickCards,
                            columnHeight = columnHeight
                        )
                    }
                    if (!db.credits.isNullOrEmpty()) {
                        ItemBottomBar(
                            color = if (baseState is Credits) blue else grey,
                            icon = ImageVector.vectorResource(id = drawable.credits),
                            content = stringResource(id = string.credits),
                            onClick = onClickCredits,
                            columnHeight = columnHeight
                        )
                    }
                }

            }
        }
    ) { valuePaddings ->
        when (baseState) {
            is Cards -> {
                CardsScreen(
                    valuePaddings = valuePaddings,
                    creditCards = creditCards,
                    debitCards = debitCards,
                    installmentCards = installmentCards,
                    typeCard = baseState.typeCard,
                    onEvent = onEvent,
                    baseState = baseState,
                    creditCardloanLazyState = creditCardloanLazyState,
                    debitCardLazyState = debitCardLazyState,
                    instalmentCardLazyState = instalmentCardLazyState,
                )
            }

            Credits -> {
                Credits(
                    valuePaddings = valuePaddings,
                    credits = db.credits,
                    onEvent = onEvent,
                    baseState = baseState,
                    creditLazyState = creditLazyState
                )
            }

            Loans -> {
                Loans(
                    valuePaddings = valuePaddings,
                    loans = db.loans,
                    onEvent = onEvent,
                    baseState = baseState,
                    loanLazyState = loanLazyState
                )
            }
            is BaseState.WebPrimary -> {
                WebViewScreenPrimary(
                    url = db.appConfig.urlPrimary?:"",
                    offerName = db.appConfig.namePrimary?:"",
                    valuePaddings = valuePaddings,
                    onEvent = onEvent)
            }
        }
    }
}

@Composable
fun ItemBottomBar(
    color: Color,
    icon: ImageVector,
    content: String,
    onClick: () -> Unit,
    columnHeight: MutableState<Dp>
) {
    Column(
        modifier = Modifier.onGloballyPositioned {layoutCoordinates ->
            columnHeight.value = layoutCoordinates.size.height.dp
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = color
            ),
            onClick = onClick) {
            Icon(imageVector = icon, contentDescription = "")
        }
        Text(
            color = color,
            fontStyle = FontStyle(R.font.montserrat),
            fontSize = 11.sp,
            fontWeight = FontWeight(700),
            text = content
        )
    }
}
