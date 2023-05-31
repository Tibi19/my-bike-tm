package com.tam.mybike.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.tam.mybike.ui.theme.PADDING_SMALL
import com.tam.mybike.ui.theme.TEXT_BIKE
import com.tam.mybike.ui.theme.TEXT_CONNECTOR_DEFAULT
import com.tam.mybike.ui.theme.TEXT_CONNECTOR_IN
import com.tam.mybike.ui.theme.TEXT_DATE
import com.tam.mybike.ui.theme.TEXT_DISTANCE
import com.tam.mybike.ui.theme.TEXT_DURATION
import com.tam.mybike.ui.theme.TEXT_SERVICE

@Composable
fun MediumDetails(
    intro: String,
    description: String,
    modifier: Modifier = Modifier,
    connector: String = TEXT_CONNECTOR_DEFAULT
) =
    Details(
        intro = intro,
        description = description,
        introStyle = MaterialTheme.typography.headlineMedium,
        descriptionStyle = MaterialTheme.typography.headlineLarge,
        connector = connector,
        modifier = modifier
    )

@Composable
fun LargeDetails(
    intro: String,
    description: String,
    modifier: Modifier = Modifier,
    connector: String = TEXT_CONNECTOR_DEFAULT
) =
    Details(
        intro = intro,
        description = description,
        introStyle = MaterialTheme.typography.titleMedium,
        descriptionStyle = MaterialTheme.typography.titleLarge,
        connector = connector,
        modifier = modifier
    )

@Composable
private fun Details(
    intro: String,
    description: String,
    introStyle: TextStyle,
    descriptionStyle: TextStyle,
    connector: String,
    modifier: Modifier = Modifier
) {
    val details = "$intro$connector $description"
    val detailsStyles = listOf(
        AnnotatedString.Range(
            item = introStyle.toSpanStyle(),
            start = 0,
            end = intro.length + connector.length
        ),
        AnnotatedString.Range(
            item = descriptionStyle.toSpanStyle(),
            start = details.indexOf(description),
            end = details.length
        )
    )
    val detailsAnnotatedString = AnnotatedString(
        text = details,
        spanStyles = detailsStyles
    )
    Text(
        text = detailsAnnotatedString,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier,
    )
}

@Preview
@Composable
private fun DetailsPreview() =
    WrapHeightPreview {
        Column(
            modifier = Modifier
                .padding(PADDING_SMALL)
                .background(MaterialTheme.colorScheme.background)
                .padding(PADDING_SMALL)
        ) {
            LargeDetails(
                intro = TEXT_SERVICE,
                description = "170km",
                connector = TEXT_CONNECTOR_IN
            )
            MediumDetails(
                intro = TEXT_BIKE,
                description = "Nukeproof Scout 290"
            )
            MediumDetails(
                intro = TEXT_DISTANCE,
                description = "60km"
            )
            MediumDetails(
                intro = TEXT_DURATION,
                description = "2h, 14min"
            )
            MediumDetails(
                intro = TEXT_DATE,
                description = "29.03.2023"
            )
        }
    }