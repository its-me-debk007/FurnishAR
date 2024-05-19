package `in`.furniture.furnishar.screens

import android.content.Context
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import `in`.furniture.furnishar.MainActivity
import `in`.furniture.furnishar.SharedViewModel
import `in`.furniture.furnishar.components.OtpFields
import `in`.furniture.furnishar.ui.theme.ColorPrimary
import `in`.furniture.furnishar.ui.theme.Typography
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginBottomSheet(
    sheetState: ModalBottomSheetState,
    modifier: Modifier = Modifier,
    viewModel: SharedViewModel,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

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

        Text(
            "Login",
            style = Typography.h1,
            fontSize = 30.sp,
            color = Color.Black,
        )

        Spacer(modifier = Modifier.height(16.dp))

        ClickableText(
            onClick = { _ ->
                viewModel.verificationId = null
                viewModel.loginErrorMsg = null
            },
            text = if (viewModel.verificationId == null) buildAnnotatedString { append("We need your mobile number, in order to proceed!") }
            else buildAnnotatedString {
                append("OTP sent to ")

                pushStringAnnotation(tag = "CLICKABLE", annotation = "link")
                withStyle(
                    SpanStyle(
                        color = Color.Blue,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("${viewModel.msisdn}")
                }
                pop()

                append(" successfully")
            },
        )

        Spacer(modifier = Modifier.height(16.dp))

        AnimatedVisibility(visible = viewModel.verificationId == null) {
            OutlinedTextField(
                value = viewModel.msisdn,
                onValueChange = { if (it.length <= 10) viewModel.msisdn = it },
                label = {
                    Text(text = " Enter your mobile number")
                },
                leadingIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "+91",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        )

//                    Spacer(
//                        modifier = Modifier
//                            .padding(start = 6.dp)
//                            .size(1.dp, 28.dp)
//                            .background(Color.Black)
//                    )
                    }
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
        }

        AnimatedVisibility(visible = viewModel.verificationId != null) {
            OtpFields(otpLength = 6, otp = viewModel.otp, onOtpChange = {
                if (it.length <= 6) viewModel.otp = it
            })
        }

        AnimatedVisibility(visible = viewModel.loginErrorMsg != null) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = viewModel.loginErrorMsg ?: "",
                    style = TextStyle(
                        color = MaterialTheme.colors.error,
                        fontWeight = FontWeight(600)
                    ),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.isLoginSheetLoading = true
                viewModel.loginErrorMsg = null

                if (viewModel.verificationId == null) {
                    sendOtp(
                        context = context,
                        msisdn = viewModel.msisdn,
                        onSuccess = { verificationId ->
                            viewModel.verificationId = verificationId
                            viewModel.isLoginSheetLoading = false
                        }
                    )

                    return@Button
                }

                verifyOtp(
                    context = context,
                    verificationId = viewModel.verificationId!!,
                    otp = viewModel.otp,
                    onSuccess = {
                        Log.d("furnu", "success")
                        viewModel.isLoginSheetLoading = false
                        scope.launch { sheetState.hide() }
                    },
                    onError = { errorMsg ->
                        viewModel.isLoginSheetLoading = false
                        viewModel.loginErrorMsg = errorMsg
                    }
                )
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = ColorPrimary),
            modifier = Modifier.fillMaxWidth()
        ) {
            if (viewModel.isLoginSheetLoading) {
                CircularProgressIndicator(strokeWidth = 1.6.dp, modifier = Modifier.size(20.dp))
                return@Button
            }

            Text(
                if (viewModel.verificationId == null) "Send OTP" else "Verify OTP",
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            )
        }
    }
}

fun sendOtp(context: Context, msisdn: String, onSuccess: (String) -> Unit) {

    val sendOtpCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {}

        override fun onVerificationFailed(e: FirebaseException) {}

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken,
        ) {
            onSuccess(verificationId)
        }
    }

    val options = PhoneAuthOptions.newBuilder()
        .setPhoneNumber("+91$msisdn")
        .setTimeout(30L, TimeUnit.SECONDS)
        .setActivity(context as MainActivity)
        .setCallbacks(sendOtpCallbacks)
        .build()

    PhoneAuthProvider.verifyPhoneNumber(options)
}

fun verifyOtp(
    context: Context,
    verificationId: String,
    otp: String,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    Log.d("furnu", otp)
    val credential = PhoneAuthProvider.getCredential(verificationId, otp)

    FirebaseAuth.getInstance().signInWithCredential(credential)
        .addOnCompleteListener(context as MainActivity) { task ->
            if (task.isSuccessful) {
                onSuccess()
            } else {

                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    Log.d(
                        "furnu",
                        (task.exception as FirebaseAuthInvalidCredentialsException).message.toString()
                    )
                    onError("Invalid OTP")
                }
            }
        }

}

















