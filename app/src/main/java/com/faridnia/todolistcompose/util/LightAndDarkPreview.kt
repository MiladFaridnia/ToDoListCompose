package com.faridnia.todolistcompose.util

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
annotation class LightAndDarkPreview
