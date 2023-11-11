package com.faridnia.todolistcompose.presentaion.ui.todos

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faridnia.todolistcompose.R
import com.faridnia.todolistcompose.util.LightAndDarkPreview

@LightAndDarkPreview
@Composable
fun PreviewToDoItem() {
    ToDoItem(
        itemName = "New To Do",
        drawableResourceId = R.drawable.tik,
        isChecked = true,
        onClick = {}
    )
}

@Composable
fun ToDoItem(
    itemName: String,
    @DrawableRes drawableResourceId: Int? = null,
    isChecked: Boolean,
    onClick: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .border(
                width = 1.5.dp,
                color = getSettingItemColor(isChecked),
                shape = RoundedCornerShape(size = 12.dp)
            )
            .fillMaxWidth()
            .height(56.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(size = 12.dp)
            )
            .clickable {
                onClick.invoke(itemName)
            },
        horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Spacer(modifier = Modifier.width(16.dp))

        if (drawableResourceId != null) {
            Image(
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp),
                painter = painterResource(id = drawableResourceId),
                contentDescription = "country",
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(12.dp))
        }

        Text(
            text = itemName,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = FontFamily(Font(R.font.raleway_light)),
                fontWeight = FontWeight(500),
                color = MaterialTheme.colorScheme.onSurface,
            )
        )

        Spacer(modifier = Modifier.weight(1.0f))

        if (isChecked) {
            Image(
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(size = 12.dp)
                    ),
                painter = painterResource(id = R.drawable.tik),
                contentDescription = "tik",
                contentScale = ContentScale.None
            )
        } else {
            Box(
                modifier = Modifier
                    .border(
                        width = 1.5.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(size = 12.dp)
                    )
                    .width(24.dp)
                    .height(24.dp),
            )
        }

        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Composable
private fun getSettingItemColor(isChecked: Boolean): Color =
    if (isChecked)
        MaterialTheme.colorScheme.primary
    else
        MaterialTheme.colorScheme.outline
