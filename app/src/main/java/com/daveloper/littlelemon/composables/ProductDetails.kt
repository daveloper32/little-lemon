package com.daveloper.littlelemon.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daveloper.littlelemon.R
import com.daveloper.littlelemon.data.model.DishItemNetwork
import com.daveloper.littlelemon.ui.theme.GrayTransparent
import com.daveloper.littlelemon.ui.theme.karlaRegularFont

@Composable
fun ProductDetails(
    dishItemNetwork: DishItemNetwork
) {
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 16.dp)
            ,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            // Dish name
            Text(
                text = dishItemNetwork.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = karlaRegularFont,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                ,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth(0.75f)
                ) {

                    // Dish explanation
                    Text(
                        text = dishItemNetwork.description,
                        fontFamily = karlaRegularFont,
                        color = Color.DarkGray,
                        fontSize = 16.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(bottom = 12.dp, top = 4.dp)
                    )
                    // Dish price
                    Text(
                        text = "$${dishItemNetwork.price}",
                        fontSize = 18.sp,
                        color = Color.Gray,
                        fontFamily = karlaRegularFont,
                    )
                }
                Image(
                    painter = painterResource(R.drawable.hero_image),
                    contentDescription = "Dish image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(80.dp)
                )
            }
        }
        Box(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(GrayTransparent)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailsPreview() {
    ProductDetails(
        DishItemNetwork(
            title = "Greek Salad",
            description = "The famous greek salad of crispy lettuce, peppers, olives and our Chicago",
            price = 12.99,
            imageUrl = ""
        )
    )
}