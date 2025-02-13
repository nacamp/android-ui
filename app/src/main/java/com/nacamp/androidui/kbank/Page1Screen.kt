package com.nacamp.androidui.kbank

import android.annotation.SuppressLint
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nacamp.androidui.R
import com.nacamp.androidui.StyledCard
import kotlinx.coroutines.launch


private val KbankLightColorScheme = lightColorScheme(
    primary = Color(0xff4b69f5),
//    primary = Purple40,
////    secondary = PurpleGrey40,
//    tertiary =  Color.Blue,
//
//    ///* Other default colors to override
    background = Color(0xFFD9E5F1),
    //   background = Color.Red,
    surface = Color(0xFFF8F9FC),
    surfaceVariant = Color(0xffeef0f6),
//    onPrimary = Color.Blue,
//    onSecondary = Color.Blue,
//    onTertiary = Color.Red,
//    onBackground =Color.Red,
    onSurface = Color(0xFF232D3D),
    //*/
)


val KBankTypography = Typography()

object Spacing {
    val tiny = 4.dp
    val small = 8.dp
    val medium = 12.dp
    val default = 16.dp
    val large = 24.dp
    val extraLarge = 40.dp
}

fun TextStyle.withBold(): TextStyle {
    return this.copy(fontWeight = FontWeight.Bold)
}

fun TextStyle.withAlpha(alpha: Float): TextStyle {
    return this.copy(color = this.color.copy(alpha = alpha))
}

object KBankCardColors {
    @Composable
    fun default() = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    )

    @Composable
    fun primary() = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    )
}


@Composable
fun KBankUITheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = KbankLightColorScheme,
        typography = KBankTypography,
        content = content
    )
}

@Composable
fun Page1Screen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(Spacing.default)
            .fillMaxSize()
            //.border(1.dp, Color.Red)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(Spacing.default)
    ) {
        BankCardA(colors = KBankCardColors.default())
        BankCardBCLayout()
        BankCardD()
        BankCardE(colors = KBankCardColors.default())
    }
}

@Composable
fun BankCardBCLayout(modifier: Modifier = Modifier, initialIsExpanded: Boolean = false) {
    var isExpanded by remember { mutableStateOf(initialIsExpanded) }

    val bankCardBHeight = 100.dp
    val bankCardCExpandedHeight = 320.dp
    val bankCardCCollapsedHeight = 220.dp

    val boxHeight = if (isExpanded) {
        bankCardBHeight + bankCardCExpandedHeight
    } else {
        bankCardBHeight + bankCardCCollapsedHeight
    }
    Row {
        Box(
            modifier = Modifier
                //.border(1.dp, Color.Blue)
                .fillMaxWidth()
                .height(boxHeight)
        ) {
            BankCardB(
                modifier = modifier
                    .align(Alignment.TopStart),
                colors = KBankCardColors.primary(),
                onToggleExpand = { isExpanded = !isExpanded }, // 상태 변경
                isExpanded = isExpanded
            )
            BankCardC(
                modifier = modifier
                    .fillMaxWidth()
                    //.fillMaxSize() //=> StyledCard의 modifier.wrapContentHeight() 사용시 하단에 여백이 생김
                    .align(Alignment.TopCenter)
                    .offset {
                        IntOffset(
                            0,
                            if (isExpanded) 150.dp.roundToPx() else 50.dp.roundToPx()
                        )
                    },
                colors = KBankCardColors.default()
            )
        }
    }

}


