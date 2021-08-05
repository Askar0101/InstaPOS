package uz.texnopos.instapos.data.mode

data class Post(
    val id: String = "",
    val userId: String = "",
    val photo: String = "",
    val description: String = "",
    val createdData: Long = System.currentTimeMillis(),
    var likes: Long = 0,
    var views: Long = 0
)