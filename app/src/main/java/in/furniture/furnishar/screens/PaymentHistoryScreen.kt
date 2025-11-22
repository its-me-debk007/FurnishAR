package `in`.furniture.furnishar.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import `in`.furniture.furnishar.SharedViewModel
import `in`.furniture.furnishar.ui.theme.Typography

@Composable
fun PaymentHistoryScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: SharedViewModel
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp, top = 52.dp),
        topBar = {
            Row {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .padding(end = 16.dp, top = 6.dp)
                        .size(28.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )
                Text(
                    "Payment History",
                    style = Typography.h1,
                    fontSize = 30.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 32.dp)
                )
            }
        }
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = it
        ) {
            items(viewModel.transactionList.size) { idx ->
                TransactionListItem(
                    amountWithCurrency = "Rs. ${viewModel.transactionList[idx].amount}",
                    date = viewModel.transactionList[idx].date,
                    errorDescription = viewModel.transactionList[idx].error
                )
            }
        }
    }
}

@Composable
fun TransactionListItem(
    amountWithCurrency: String,
    date: String,
    errorDescription: String?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Amount: $amountWithCurrency")
        Text("Date: $date", modifier = Modifier.padding(top = 4.dp))

        errorDescription?.let {
            Text("Error: $errorDescription", color = Color.Red, modifier = Modifier.padding(top = 4.dp))
        }
    }
}