@Composable
fun BankCardA(modifier: Modifier = Modifier, colors: CardColors = KBankCardColors.default()) {
    StyledCard(modifier = modifier, colors = colors) {
        Row {
            Icon(
                painter = painterResource(id = R.drawable.ic_bank),
                contentDescription = "Bank Icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(Spacing.default)
                    .size(30.dp)
                    .align(Alignment.CenterVertically)
            )

            Column(
                modifier = Modifier
                    .padding(Spacing.default)
                    .weight(3f) // 나머지 공간(2배)을 Column에 할당
            ) {
                Text(
                    text = "코드K 정기예금",
                    style = it.withAlpha(0.5f),
                )
                Text(
                    text = "1개월만 보관하셔도",
                    style = it.withBold()
                )
                Text(
                    text = "연 2.90%(세전) 이자 드려요",
                    style = it.withBold()
                )
                Text(
                    text = "(2024.12.09, 세전)",
                    style = it.withAlpha(0.5f),
                )
            }

            Icon(
                imageVector = Icons.Default.Close, // Material Design X 아이콘
                contentDescription = "Close Button",
                modifier = Modifier
                    .padding(Spacing.default)
                    .size(20.dp) // 아이콘 크기
                    .clickable { }, // 클릭 동작
                tint = it.color.copy(alpha = 0.5f)
            )
        }
    }
}

@Composable
fun BankCardB(
    modifier: Modifier = Modifier, colors: CardColors, onToggleExpand: () -> Unit,
    isExpanded: Boolean
) {
    StyledCard(modifier = modifier, colors = colors) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Kbank",
                    modifier = Modifier
                        .padding(Spacing.default)
                        .weight(1f),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = it.color.copy(alpha = 0.7f),
                        fontWeight = FontWeight.Bold,
                    )
                )

                Box(
                    modifier = Modifier
                        .width(30.dp)
                        .height(20.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                                .compositeOver(MaterialTheme.colorScheme.onSurface)
                        )
                        .clickable { onToggleExpand() }
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (isExpanded) R.drawable.ic_arrow_down else R.drawable.ic_arrow_up
                        ),
                        contentDescription = "Bank Icon",
                        tint = it.color.copy(alpha = 0.5f),
                        modifier = Modifier
                            .padding(0.dp)
                            .size(24.dp)
                            .align(Alignment.Center)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
            }
            Row(
                modifier = Modifier
                    .padding(
                        start = Spacing.default,
                        top = Spacing.tiny,
                        bottom = Spacing.extraLarge
                    )
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CircleIcon()
                CircleIcon()
                CircleIcon()
                CircleIcon()
            }
            //Spacer(modifier = Modifier.padding(Spacing.extraLarge))
        }
    }
}

@Composable
fun BankCardC(modifier: Modifier = Modifier, colors: CardColors = KBankCardColors.default()) {
    val contentList: List<@Composable () -> Unit> = listOf(
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth() // Row를 가로로 꽉 채우기
                    .padding(horizontal = Spacing.default, vertical = 0.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bank), // Vector Drawable 리소스 ID
                    contentDescription = "Bank Icon",
                    tint = Color(0xFF4560F4),
                    modifier = Modifier
                        .padding(5.dp)
                        .size(30.dp)
                        .align(Alignment.CenterVertically)
                )
                Column(
                    modifier = Modifier.padding(Spacing.default)
                ) {
                    Row {
                        Text(
                            text = "홍길동",
                        )
                        Text(
                            text = " | 1일전",
                            //style = it.withAlpha(0.5f),
                        )
                    }
                    Text(
                        text = "+110,000원",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                        ),
                    )
                }
            }
        },
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth() // Row를 가로로 꽉 채우기
                    .padding(horizontal = Spacing.default, vertical = 0.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bank), // Vector Drawable 리소스 ID
                    contentDescription = "Bank Icon",
                    tint = Color(0xFF4560F4),
                    modifier = Modifier
                        .padding(5.dp)
                        .size(30.dp)
                        .align(Alignment.CenterVertically)
                )
                Column(
                    modifier = Modifier.padding(Spacing.default)
                ) {
                    Row {
                        Text(
                            text = "홍길동",
                        )
                        Text(
                            text = " | 2일전",
                            //style = it.withAlpha(0.5f),
                        )
                    }
                    Text(
                        text = "+110,000원",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                        ),
                    )
                }
            }
        },
        { Text("Card 3", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
    )

    StyledCard(modifier = modifier, colors = colors) {
        Column {
            Column(modifier = Modifier.padding(Spacing.default)) {
                Row {
                    Text(
                        text = "생활통장 0096",
                        style = it.withAlpha(0.5f),
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bank), // Vector Drawable 리소스 ID
                        contentDescription = "Bank Icon",
                        tint = it.color.copy(alpha = 0.5f),
                        modifier = Modifier
                            .padding(5.dp)
                            .size(16.dp)
                    )
                    LabelChip("마이너스")
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Default.MoreVert, // 세로형 삼점 아이콘
                        contentDescription = "More Options",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { /* 메뉴 동작 */ },
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )

                }
                Text(
                    text = "239,444원",
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                    modifier = Modifier.padding(0.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Spacing.default)
                ) {
                    CDefaultButton("가져오기", modifier = Modifier.weight(1f))
                    CDefaultButton("이체하기", modifier = Modifier.weight(1f))
                }
            }

            HorizontalDivider(
                thickness = 1.dp,
                color = Color.LightGray
            )
            ScrollableCardPager(contentList)
        }
    }
}

