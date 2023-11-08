package com.faridnia.todolistcompose.presentaion.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faridnia.todolistcompose.R
import com.faridnia.todolistcompose.util.LightAndDarkPreview


@LightAndDarkPreview
@Composable
fun PreviewCustomTextInput() {
    CustomTextInput(
        textValue = remember { mutableStateOf("") },
        onTextChanged = {},
        labelText = "Sample Text",
        placeHolderText = "placeHolderText",
        supportingText = "supportingText",
        isError = false
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextInput(
    textValue: MutableState<String>,
    onTextChanged: (String) -> Unit,
    labelText: String,
    isError: Boolean,
    placeHolderText: String,
    supportingText: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {

    val isFocused = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .height(56.dp)
                .border(
                    width = 1.5.dp,
                    color = getBorderColor(isFocused, isError),
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(size = 12.dp)
                )
        ) {

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp)
                    .onFocusChanged {
                        isFocused.value = it.isFocused
                    },
                value = textValue.value,
                onValueChange = {
                    textValue.value = it
                    onTextChanged.invoke(it)
                },
                label = {
                    Text(
                        text = labelText,
                        modifier = Modifier.padding(top = 0.dp),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.raleway_regular)),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    )

                },
                isError = false,
                placeholder = {
                    Text(
                        text = placeHolderText,
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 24.sp,
                            fontFamily = FontFamily(Font(R.font.raleway_regular)),
                            fontWeight = FontWeight(400),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily(Font(R.font.raleway_regular)),
                    fontWeight = FontWeight(400),
                    color = MaterialTheme.colorScheme.onSurface,
                ),
                colors = TextFieldDefaults.textFieldColors(
                    focusedLabelColor = MaterialTheme.colorScheme.surface,
                    cursorColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.surface,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }

        Spacer(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.surface)
                .height(8.dp)
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 0.dp)
                .background(color = MaterialTheme.colorScheme.surface),
            text = supportingText,
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily(Font(R.font.raleway_regular)),
                fontWeight = FontWeight(400),
                color = MaterialTheme.colorScheme.error,
            )
        )
    }
}

@Composable
fun getBorderColor(focused: MutableState<Boolean>, isError: Boolean): Color {
    return if (isError)
        MaterialTheme.colorScheme.error
    else if (focused.value)
        MaterialTheme.colorScheme.onSurface
    else
        MaterialTheme.colorScheme.outline
}
