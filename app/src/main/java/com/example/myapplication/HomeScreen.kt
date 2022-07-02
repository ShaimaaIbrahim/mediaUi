package com.example.myapplication

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.BottomMenuContent
import com.example.myapplication.data.Feature
import com.example.myapplication.data.standardQuadTo
import com.example.myapplication.ui.theme.*

@ExperimentalFoundationApi
@Composable
fun HomeScreen() {

    Box(
        modifier = Modifier
            .background(Purple700)
            .fillMaxSize()
    ) {
        Column{
            GreetingSection()
            ChipsSection(chips = listOf("Sweet Sleep", "Insomnia", "Depression"))
            CurrentMediation()
            FeaturedSection(
                features = listOf(
                    Feature(
                        "section1",
                        lightColor = lightColor1,
                        darkColor = darkColor1,
                        mediumColor = mediumColor1,
                        iconId = R.drawable.ic_search
                    ),
                    Feature(
                        "section2",
                        lightColor = lightColor2,
                        darkColor = darkColor2,
                        mediumColor = mediumColor2,
                        iconId = R.drawable.ic_search
                    ),
                )
            )
        }
        BottomMenu(items = listOf(
            BottomMenuContent(
                "Home", R.drawable.ic_search
            ),
            BottomMenuContent(
                "Home", R.drawable.ic_search
            ),
            BottomMenuContent(
                "Home", R.drawable.ic_search
            ),
            BottomMenuContent(
                "Home", R.drawable.ic_search
            ),
            BottomMenuContent(
                "Home", R.drawable.ic_search
            )
        ))
    }
}

@Composable
fun GreetingSection(
    name: String = "Philip"
) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = "Welcome $name",
                style = MaterialTheme.typography.h6,
                color = TextWhite
            )
            Text(
                text = "we wish you to have good day!",
                style = MaterialTheme.typography.body1,
                color = TextWhite
            )


        }
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ChipsSection(
    chips: List<String>
) {

    var selectedChipIndex by remember {
        mutableStateOf(0)
    }

    LazyRow {

        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(top = 15.dp, start = 15.dp, bottom = 15.dp)
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) DarkerButtonBlue else ButtonBlue
                    )
                    .padding(15.dp)
            ) {
                Text(text = chips[it], color = TextWhite)
            }
        }
    }

}

@Composable
fun CurrentMediation(
    color: Color = ColorRed
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)

            .padding(
                horizontal = 15.dp,
                vertical = 20.dp
            )
            .fillMaxWidth()
    ) {

        Column() {
            Text(
                text = "Daily Through",
                style = MaterialTheme.typography.body1,
                color = TextWhite
            )
            Text(
                text = "Mediation 10-3 minutes",
                style = MaterialTheme.typography.body1,
                color = TextWhite
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.play),
                contentDescription = "play",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )

        }


    }

}

@ExperimentalFoundationApi
@Composable
fun FeaturedSection(
    features: List<Feature>
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = "Features",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(15.dp)
        )
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {

            items(features.size) {
                featureItem(feature = features[it])
            }
        }
    }
}

@Composable
fun featureItem(
    feature: Feature
) {

    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {

        val width = constraints.maxWidth
        val height = constraints.maxHeight

        //Medium colored path
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

        var mediumColorPath = Path().apply {

            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)

            standardQuadTo(from = mediumColoredPoint1, to = mediumColoredPoint2)
            standardQuadTo(from = mediumColoredPoint2, to = mediumColoredPoint3)

            standardQuadTo(from = mediumColoredPoint3, to = mediumColoredPoint4)
            standardQuadTo(from = mediumColoredPoint4, to = mediumColoredPoint5)

            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)

            close()
        }

        //light colored path
        val lightColoredPoint1 = Offset(0f, height * 0.35f)
        val lightColoredPath2 = Offset(width * 0.1f, height * 0.4f)
        val lightColoredPath3 = Offset(width * 0.3f, height * 0.35f)
        val lightColoredPath4 = Offset(width * 0.65f, height.toFloat())
        val lightColoredPath5 = Offset(width * 1.4f, -height.toFloat()/3f)

        var lightColorPath = Path().apply {

            moveTo(lightColoredPoint1.x, lightColoredPoint1.y)

            standardQuadTo(from = lightColoredPoint1, to = lightColoredPath2)
            standardQuadTo(from = lightColoredPath2, to = mediumColoredPoint3)

            standardQuadTo(from = lightColoredPath3, to = lightColoredPath4)
            standardQuadTo(from = lightColoredPath4, to = lightColoredPath5)

            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)

            close()

        }

        Canvas(modifier = Modifier.fillMaxSize()) {



            drawPath(
                path = mediumColorPath,
                color = feature.lightColor
            )

            drawPath(
                path = lightColorPath,
                color = feature.mediumColor
            )

        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = feature.title,
                style = MaterialTheme.typography.h6,
                lineHeight = 20.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Icon(
                painter = painterResource(id = feature.iconId),

                contentDescription = feature.title,
                tint = Color.White,
                modifier = Modifier
                    .align(alignment = Alignment.BottomStart)
                    .size(25.dp)
            )
            Text(
                text = "Start",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {

                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)

            )
        }
    }
}

@Composable
fun BottomMenu(
    items : List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighLightColor: Color= ButtonBlue,
    activeTextColor: Color= Color.White,
    inactiveTextColor: Color = lightColor1,
    initialSelectedIndex: Int =0
){

    var selectedItemIndex by remember {
         mutableStateOf(initialSelectedIndex)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Bottom,
        modifier = modifier
            .fillMaxWidth()
            .background(Purple700)
            .padding(16.dp)
    ){

        items.forEachIndexed { index, item ->

            BottomMenItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighLightColor = activeHighLightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor) {

                selectedItemIndex = index
            }
        }
    }

}

@Composable
fun BottomMenItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighLightColor: Color= ButtonBlue,
    activeTextColor: Color= Color.White,
    inactiveTextColor: Color = lightColor1,
    onItemClick: ()->Unit
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighLightColor else Color.Transparent)
                .padding(10.dp)
        ){
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription= item.title,
                tint=if(isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text= item.title,
            color = if(isSelected) activeTextColor else inactiveTextColor
        )
    }

}















