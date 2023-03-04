package com.daveloper.littlelemon.composables

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.daveloper.littlelemon.R
import com.daveloper.littlelemon.data.manager.LittleLemonPreferencesManager
import com.daveloper.littlelemon.navigation.OnboardingScreen
import com.daveloper.littlelemon.ui.theme.*

@Composable
fun Profile(
    navController: NavHostController,
    onLogoutAction: () -> Unit
) {
    val context = LocalContext.current
    val littleLemonPreferencesManager: LittleLemonPreferencesManager =
        LittleLemonPreferencesManager(context)
    var firstName by remember {
        mutableStateOf(littleLemonPreferencesManager.firstName)
    }
    var lastName by remember {
        mutableStateOf(littleLemonPreferencesManager.lastName)
    }
    var email by remember {
        mutableStateOf(littleLemonPreferencesManager.email)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .width(200.dp)
                .height(100.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Personal Information",
                fontFamily = karlaRegularFont,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = 48.dp
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
                    enabled = false,
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
                    enabled = false,
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
                    enabled = false,
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
        }
        Button(
            onClick = {
                clearLocalData(littleLemonPreferencesManager)
                onLogoutAction.invoke()
                navController.navigate(OnboardingScreen.route)
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
                text = "Log out",
                fontFamily = karlaRegularFont,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

fun clearLocalData (
    littleLemonPreferencesManager: LittleLemonPreferencesManager
) {
    with(littleLemonPreferencesManager) {
        firstName = ""
        lastName = ""
        email = ""
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    //Profile()
}