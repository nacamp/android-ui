package com.nacamp.androidui.kbank

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nacamp.androidui.R
import com.nacamp.androidui.StyledCard
import com.nacamp.androidui.ui.theme.Typography


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


object Paddings {
    val spacingXxs = 4.dp
    val spacingXs = 8.dp
    val spacingSm = 12.dp
    val spacing = 16.dp
    val spacingLg = 20.dp
    val spacingXl = 24.dp
    val spacing2xl = 32.dp
    val spacing3xl = 40.dp
    val spacing4xl = 60.dp
    val spacing5xl = 80.dp

    val titleBarHeight = 56.dp
}

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

//    @Composable
//    fun Primary() = CardDefaults.cardColors(
//        containerColor = MaterialTheme.colorScheme.primary,
//        contentColor = MaterialTheme.colorScheme.onPrimary
//    )
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
    Column {
        BankCardA(modifier = modifier.padding(Spacing.default), colors = KBankCardColors.default())
        BankCardC(
            modifier = modifier.padding(Spacing.default),
            colors = KBankCardColors.default()
        )
    }
}

@Composable
fun BankCardA(modifier: Modifier = Modifier, colors: CardColors) {
    StyledCard(modifier = modifier, colors = colors) {
        Row{
            Icon(
                painter = painterResource(id = R.drawable.bank), // Vector Drawable 리소스 ID
                contentDescription = "Bank Icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = modifier
                    .padding(5.dp)
                    .size(30.dp)
                    .align(Alignment.CenterVertically)
            )

            Column(
                modifier = modifier
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
                modifier = modifier
                    .padding(Spacing.tiny)
                    .size(20.dp) // 아이콘 크기
                    .clickable { }, // 클릭 동작
                tint = it.color.copy(alpha = 0.5f)
            )
        }
    }
}

@Composable
fun BankCardC(modifier: Modifier = Modifier, colors: CardColors) {
    StyledCard(modifier = modifier, colors = colors) {
        Column {
            Column(modifier = Modifier.padding(Spacing.default)) {
                Row {
                    Text(
                        text = "생활통장 0096",
                        style = it.withAlpha(0.5f),
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.bank), // Vector Drawable 리소스 ID
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
                    DefaultButton("가져오기", modifier = Modifier.weight(1f))
                    DefaultButton("이체하기", modifier = Modifier.weight(1f))
                }
            }

            HorizontalDivider(
                thickness = 1.dp,
                color = Color.LightGray
            )

            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth() // Row를 가로로 꽉 채우기
                        .padding(horizontal = Spacing.default),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.bank), // Vector Drawable 리소스 ID
                        contentDescription = "Bank Icon",
                        tint = Color(0xFF4560F4),
                        modifier = Modifier
                            .padding(5.dp)
                            .size(30.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Column(
                        modifier =modifier
                    ) {
                        Row {
                            Text(
                                text = "홍길동",
                            )
                            Text(
                                text = " | 2일전",
                                style = it.withAlpha(0.5f),
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
                IndicatorRow(0)
            }

        }
    }
}

@Composable
fun IndicatorRow(selectedIndex: Int, total: Int = 10) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(total) { index ->
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
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
fun DefaultButton(text: String, modifier: Modifier = Modifier) {
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

@Preview(showBackground = true)
@Composable
fun Case1ScreenPreview() {
    KBankUITheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Page1Screen()
        }
    }
}