@Composable
fun BankCardD(modifier: Modifier = Modifier, colors: CardColors = KBankCardColors.default()) {
    StyledCard(modifier = modifier, colors = colors) {
        Row(
//            modifier = Modifier.border(1.dp, Color.Red),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(Spacing.default)
                    .weight(13f)
            ) {
                Text(
                    text = "플러스박스에 모인 이자",
                    style = it.withAlpha(0.5f),
                )
                Text(
                    text = "300원",
                    style = it.withBold()
                )
            }
            ShakingIconWithAnimation(modifier = modifier)
            Spacer(modifier = Modifier.padding(Spacing.default))
        }
    }
}

@Composable
fun BankCardE(modifier: Modifier = Modifier, colors: CardColors = KBankCardColors.default()) {
    StyledCard(modifier = modifier, colors = colors) {
        Column {
            Column(modifier = Modifier.padding(Spacing.default)) {
                Row {
                    Text(
                        text = "계좌",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                        )
                    )
                }
                BankCardERow(
                    column1 = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bank), // Vector Drawable 리소스 ID
                            contentDescription = "Bank Icon",
                            tint = Color(0xFF4560F4),
                            modifier = Modifier
                                .padding(5.dp)
                                .size(30.dp)
                        )
                    },
                    column2Text1 = "급할 때 쓸 여윳돈",
                    column2Text2 = "0원",
                    column3 = {
                        COutlinedButton("더하기", modifier = modifier.padding(Spacing.tiny))
                    })
                BankCardERow(
                    column1 = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bank), // Vector Drawable 리소스 ID
                            contentDescription = "Bank Icon",
                            tint = Color(0xFF4560F4),
                            modifier = Modifier
                                .padding(5.dp)
                                .size(30.dp)
                        )
                    },
                    column2Text1 = "급할 때 쓸 여윳돈",
                    column2Text2 = "0원",
                    column3 = {
                        COutlinedButton("더하기", modifier = Modifier)
                    })
                BankCardERow(
                    column1 = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bank), // Vector Drawable 리소스 ID
                            contentDescription = "Bank Icon",
                            tint = Color(0xFF4560F4),
                            modifier = Modifier
                                .padding(5.dp)
                                .size(30.dp)
                        )
                    },
                    column2Text1 = "코드K자유적금",
                    column2Text2 = "9,600,000원",
                )
            }

            HorizontalDivider(
                thickness = 1.dp,
                color = Color.LightGray
            )
            Column(modifier = Modifier.padding(Spacing.default)) {
                Row {
                    Text(
                        text = "대출",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                        )
                    )
                }
                BankCardERow(
                    column1 = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bank), // Vector Drawable 리소스 ID
                            contentDescription = "Bank Icon",
                            tint = Color(0xFF4560F4),
                            modifier = Modifier
                                .padding(5.dp)
                                .size(30.dp)
                        )
                    },
                    column2Text1 = "마이너스 통장 대출",
                    column2Text2 = "49,998,726원",
                )
            }

            HorizontalDivider(
                thickness = 1.dp,
                color = Color.LightGray
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth() // Row를 가로로 꽉 채우기
                    .padding(vertical = Spacing.default),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "전체보기",
                    style = it.withAlpha(0.5f),
                )
                Icon(
                    imageVector = Icons.Filled.ChevronRight, // ✅ 기본 chevron_right 아이콘
                    contentDescription = "Chevron Right",
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    modifier = Modifier.size(24.dp) // 크기 설정
                )
            }
        }
    }
}

