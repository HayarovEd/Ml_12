
package com.financialtracker.app.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.financialtracker.app.R
import com.budgetwise.financial.data.VALUE_ONE
import com.budgetwise.financial.domain.model.ElementOffer
import com.budgetwise.financial.domain.model.StatusApplication
import com.budgetwise.financial.domain.model.basedto.BaseState
import com.budgetwise.financial.domain.model.basedto.Credit
import com.budgetwise.financial.presentation.MainEvent
import com.budgetwise.financial.presentation.RowCard
import com.budgetwise.financial.presentation.RowData
import com.financialtracker.app.ui.theme.cardColor
import com.financialtracker.app.ui.theme.green

@Composable
fun ItemCredit(
    modifier: Modifier = Modifier,
    credit: Credit,
    baseState: BaseState,
    onEvent: (MainEvent) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            //.border(width = 3.dp, color = grey, shape = RoundedCornerShape(15.dp))
            .clip(shape = RoundedCornerShape(25.dp))
            .background(color = cardColor)
            .padding(16.dp)
    ) {
        AsyncImage(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    onEvent(
                        MainEvent.OnChangeStatusApplication(
                            StatusApplication.Offer(
                                currentBaseState = baseState,
                                ElementOffer(
                                    name = credit.name,
                                    pathImage = credit.screen,
                                    rang = credit.score,
                                    description = credit.description,
                                    amount = credit.summPrefix + " " + credit.summMin + " " + credit.summMid + " " + credit.summMax + " " + credit.summPostfix,
                                    bet = credit.percentPrefix + " " + credit.percent + " " + credit.percentPostfix,
                                    term = credit.termPrefix + " " + credit.termMin + " " + credit.termMid + " " + credit.termMax + " " + credit.termPostfix,
                                    showMir = credit.showMir,
                                    showVisa = credit.showVisa,
                                    showMaster = credit.showMastercard,
                                    showQiwi = credit.showQiwi,
                                    showYandex = credit.showYandex,
                                    showCache = credit.showCash,
                                    showPercent = credit.hidePercentFields,
                                    showTerm = credit.hideTermFields,
                                    nameButton = credit.orderButtonText,
                                    order = credit.order
                                )
                            )
                        )
                    )
                },
            model = credit.screen,
            contentScale = ContentScale.FillWidth,
            contentDescription = ""
        )
        Spacer(modifier = modifier.height(13.dp))
        Text(
            color = green,
            fontStyle = FontStyle(R.font.gotham),
            fontSize = 18.sp,
            fontWeight = FontWeight(500),
            text = credit.name
        )
        /*Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                color = black,
                fontStyle = FontStyle(R.font.soyuz_grotesk_bold),
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                text = credit.name
            )
            Rang(
                rang = credit.score
            )
        }*/
        Spacer(modifier = modifier.height(13.dp))
        RowData(
            title = stringResource(id = R.string.amount),
            content = credit.summPrefix +" " + credit.summMin +" " + credit.summMid +" " + credit.summMax +" " + credit.summPostfix
        )
        if (credit.hidePercentFields == VALUE_ONE) {
            Spacer(modifier = modifier.height(8.dp))
            RowData(
                title = stringResource(id = R.string.bet),
                content = credit.percentPrefix +" " + credit.percent +" " + credit.percentPostfix
            )
        }
        if (credit.hideTermFields == VALUE_ONE) {
            Spacer(modifier = modifier.height(8.dp))
            RowData(
                title = stringResource(id = R.string.term),
                content = credit.termPrefix +" "+ credit.termMin +" " + credit.termMid +" " + credit.termMax +" " + credit.termPostfix
            )
        }
        Spacer(modifier = modifier.height(13.dp))
        RowCard(
            showVisa = credit.showVisa,
            showMaster = credit.showMastercard,
            showYandex = credit.showYandex,
            showMir = credit.showMir,
            showQivi = credit.showQiwi,
            showCache = credit.showCash
        )
        Spacer(modifier = modifier.height(13.dp))
        RowButtons(
            titleOffer = credit.orderButtonText,
            onEvent = onEvent,
            currentBaseState = baseState,
            name = credit.name,
            pathImage = credit.screen,
            rang = credit.score,
            description = credit.description,
            amount = credit.summPrefix + " " + credit.summMin + " " + credit.summMid + " " + credit.summMax + " " + credit.summPostfix,
            bet = credit.percentPrefix + " " + credit.percent + " " + credit.percentPostfix,
            term = credit.termPrefix + " " + credit.termMin + " " + credit.termMid + " " + credit.termMax + " " + credit.termPostfix,
            showMir = credit.showMir,
            showVisa = credit.showVisa,
            showMaster = credit.showMastercard,
            showQiwi = credit.showQiwi,
            showYandex = credit.showYandex,
            showCache = credit.showCash,
            showPercent = credit.hidePercentFields,
            showTerm = credit.hideTermFields,
            order = credit.order,
        )
    }
}
