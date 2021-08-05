package uz.texnopos.instapos.data.helpers

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import uz.texnopos.instapos.data.N
import uz.texnopos.instapos.data.mode.User

class AuthHelper(private val auth: FirebaseAuth, private val db: FirebaseFirestore) {
    fun signUp(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            onSuccess.invoke()
        }
            .addOnFailureListener {
                onFailure.invoke(it.localizedMessage)
            }
    }

    fun signIn(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            onSuccess.invoke()
        }
            .addOnFailureListener {
                onFailure.invoke(it.localizedMessage)
            }
    }

    fun addUserToDb(onSuccess: () -> Unit, onFailure: (msg: String?) -> Unit) {
        val user = User(auth.currentUser!!.uid, auth.currentUser!!.email!!)
        db.collection(N.USERS).document(user.uid).set(user)
            .addOnSuccessListener {
                onSuccess.invoke()
            }
            .addOnFailureListener {
                onFailure.invoke(it.localizedMessage)
            }

    }
}