package dev.loki.securekb.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import dev.loki.securekb.setting.KeypadButton

/**
 * 입력값을 마스킹하는 VisualTransformation
 * 각 문자를 ● 로 표시
 */
class PasswordVisualTransformation(private val mask: Char = '●') : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            AnnotatedString(mask.toString().repeat(text.length)),
            OffsetMapping.Identity
        )
    }
}

@Composable
fun SecureKeyboardScreen(
    modifier: Modifier = Modifier
) {
    var localText by remember { mutableStateOf("") }
    var showKeyboard by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                showKeyboard = false
            }
    ) {
        TextField(
            value = localText,
            onValueChange = { localText = it },
            readOnly = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 200.dp)
                .fillMaxWidth(0.8f)
                .clickable {
                    showKeyboard = true
                },
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect { interaction ->
                            if (interaction is PressInteraction.Release) {
                                showKeyboard = true
                            }
                        }
                    }
                }
        )

        if (showKeyboard) {
            SecureKeypad(
                onButtonClick = { button ->
                    when (button) {
                        is KeypadButton.KeypadContentButton.KeypadItemButton -> localText += button.value
                        is KeypadButton.KeypadUtilButton.KeypadDeleteButton -> localText =
                            localText.dropLast(1)

                        is KeypadButton.KeypadUtilButton.KeypadConfirmButton -> showKeyboard = false
                        else -> return@SecureKeypad
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            )
        }
    }
}