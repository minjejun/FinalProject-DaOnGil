package kr.tekit.lion.daongil.domain.model
import java.time.LocalDateTime

data class Review (
    val reviewId: Long,
    val nickname : String,
    val profileImg : String,
    val content : String,
    val reviewImg : String,
    val grade : Float,
    val date : LocalDateTime
)