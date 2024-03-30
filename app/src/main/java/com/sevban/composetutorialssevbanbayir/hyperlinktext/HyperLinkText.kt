package com.sevban.composetutorialssevbanbayir.hyperlinktext

import androidx.annotation.StringRes
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle

/**
 * Composable function for displaying text with hyperlinks. It uses the ClickableText composable
 * to render hyperlinks and provides a callback for handling hyperlink clicks.
 *
 * @param stringResource A function to retrieve the text with hyperlinks as a string resource.
 * @example string
 *      "Visit our website [here](https://www.example.com) for more information."
 * @param onClick A callback function invoked when a hyperlink is clicked.
 *
 * @sample
 * ```
 * HyperlinkText(
 *     stringResource = { stringResource(R.string.sample_text_with_links) },
 *     modifier = Modifier.padding(16.dp),
 *     onClick = { hyperlink ->
 *
 *     }
 * )
 * ```
 */
@Composable
fun HyperlinkText(
    @StringRes
    stringResource: Int,
    modifier: Modifier = Modifier,
    onClick: (Hyperlink) -> Unit
) {
    val textWithLinks = stringResource(stringResource)
    val links = Regex("""\[(.*?)\]\((.*?)\)""").findAll(textWithLinks)
    val text = buildAnnotatedString {

        var currentIndex = 0

        links.forEach { result ->

            val (displayText, url) = result.destructured
            val linkStart = result.range.first
            val linkEnd = result.range.last + 1

            append(textWithLinks.subSequence(currentIndex, linkStart))

            pushStringAnnotation(displayText, url)
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colors.primary,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(displayText)
            }
            pop()

            currentIndex = linkEnd
        }

        if (currentIndex < textWithLinks.length) {
            append(textWithLinks.subSequence(currentIndex, textWithLinks.length))
        }
    }

    ClickableText(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.body1,
        onClick = { offset ->
            links.forEach { result ->
                val displayText = result.destructured.component1()
                text.getStringAnnotations(displayText, offset, offset).firstOrNull()
                    ?.let { annotation ->
                        onClick(
                            Hyperlink(
                                link = annotation.item,
                                displayText = annotation.tag,
                            )
                        )
                    }
            }
        }
    )
}

data class Hyperlink(val link: String, val displayText: String)

/*fun analyzeHyperlink(matchResults: Sequence<MatchResult>): List<Hyperlink> {
    return matchResults.map {
        val (displayText, url) = it.destructured
        Hyperlink(link = url, displayText = displayText)
    }.toList()
}*/
/*
enum class LinkType(val possibleWords: List<String>) {
    //üyelik sözleşmesi
    MEMBERSHIP_AGREEMENT(possibleWords = listOf("üyelik", "membership")),

    //kullanım kosulları
    TERMS_OF_SERVICE(possibleWords = listOf("kullanım", "terms")),

    //aydınlatma metni
    CLARIFICATION_TEXT(possibleWords = listOf("clarification", "aydınlatma")),

    //açık rıza metni
    EXPLICIT_CONSENT_TEXT(possibleWords = listOf("consent", "açık")),

    UNKNOWN_LINK_TYPE(possibleWords = listOf())
}*/

/*
data class Hyperlink2(
    val link: String,
    val displayText: String,
    val range: IntRange,
    val linkType: LinkType
)*/