@Composable
fun BankCardERow(
    modifier: Modifier = Modifier,    // Row의 Modifier
    column1: @Composable () -> Unit, // column1의 컴포저블 콘텐츠
    column2Text1: String,            // column2의 첫 번째 텍스트
    column2Text2: String,            // column2의 두 번째 텍스트
    column3: (@Composable () -> Unit)? = null, // column3의 컴포저블 콘텐츠 (nullable)
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.tiny, vertical = Spacing.tiny),
        verticalAlignment = Alignment.CenterVertically // Row 내에서 세로 중앙 정렬
    ) {
        Box(
            modifier = Modifier
                .padding(end = Spacing.default)
                .weight(1f),
            //.border(1.dp, Color.Red),
            contentAlignment = Alignment.Center // Box 안에서 콘텐츠 중앙 정렬
        ) {
            column1() // column1의 컴포저블 호출
        }

        Column(
            modifier = Modifier
                .padding(end = Spacing.default)
                .weight(3f) // Column 2는 넓게 설정
        ) {
            Text(
                text = column2Text1,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f) // 흐림 효과
                )
            )
            Text(
                text = column2Text2,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
        }

        if (column3 != null) {
            Box(
                modifier = Modifier
                    .padding(start = Spacing.default)
                    .height(24.dp)
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                column3()
            }
        } else {
            Spacer(
                modifier = Modifier
                    .padding(start = Spacing.default)
                    .weight(1f) // 동일한 공간 차지
            )
        }
    }
}

// 주의: contents 들의 높이는 같아야 한다.
@Composable
fun ScrollableCardPager(
    contents: List<@Composable () -> Unit> // ✅ 여러 개의 콘텐츠를 리스트로 전달받음
) {
    val pagerState = rememberPagerState { contents.size }
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            //.fillMaxSize() //=> StyledCard의 modifier.wrapContentHeight() 사용시 하단에 여백이 생김
            //.border(1.dp, Color.Red)
            .padding(0.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(0.dp),
        ) { page ->
            Box(
                modifier = Modifier
                    .padding(0.dp)
                    //.border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                contents[page]() // ✅ 전달된 리스트에서 현재 페이지의 Composable 표시
            }
        }
        IndicatorRow(
            selectedIndex = pagerState.currentPage,
            total = contents.size,
            onIndicatorClick = { index ->
                coroutineScope.launch {
                    pagerState.animateScrollToPage(
                        index,
                        animationSpec = tween(durationMillis = 500)
                    )
                }
            }
        )
    }
}


@Composable
fun IndicatorRow(
    selectedIndex: Int,
    total: Int = 4,
    onIndicatorClick: (Int) -> Unit // ✅ 클릭 이벤트 콜백 추가
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(total) { index ->
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .clickable { onIndicatorClick(index) } // ✅ 클릭 시 인덱스 전달
                    .then(
                        if (index == selectedIndex) {
                            Modifier.background(Color.DarkGray)
                        } else {
                            Modifier.border(2.dp, Color.LightGray, CircleShape)
                        }
                    )
            )
            Spacer(modifier = Modifier.width(4.dp)) // 원 간 간격
        }
    }
}

