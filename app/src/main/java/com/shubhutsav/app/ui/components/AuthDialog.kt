package com.shubhutsav.app.ui.components

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.shubhutsav.app.data.AuthManager
import com.shubhutsav.app.ui.theme.*

@Composable
fun AuthDialog(
    isHindi: Boolean = false,
    language: String = if (isHindi) "hi" else "en",
    onDismiss: () -> Unit,
    onAuthSuccess: () -> Unit = {}
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var isSignUp by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var successMessage by remember { mutableStateOf<String?>(null) }

    // Google Sign-In launcher setup
    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                val idToken = account.idToken
                if (idToken != null) {
                    isLoading = true
                    AuthManager.signInWithGoogle(
                        idToken = idToken,
                        onSuccess = {
                            isLoading = false
                            onAuthSuccess()
                            onDismiss()
                        },
                        onError = { err ->
                            isLoading = false
                            errorMessage = err
                        }
                    )
                } else {
                    errorMessage = "Unable to get Google ID token"
                }
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Google sign-in cancelled"
            }
        }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, VedicGold.copy(alpha = 0.35f)),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header Icon
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .background(PanchangCardGradient)
                ) {
                    Text(text = "🪔", fontSize = 26.sp)
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Title
                Text(
                    text = if (isSignUp) {
                        when (language) {
                            "mr" -> "नवीन खाते तयार करा"
                            "hi" -> "नया खाता बनाएं"
                            else -> "Create Account"
                        }
                    } else {
                        when (language) {
                            "mr" -> "शुभ उत्सवात स्वागत आहे"
                            "hi" -> "शुभ उत्सव में लॉगिन करें"
                            else -> "Welcome to Shubh Utsav"
                        }
                    },
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = when (language) {
                        "mr" -> "आपली पूजा यादी आणि सण स्मरणपत्रे क्लाउडवर सुरक्षित ठेवा"
                        "hi" -> "अपनी पूजा सामग्री और व्रत अनुस्मारक क्लाउड पर सुरक्षित रखें"
                        else -> "Sync your festival reminders & shopping lists to the cloud"
                    },
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
                )

                // Error / Success message alert
                AnimatedVisibility(visible = errorMessage != null) {
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xFFFFEBEE),
                        border = BorderStroke(0.8.dp, Color(0xFFE57373)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Icon(
                                Icons.Default.ErrorOutline,
                                contentDescription = null,
                                tint = Color(0xFFD32F2F),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = errorMessage ?: "",
                                color = Color(0xFFC62828),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                AnimatedVisibility(visible = successMessage != null) {
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xFFE8F5E9),
                        border = BorderStroke(0.8.dp, Color(0xFF81C784)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Icon(
                                Icons.Default.CheckCircleOutline,
                                contentDescription = null,
                                tint = Color(0xFF2E7D32),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = successMessage ?: "",
                                color = Color(0xFF1B5E20),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                // Name field (Sign Up only)
                if (isSignUp) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = {
                            Text(
                                when (language) {
                                    "mr" -> "पूर्ण नाव"
                                    "hi" -> "पूरा नाम"
                                    else -> "Full Name"
                                }
                            )
                        },
                        leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = VedicGold) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                    )
                }

                // Email field
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = {
                        Text(
                            when (language) {
                                "mr" -> "ईमेल पत्ता"
                                "hi" -> "ईमेल पता"
                                else -> "Email Address"
                            }
                        )
                    },
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = VedicGold) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                )

                // Password field
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = {
                        Text(
                            when (language) {
                                "mr" -> "पासवर्ड"
                                "hi" -> "पासवर्ड"
                                else -> "Password"
                            }
                        )
                    },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = VedicGold) },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Toggle Password"
                            )
                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 6.dp)
                )

                // Forgot Password Link (Sign In only)
                if (!isSignUp) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = when (language) {
                                "mr" -> "पासवर्ड विसरलात?"
                                "hi" -> "पासवर्ड भूल गए?"
                                else -> "Forgot Password?"
                            },
                            fontSize = 12.sp,
                            color = KesariyaSaffron,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.clickable {
                                if (email.isBlank()) {
                                    errorMessage = "Please enter your email above to receive a reset link"
                                } else {
                                    isLoading = true
                                    AuthManager.sendPasswordReset(
                                        email = email,
                                        onSuccess = {
                                            isLoading = false
                                            successMessage = "Password reset link sent to your email!"
                                            errorMessage = null
                                        },
                                        onError = { err ->
                                            isLoading = false
                                            errorMessage = err
                                        }
                                    )
                                }
                            }
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.height(10.dp))
                }

                // Primary Action Button (Sign In / Sign Up)
                Button(
                    onClick = {
                        errorMessage = null
                        successMessage = null
                        isLoading = true
                        if (isSignUp) {
                            AuthManager.signUpWithEmail(
                                email = email,
                                pass = password,
                                name = name,
                                onSuccess = {
                                    isLoading = false
                                    onAuthSuccess()
                                    onDismiss()
                                },
                                onError = { err ->
                                    isLoading = false
                                    errorMessage = err
                                }
                            )
                        } else {
                            AuthManager.signInWithEmail(
                                email = email,
                                pass = password,
                                onSuccess = {
                                    isLoading = false
                                    onAuthSuccess()
                                    onDismiss()
                                },
                                onError = { err ->
                                    isLoading = false
                                    errorMessage = err
                                }
                            )
                        }
                    },
                    enabled = !isLoading && email.isNotBlank() && password.isNotBlank(),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = RoyalMaroon),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            color = VedicGold,
                            strokeWidth = 2.dp,
                            modifier = Modifier.size(20.dp)
                        )
                    } else {
                        Text(
                            text = if (isSignUp) {
                                when (language) {
                                    "mr" -> "नोंदणी करा (Sign Up)"
                                    "hi" -> "रजिस्टर करें (Sign Up)"
                                    else -> "Create Account"
                                }
                            } else {
                                when (language) {
                                    "mr" -> "लॉगिन करा (Sign In)"
                                    "hi" -> "लॉगिन करें (Sign In)"
                                    else -> "Sign In"
                                }
                            },
                            color = VedicGoldLight,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Guest / Anonymous button
                OutlinedButton(
                    onClick = {
                        isLoading = true
                        AuthManager.signInAnonymously(
                            onSuccess = {
                                isLoading = false
                                onAuthSuccess()
                                onDismiss()
                            },
                            onError = { err ->
                                isLoading = false
                                errorMessage = err
                            }
                        )
                    },
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                ) {
                    Text(
                        text = when (language) {
                            "mr" -> "अतिथी म्हणून सुरू ठेवा (Guest)"
                            "hi" -> "अतिथि के रूप में जारी रखें (Guest)"
                            else -> "Continue as Guest"
                        },
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Switch between Sign In and Sign Up
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (isSignUp) {
                            when (language) {
                                "mr" -> "आधीच खाते आहे? "
                                "hi" -> "पहले से खाता है? "
                                else -> "Already have an account? "
                            }
                        } else {
                            when (language) {
                                "mr" -> "नवीन आहात? "
                                "hi" -> "नए उपयोगकर्ता हैं? "
                                else -> "Don't have an account? "
                            }
                        },
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = if (isSignUp) {
                            when (language) {
                                "mr" -> "लॉगिन करा"
                                "hi" -> "लॉगिन करें"
                                else -> "Sign In"
                            }
                        } else {
                            when (language) {
                                "mr" -> "नोंदणी करा"
                                "hi" -> "रजिस्टर करें"
                                else -> "Register"
                            }
                        },
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = RoyalMaroon,
                        modifier = Modifier.clickable {
                            isSignUp = !isSignUp
                            errorMessage = null
                            successMessage = null
                        }
                    )
                }
            }
        }
    }
}
