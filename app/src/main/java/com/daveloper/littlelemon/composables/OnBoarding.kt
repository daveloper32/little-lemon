package com.daveloper.littlelemon.composables

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daveloper.littlelemon.R
import com.daveloper.littlelemon.ui.theme.*

@Composable
fun OnBoarding() {
    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .width(200.dp)
                .height(100.dp)
        )
        Text(
            text = "Let's get to know you",
            Modifier
                .background(Primary)
                .fillMaxWidth()
                .padding(vertical = 38.dp)
            ,
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 30.sp,
            fontFamily = karlaRegularFont
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Personal Information",
                fontFamily = karlaRegularFont,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 48.dp
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = "First Name",
                    fontFamily = karlaRegularFont,
                    fontWeight = FontWeight.Bold,
                    color = GrayLight,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                )
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults
                        .outlinedTextFieldColors(
                            focusedBorderColor = Primary,
                            cursorColor = Secondary
                        )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = "Last Name",
                    fontFamily = karlaRegularFont,
                    fontWeight = FontWeight.Bold,
                    color = GrayLight,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                )
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults
                        .outlinedTextFieldColors(
                            focusedBorderColor = Primary,
                            cursorColor = Secondary
                        )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = "Email",
                    fontFamily = karlaRegularFont,
                    fontWeight = FontWeight.Bold,
                    color = GrayLight,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults
                        .outlinedTextFieldColors(
                            focusedBorderColor = Primary,
                            cursorColor = Secondary
                        ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email
                    )
                )
            }
            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Secondary,
                    contentColor = Black,
                ),
                border = BorderStroke(1.dp, Tertiary),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
                ,
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Register",
                    fontFamily = karlaRegularFont,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun OnBoardingPreview() {
    OnBoarding()
}

