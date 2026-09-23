package com.shubhutsav.app.data

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest

object AuthManager {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    private val _currentUser = mutableStateOf<FirebaseUser?>(null)
    val currentUser: State<FirebaseUser?> = _currentUser

    init {
        try {
            _currentUser.value = auth.currentUser
            auth.addAuthStateListener { firebaseAuth ->
                _currentUser.value = firebaseAuth.currentUser
            }
        } catch (e: Exception) {
            // Firebase may not be initialized in unit tests
        }
    }

    val isLoggedIn: Boolean
        get() = _currentUser.value != null

    val isAnonymous: Boolean
        get() = _currentUser.value?.isAnonymous == true

    val displayName: String
        get() = _currentUser.value?.displayName?.ifBlank { null }
            ?: _currentUser.value?.email?.substringBefore("@")
            ?: if (isAnonymous) "Guest Devotee" else "Devotee"

    val email: String?
        get() = _currentUser.value?.email

    val photoUrl: String?
        get() = _currentUser.value?.photoUrl?.toString()

    fun signInWithEmail(
        email: String,
        pass: String,
        onSuccess: (FirebaseUser) -> Unit,
        onError: (String) -> Unit
    ) {
        if (email.isBlank() || pass.isBlank()) {
            onError("Please enter both email and password")
            return
        }
        auth.signInWithEmailAndPassword(email.trim(), pass.trim())
            .addOnSuccessListener { result ->
                result.user?.let(onSuccess) ?: onError("Failed to get user details")
            }
            .addOnFailureListener { e ->
                onError(e.localizedMessage ?: "Sign-in failed")
            }
    }

    fun signUpWithEmail(
        email: String,
        pass: String,
        name: String,
        onSuccess: (FirebaseUser) -> Unit,
        onError: (String) -> Unit
    ) {
        if (email.isBlank() || pass.isBlank()) {
            onError("Please enter email and password")
            return
        }
        if (pass.length < 6) {
            onError("Password must be at least 6 characters")
            return
        }
        auth.createUserWithEmailAndPassword(email.trim(), pass.trim())
            .addOnSuccessListener { result ->
                val user = result.user
                if (user != null && name.isNotBlank()) {
                    val profileUpdate = UserProfileChangeRequest.Builder()
                        .setDisplayName(name.trim())
                        .build()
                    user.updateProfile(profileUpdate).addOnCompleteListener {
                        onSuccess(user)
                    }
                } else if (user != null) {
                    onSuccess(user)
                } else {
                    onError("Failed to create user")
                }
            }
            .addOnFailureListener { e ->
                onError(e.localizedMessage ?: "Registration failed")
            }
    }

    fun signInWithGoogle(
        idToken: String,
        onSuccess: (FirebaseUser) -> Unit,
        onError: (String) -> Unit
    ) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnSuccessListener { result ->
                result.user?.let(onSuccess) ?: onError("Failed to sign in with Google")
            }
            .addOnFailureListener { e ->
                onError(e.localizedMessage ?: "Google sign-in failed")
            }
    }

    fun signInAnonymously(
        onSuccess: (FirebaseUser) -> Unit,
        onError: (String) -> Unit
    ) {
        auth.signInAnonymously()
            .addOnSuccessListener { result ->
                result.user?.let(onSuccess) ?: onError("Failed to continue as guest")
            }
            .addOnFailureListener { e ->
                onError(e.localizedMessage ?: "Guest login failed")
            }
    }

    fun sendPasswordReset(
        email: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (email.isBlank()) {
            onError("Please enter your registered email")
            return
        }
        auth.sendPasswordResetEmail(email.trim())
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e ->
                onError(e.localizedMessage ?: "Failed to send reset link")
            }
    }

    fun signOut() {
        auth.signOut()
    }
}
