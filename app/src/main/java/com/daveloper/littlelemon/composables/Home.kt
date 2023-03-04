package com.daveloper.littlelemon.composables

import android.content.Context
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.daveloper.littlelemon.R
import com.daveloper.littlelemon.data.enums.CategoryType
import com.daveloper.littlelemon.data.enums.getAllCategoryTypeNames
import com.daveloper.littlelemon.data.enums.toCategoryType
import com.daveloper.littlelemon.data.model.MenuItemEntity
import com.daveloper.littlelemon.data.model.MenuItemNetwork
import com.daveloper.littlelemon.navigation.ProfileScreen
import com.daveloper.littlelemon.ui.theme.*

@Composable
fun Home(
    navController: NavHostController,
    data: State<List<MenuItemEntity>?>
) {
    var localMenuData by remember {
        mutableStateOf(data)
    }
    val context = LocalContext.current
    var filterText by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ToolbarLittleLemon() {
            navController.navigate(ProfileScreen.route)
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
            ,
        ) {
            item {
                HeaderLittleLemon(
                    filterText
                ) { newFilterText: String ->
                    localMenuData = if (newFilterText.isNotEmpty()) {
                        val res = data.value?.filter {
                            it.title.lowercase().contains(newFilterText)
                        } ?: emptyList<MenuItemEntity>()
                        Log.i("HomeScreen", "onFilterTextValueChange() filtered values: $res")
                        mutableStateOf(res)
                    } else {
                        data
                    }
                    filterText = newFilterText
                }
                ChipsContainer(
                    context = context
                ) { categoryType ->
                    Log.i("HomeScreen", "onSomeChipClicked() called -> $categoryType")
                    val res = if (categoryType == CategoryType.ALL) {
                        data.value
                    } else {
                        data.value?.filter {
                            it.category.toCategoryType(context) == categoryType
                        } ?: emptyList<MenuItemEntity>()
                    }
                    Log.i("HomeScreen", "onSomeChipClicked() filtered values: $res")
                    localMenuData = mutableStateOf(res)
                }
            }
            ProductsList(
                menuItemData = localMenuData.value?: emptyList()
            )
        }
    }
}

@Composable
private fun ToolbarLittleLemon(
    onProfilePhotoClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .align(Alignment.Center)
                .width(200.dp)
                .height(100.dp)
        )
        Box(
            modifier = Modifier
                .clickable {
                    onProfilePhotoClicked.invoke()
                }
                .clip(CircleShape)
                .size(45.dp)
                .align(Alignment.CenterEnd)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile photo",
            )
        }
    }
}

@Composable
private fun HeaderLittleLemon(
    filterTextValue: String,
    onFilterTextValueChange: (value: String) -> Unit
) {
    Column(
        modifier = Modifier
            .background(color = Primary)
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.title),
            fontSize = 48.sp,
            fontFamily = markaziTextRegularFont,
            color = Secondary,
            modifier = Modifier.height(44.dp)
        )
        Text(
            text = stringResource(id = R.string.location),
            fontSize = 36.sp,
            fontFamily = markaziTextRegularFont,
            color = White
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.description),
                color = White,
                fontSize = 16.sp,
                fontFamily = karlaRegularFont,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(end = 16.dp),

                )
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "Upper Panel Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .height(140.dp)
                ,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 8.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(WhiteTransparent)
        ) {
            TextField(
                value = filterTextValue,
                onValueChange = { onFilterTextValueChange.invoke(it) },
                modifier = Modifier
                    .fillMaxWidth()
                ,
                placeholder = {
                    Text(text = "Enter search phrase")
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search icon",
                        modifier = Modifier
                            .size(24.dp)
                    )
                },
                colors = TextFieldDefaults
                    .outlinedTextFieldColors(
                        cursorColor = Color.Gray
                    )
            )
        }
    }
}

@Composable
fun ChipsContainer(
    context: Context,
    onSomeChipClicked: (categoryType: CategoryType) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.lab_order_for_delivery).uppercase(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = karlaRegularFont,
                modifier = Modifier
                    .padding(
                        bottom = 20.dp,
                        top = 24.dp
                    )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(bottom = 24.dp)
                ,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                getAllCategoryTypeNames(context).forEach { chipName ->
                    ChipCustom(
                        name = chipName
                    ) {
                        onSomeChipClicked
                            .invoke(
                                chipName.toCategoryType(context)
                            )
                    }
                }
            }

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(GrayTransparent)
        )
    }
}

@Composable
fun ChipCustom(
    name: String,
    onChipClicked: (name: String) -> Unit
) {
    Button(
        onClick = {
            onChipClicked.invoke(name)
        },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults
            .buttonColors(
                backgroundColor = Gray
            )
        ,
        modifier = Modifier
            .padding(end = 16.dp)
            .height(42.dp)
    ) {
        Text(
            text = name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = karlaRegularFont,
            color = Primary
        )
    }
    
}

fun LazyListScope.ProductsList(
    menuItemData: List<MenuItemEntity>
) {
    items(menuItemData) {
        ProductDetails(it)
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    //Home()
}