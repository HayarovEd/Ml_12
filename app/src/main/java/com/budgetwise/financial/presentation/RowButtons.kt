
package com.financialtracker.app.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.financialtracker.app.R
import com.budgetwise.financial.domain.model.ElementOffer
import com.budgetwise.financial.domain.model.StatusApplication
import com.budgetwise.financial.domain.model.basedto.BaseState
import com.financialtracker.app.ui.theme.baseBackground
import com.financialtracker.app.ui.theme.border
import com.financialtracker.app.ui.theme.green
import com.financialtracker.app.ui.theme.secondText

@Composable
fun RowButtons(
    modifier: Modifier = Modifier,
    titleOffer: String,
    currentBaseState: BaseState,
    onEvent: (MainEvent) -> Unit,
    name: String,
    pathImage: String,
    rang: String,
    description: String,
    amount: String,
    bet: String,
    term: String,
    showMir: String,
    showVisa: String,
    showMaster: String,
    showQiwi: String,
    showYandex: String,
    showCache: String,
    showPercent: String,
    showTerm: String,
    order:String
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = modifier
                .weight(1f)
                .border(width = 2.dp, color = border, shape = RoundedCornerShape(8.dp))
                .clip(shape = RoundedCornerShape(8.dp))
                .background(color = baseBackground)
                .clickable(onClick = {
                    onEvent(
                        MainEvent.OnChangeStatusApplication(
                            StatusApplication.Offer(
                                currentBaseState = currentBaseState,
                                ElementOffer(
                                    name = name,
                                    pathImage = pathImage,
                                    rang = rang,
                                    description = description,
                                    amount = amount,
                                    bet = bet,
                                    term = term,
                                    showMir = showMir,
                                    showVisa = showVisa,
                                    showMaster = showMaster,
                                    showQiwi = showQiwi,
                                    showYandex = showYandex,
                                    showCache = showCache,
                                    showPercent = showPercent,
                                    showTerm = showTerm,
                                    nameButton = titleOffer,
                                    order = order
                                )
                            )
                        )
                    )
                })
                .padding(vertical = 13.dp)
        ) {
            Icon(
                modifier = modifier.align(alignment = Alignment.Center),
                imageVector = ImageVector.vectorResource(id = R.drawable.carbon_overflow),
                tint = green,
                contentDescription = ""
            )
        }
        Spacer(modifier = modifier.width(9.dp))
        Box(
            modifier = modifier
                .weight(3f)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(color = green)
                .clickable(onClick = {
                    onEvent(
                        MainEvent.OnGoToWeb(
                            urlOffer = order,
                            nameOffer = name
                        )
                    )
                })
                .padding(vertical = 16.dp, horizontal = 16.dp)
        ) {
            Row (
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = stringResource(id = R.string.checkout),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.gotham)),
                        fontWeight = FontWeight(700),
                    ),
                    color = secondText
                )
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_arrow_outward_17),
                    contentDescription = "",
                    tint = secondText)
            }
        }
    }
}
