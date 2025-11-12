package dev.loki.securekb.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.loki.securekb.SecureKeypadControllerImpl
import dev.loki.securekb.factory.KeypadButtonFactory
import dev.loki.securekb.handler.ButtonPressHandler
import dev.loki.securekb.listener.KeypadEventListener
import dev.loki.securekb.listener.SecureKeypadController
import dev.loki.securekb.manager.KeypadConfigManager
import dev.loki.securekb.manager.KeypadShuffleManager
import dev.loki.securekb.setting.KeypadButton
import dev.loki.securekb.setting.KeypadButton.KeypadContentButton
import dev.loki.securekb.setting.KeypadButton.KeypadUtilButton
import dev.loki.securekb.setting.KeypadState
import dev.loki.securekb.strategy.KeypadInitializationStrategy

@Composable
private fun rememberSecureKeypadController(
    onButtonClick: (KeypadButton) -> Unit
): SecureKeypadController {
    return remember {
        val configManager = KeypadConfigManager()
        val shuffleManager = KeypadShuffleManager()
        val buttonFactory = KeypadButtonFactory()
        val listener = KeypadEventListener { button ->
            onButtonClick(button)
        }

        val initializationStrategy = KeypadInitializationStrategy(
            buttonFactory = buttonFactory,
            shuffleManager = shuffleManager,
            configManager = configManager
        )

        val buttonPressHandler = ButtonPressHandler(
            configManager = configManager,
            listener = listener
        )

        val controller = SecureKeypadControllerImpl(
            shuffleManager = shuffleManager,
            initializationStrategy = initializationStrategy,
            buttonPressHandler = buttonPressHandler
        )

        buttonPressHandler.onShuffle = { controller.shuffleButtons() }

        controller
    }
}

@Composable
fun SecureKeypad(
    onButtonClick: (KeypadButton) -> Unit,
    modifier: Modifier = Modifier,
    controller: SecureKeypadController? = null
) {
    val state = rememberLazyGridState()
    val keypadManager: SecureKeypadController = controller ?: rememberSecureKeypadController(onButtonClick)
    val keypadState by keypadManager.state.collectAsState(KeypadState())

    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 4),
        state = state,
        modifier = modifier
            .background(Color(0xFF0068FF))
            .fillMaxWidth()
    ) {
        val buttons = keypadState.contentButtons
        val utilButtons = listOf(
            KeypadUtilButton.KeypadShuffleButton,
            KeypadUtilButton.KeypadDeleteButton,
            KeypadUtilButton.KeypadConfirmButton
        )
        items(
            count = buttons.size,
            span = { index ->
                GridItemSpan(1)
            }
        ) { index ->
            val item = buttons[index]
            KeypadButtonItem(
                item = item,
                onClick = onButtonClick
            )
        }

        items(
            count = utilButtons.size,
            span = { index ->
                if (index == 2) {
                    GridItemSpan(2)
                } else {
                    GridItemSpan(1)
                }
            }
        ) { index ->
            val item = utilButtons[index]
            KeypadButtonUtilItem(
                button = item,
                onClick = { button ->
                    keypadManager.handleButtonPress(button)
                }
            )
        }
    }
}

@Composable
private fun KeypadButtonItem(
    item: KeypadContentButton,
    onClick: ((KeypadContentButton) -> Unit)?,
    modifier: Modifier = Modifier
) {
    val text = if (item is KeypadContentButton.KeypadItemButton) item.value else ""

    if (item !is KeypadContentButton.KeypadBlankButton) {
        Text(
            text = text,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    onClick?.invoke(item)
                }
                .background(Color.Transparent)
                .padding(vertical = 20.dp)
        )
    } else {
        Text(
            text = text,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {

                }
                .background(Color.Transparent)
                .padding(vertical = 20.dp)
        )
    }
}


@Composable
private fun KeypadButtonUtilItem(
    button: KeypadUtilButton,
    onClick: (KeypadUtilButton) -> Unit,
    modifier: Modifier = Modifier
) {
    when (button) {
        is KeypadUtilButton.KeypadDeleteButton -> DeleteButton(
            onClick = { onClick(button) },
            modifier = modifier
        )
        is KeypadUtilButton.KeypadShuffleButton -> ShuffleButton(
            onClick = { onClick(button) },
            modifier = modifier
        )
        is KeypadUtilButton.KeypadConfirmButton -> ConfirmButton(
            onClick = { onClick(button) },
            modifier = modifier
        )
    }
}

@Composable
private fun DeleteButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = Icons.Default.Delete,
        contentDescription = null,
        tint = Color.White,
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onClick()
            }
            .background(Color.Transparent)
            .padding(vertical = 20.dp)
    )
}

@Composable
private fun ShuffleButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = "재배열",
        color = Color.White,
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onClick()
            }
            .background(Color.Transparent)
            .padding(vertical = 20.dp)
    )
}

@Composable
private fun ConfirmButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = "확인",
        color = Color.White,
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onClick()
            }
            .background(Color.Transparent)
            .padding(vertical = 20.dp)
    )
}