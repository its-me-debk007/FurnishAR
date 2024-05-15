package `in`.furniture.furnishar.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import `in`.furniture.furnishar.SharedViewModel
import `in`.furniture.furnishar.ui.theme.ColorPrimary

@Composable
fun LoginBottomSheet(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: SharedViewModel
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .padding(horizontal = 24.dp, vertical = 20.dp)
    ) {

        // Pill
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Spacer(
                modifier = Modifier
                    .size(32.dp, 6.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.LightGray)
            )
        }

        Text("Login")

        Text(text = "We need your mobile number, in order to proceed!")

        OutlinedTextField(
            value = viewModel.msisdn,
            onValueChange = { viewModel.msisdn = it },
            label = {
                Text(text = " Enter your mobile number")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Send
            ),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = ColorPrimary,
                cursorColor = ColorPrimary,
                focusedLabelColor = ColorPrimary
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OTPFields(viewModel = viewModel)

        Button(
            onClick = {

            }
        ) {
            Text("Send OTP")
        }
    }
}

@Composable
private fun OTPFields(viewModel: SharedViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        viewModel.otpValues.forEachIndexed { index, textFieldValue ->
            OutlinedTextField(
                value = textFieldValue,
                onValueChange = { newValue ->
                    if (newValue.text.length <= 1) {
                        viewModel.otpValues[index] = newValue

                        if (newValue.text.isNotEmpty()) {
                            if (index < 3) {
                                viewModel.focusRequesters[index + 1].requestFocus()
                            }
                        } else {
                            if (index > 0) {
                                viewModel.focusRequesters[index - 1].requestFocus()
                            }
                        }
                    }
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = ColorPrimary,
                    cursorColor = ColorPrimary,
                    focusedLabelColor = ColorPrimary
                ),
                modifier = Modifier
                    .size(60.dp)
                    .focusRequester(viewModel.focusRequesters[index])
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused && textFieldValue.text.isNotEmpty()) {
                            viewModel.otpValues[index] = textFieldValue.copy(
                                selection = TextRange(
                                    0,
                                    textFieldValue.text.length
                                )
                            )
                        }
                    },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
        }
    }
}