@Composable
fun ShakingIconWithAnimation(modifier: Modifier) {
    val offsetY = remember { androidx.compose.animation.core.Animatable(0.dp, Dp.VectorConverter) }
    LaunchedEffect(Unit) {
        while (true) {
            offsetY.animateTo(
                targetValue = 20.dp,
                animationSpec = tween(durationMillis = 600, easing = LinearEasing)
            )
            offsetY.animateTo(
                targetValue = 0.dp,
                animationSpec = tween(durationMillis = 600, easing = LinearEasing)
            )
        }
    }

    Icon(
        painter = painterResource(id = R.drawable.ic_bank), // Vector Drawable 리소스 ID
        contentDescription = "Bank Icon",
        tint = Color(0xFF4560F4),
        modifier = modifier
            .padding(top = 0.dp)
            .padding(top = offsetY.value)
            .size(20.dp)
    )
}

@Composable
fun COutlinedButton(text: String, modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick = {},
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent, // 배경색을 투명하게 설정
            contentColor = MaterialTheme.colorScheme.onSurface // 텍스트 색상
        ),
        border = BorderStroke(1.dp, Color.LightGray), // 아웃라인 색상 및 두께 설정
        contentPadding = PaddingValues(0.dp), // 내부 여백 제거
        modifier = Modifier.padding(0.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy( // 텍스트 스타일 조정
//                color = MaterialTheme.colorScheme.primary,
//                lineHeight = 16.sp, // 줄 간격 줄이기
//                textAlign = TextAlign.Center // 텍스트 중앙 정렬
            ),
            modifier = Modifier.padding(0.dp)
        )
    }
}

@Composable
fun CDefaultButton(text: String, modifier: Modifier = Modifier) {
    Button(
        onClick = {},
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        modifier = modifier
    ) {
        Text(text)
    }
}

@Composable
fun LabelChip(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall.copy(
            color = MaterialTheme.typography.bodySmall.color.copy(alpha = 0.5f)
        ),
        textAlign = TextAlign.Center, // 텍스트 중앙 정렬
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(30)
            )
            .padding(horizontal = 10.dp, vertical = 2.dp)
    )
}

@Composable
fun CircleIcon() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(40.dp) // 원의 크기
                .clip(RoundedCornerShape(16.dp))
//            .clip(CircleShape)
                .background(
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                        .compositeOver(MaterialTheme.colorScheme.onPrimary)
                )
                .padding(5.dp) // 아이콘 내부 패딩
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bank), // Vector Drawable 리소스 ID
                contentDescription = "Bank Icon",
                //tint = MaterialTheme.colorScheme.primary, // 아이콘 색상
                modifier = Modifier
                    .padding(5.dp)
                    .size(30.dp) // 아이콘 크기
                    .align(Alignment.Center) // 아이콘을 중앙 정렬
            )
        }
        Text("상품가입")
    }

}

@Preview(showBackground = true)
@Composable
fun Page1ScreenPreview() {
    KBankUITheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Page1Screen()
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun BankCardERowPreview() {
    KBankUITheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            BankCardERow(
                column1 = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bank), // Vector Drawable 리소스 ID
                        contentDescription = "Bank Icon",
                        tint = Color(0xFF4560F4),
                        modifier = Modifier
                            .padding(5.dp)
                            .size(20.dp)
                    )
                },
                column2Text1 = "급할 때 쓸 여윳돈",
                column2Text2 = "0원",
                column3 = {
                    COutlinedButton("더하기", modifier = Modifier.padding(Spacing.tiny))
                })
        }
    }
}


//@Preview(showBackground = true)
@Composable
fun BankCardEPreview() {
    KBankUITheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            BankCardE(modifier = Modifier.padding(Spacing.default))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BankCardDPreview() {
    KBankUITheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            BankCardD(modifier = Modifier.padding(Spacing.default))
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun BankCardCPreview() {
    KBankUITheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            BankCardC(modifier = Modifier.padding(Spacing.default))
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun BankCardAPreview() {
    KBankUITheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            BankCardA(modifier = Modifier.padding(Spacing.default))
        }
    }
}
