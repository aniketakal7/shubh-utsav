package com.shubhutsav.app.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

data class CompanionSnapshot(
    val plannedFestivalIds: Set<String> = emptySet(),
    val householdMembers: List<String> = emptyList(),
    val tradition: String = "General Indian",
    val templeNote: String = ""
)

data class CommunityEvent(val title: String, val place: String, val date: String)

/** Stores a private companion document per authenticated Firebase user. */
object CompanionSyncManager {
    private val firestore by lazy { FirebaseFirestore.getInstance() }

    fun push(snapshot: CompanionSnapshot, onComplete: (String?) -> Unit) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: run { onComplete("Sign in to sync your plan"); return }
        val data = mapOf(
            "plannedFestivalIds" to snapshot.plannedFestivalIds.toList(), "householdMembers" to snapshot.householdMembers,
            "tradition" to snapshot.tradition, "templeNote" to snapshot.templeNote, "updatedAt" to System.currentTimeMillis()
        )
        firestore.collection("users").document(uid).collection("private").document("companion").set(data)
            .addOnSuccessListener { onComplete(null) }
            .addOnFailureListener { onComplete(it.localizedMessage ?: "Could not sync your plan") }
    }

    fun pull(onComplete: (CompanionSnapshot?, String?) -> Unit) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: run { onComplete(null, "Sign in to restore your plan"); return }
        firestore.collection("users").document(uid).collection("private").document("companion").get()
            .addOnSuccessListener { doc ->
                if (!doc.exists()) { onComplete(null, "No cloud plan found yet"); return@addOnSuccessListener }
                val planned = (doc.get("plannedFestivalIds") as? List<*>)?.filterIsInstance<String>()?.toSet() ?: emptySet()
                val members = (doc.get("householdMembers") as? List<*>)?.filterIsInstance<String>() ?: emptyList()
                onComplete(CompanionSnapshot(planned, members, doc.getString("tradition") ?: "General Indian", doc.getString("templeNote") ?: ""), null)
            }.addOnFailureListener { onComplete(null, it.localizedMessage ?: "Could not restore your plan") }
    }

    fun loadCommunityEvents(onComplete: (List<CommunityEvent>) -> Unit) {
        firestore.collection("communityEvents").limit(12).get()
            .addOnSuccessListener { results -> onComplete(results.documents.mapNotNull { doc ->
                val title = doc.getString("title") ?: return@mapNotNull null
                CommunityEvent(title, doc.getString("place") ?: "Local community", doc.getString("date") ?: "Date to be announced")
            }) }.addOnFailureListener { onComplete(emptyList()) }
    }
}
