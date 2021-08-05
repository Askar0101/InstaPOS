package uz.texnopos.instapos.data.helpers

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import uz.texnopos.instapos.data.N
import uz.texnopos.instapos.data.mode.Post
import java.util.*

class PostHelper(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val storage: FirebaseStorage
) {
    fun sendNewPost(
        byteArray: ByteArray,
        description: String,
        onSuccess: () -> Unit,
        onFragment: (msg: String?) -> Unit
    ) {
        val compressedPostRef = storage.reference.child("compressedPosts/${UUID.randomUUID()}")
        val uploadTask = compressedPostRef.putBytes(byteArray)
        uploadTask.addOnSuccessListener {
            compressedPostRef.downloadUrl.addOnSuccessListener {
                val post = Post(
                    id = UUID.randomUUID().toString(),
                    photo = compressedPostRef.downloadUrl.toString(),
                    userId = auth.currentUser!!.uid, description = description
                )
                db.document("${N.POSTS}/${post.id}").set(post)
                    .addOnSuccessListener {
                        onSuccess.invoke()
                    }
                    .addOnFailureListener {
                        onFragment.invoke(it.message)
                    }
            }
        }
            .addOnFailureListener {
                onFragment.invoke(it.message)
            }
    }
}
