package com.financialtracker.app.presentation

import android.annotation.SuppressLint
import android.widget.TextView
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage
import com.financialtracker.app.R
import com.financialtracker.app.data.VALUE_ONE
import com.budgetwise.financial.domain.model.ElementOffer
import com.budgetwise.financial.domain.model.StatusApplication
import com.budgetwise.financial.domain.model.basedto.BaseState
import com.financialtracker.app.ui.theme.baseBackground
import com.financialtracker.app.ui.theme.green
import com.financialtracker.app.ui.theme.secondText
import com.financialtracker.app.ui.theme.white

@SuppressLint("ResourceAsColor")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfferScreen(
    modifier: Modifier = Modifier,
    elementOffer: ElementOffer,
    baseState: BaseState,
    onEvent: (MainEvent) -> Unit,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = baseBackground
                ),
                title = {
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            onEvent(
                                MainEvent.OnChangeStatusApplication(
                                    StatusApplication.Connect(baseState)
                                )
                            )
                        }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                                tint = white,
                                contentDescription = ""
                            )
                        }
                        Spacer(modifier = modifier.width(15.dp))
                        Text(
                            color = white,
                            fontStyle = FontStyle(R.font.gotham),
                            fontSize = 20.sp,
                            fontWeight = FontWeight(400),
                            text = elementOffer.name
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = baseBackground
            ) {
                Button(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(
                        vertical = 16.dp,
                        horizontal = 16.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = green,
                        contentColor = secondText,
                    ),
                    onClick = {
                        onEvent(
                            MainEvent.OnGoToWeb(
                                urlOffer = elementOffer.order,
                                nameOffer = elementOffer.name
                            )
                        )
                    }
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
                            )
                        )
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.baseline_arrow_outward_17),
                            contentDescription = "")
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(color = baseBackground)
                .padding(24.dp)
               // .clip(shape = RoundedCornerShape(25.dp))
                //.background(color = white)
                //.padding(17.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            AsyncImage(
                modifier = modifier
                    .fillMaxWidth(),
                model = elementOffer.pathImage,
                contentScale = ContentScale.FillWidth,
                contentDescription = ""
            )
            Spacer(modifier = modifier.height(35.dp))
            AndroidView(
                modifier = modifier
                    .fillMaxWidth()
                    .clip(shape= RoundedCornerShape(5.dp))
                    .background(color = white)
                    .padding(5.dp),
                factory = { context -> TextView(context) },
                update = {
                    it.setTextColor(R.color.white)
                    it.text = HtmlCompat.fromHtml(
                        elementOffer.description,
                        HtmlCompat.FROM_HTML_MODE_COMPACT
                    )
                }
            )
            Spacer(modifier = modifier.height(13.dp))
            RowData(
                title = stringResource(id = R.string.amount),
                content = elementOffer.amount,
                colorBack = baseBackground
            )
            if (elementOffer.showPercent == VALUE_ONE) {
                Spacer(modifier = modifier.height(8.dp))
                RowData(
                    title = stringResource(id =  R.string.bet),
                    content = elementOffer.bet,
                    colorBack = baseBackground
                )
            }
            if (elementOffer.showTerm == VALUE_ONE) {
                Spacer(modifier = modifier.height(8.dp))
                RowData(
                    title = stringResource(id =  R.string.term),
                    content = elementOffer.term,
                    colorBack = baseBackground
                )
            }
            Spacer(modifier = modifier.height(24.dp))
            RowCard(
                showVisa = elementOffer.showVisa,
                showMaster = elementOffer.showMaster,
                showYandex = elementOffer.showYandex,
                showMir = elementOffer.showMir,
                showQivi = elementOffer.showQiwi,
                showCache = elementOffer.showCache
            )
            /*Spacer(modifier = modifier.height(35.dp))
            Button(
                modifier = modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(25.dp),
                contentPadding = PaddingValues(
                    vertical = 7.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = blue,
                    contentColor = white,
                ),
                onClick = {
                    onEvent(
                        MainEvent.OnGoToWeb(
                            urlOffer = elementOffer.order,
                            nameOffer = elementOffer.name
                        )
                    )
                }
            ) {
                Text(
                    text = stringResource(id = R.string.checkout),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(font.nunito)),
                        fontWeight = FontWeight(700),
                    )
                )
            }*/
        }
    }
}