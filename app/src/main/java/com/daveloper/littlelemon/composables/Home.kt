package com.daveloper.littlelemon.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.daveloper.littlelemon.navigation.OnboardingScreen
import com.daveloper.littlelemon.navigation.ProfileScreen
import com.daveloper.littlelemon.ui.theme.Black
import com.daveloper.littlelemon.ui.theme.Secondary
import com.daveloper.littlelemon.ui.theme.Tertiary
import com.daveloper.littlelemon.ui.theme.karlaRegularFont

@Composable
fun Home(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = {
                navController.navigate(ProfileScreen.route)
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Secondary,
                contentColor = Black,
            ),
            border = BorderStroke(1.dp, Tertiary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 20.dp,
                    horizontal = 16.dp
                )
            ,
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "See profile",
                fontFamily = karlaRegularFont,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    //Home()
}