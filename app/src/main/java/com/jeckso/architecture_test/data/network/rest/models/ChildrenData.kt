package com.jeckso.reddit.data.network.rest.models


import com.google.gson.annotations.SerializedName

data class ChildrenData(
    @SerializedName("approved_at_utc")
    val approvedAtUtc: Any, // null
    @SerializedName("approved_by")
    val approvedBy: Any, // null
    @SerializedName("archived")
    val archived: Boolean, // false
    @SerializedName("author")
    val author: String, // naughteebutnice
    @SerializedName("author_fullname")
    val authorFullname: String, // t2_7s88k5j1
    @SerializedName("category")
    val category: Any, // null
    @SerializedName("content_categories")
    val contentCategories: List<String>,
    @SerializedName("created")
    val created: Double, // 1638131972.0
    @SerializedName("created_utc")
    val createdUtc: Double, // 1638131972.0
    @SerializedName("downs")
    val downs: Int, // 0
    @SerializedName("gallery_data")
    val galleryData: GalleryData,
    @SerializedName("id")
    val id: String, // r4ds0p
    @SerializedName("is_gallery")
    val isGallery: Boolean, // true
    @SerializedName("is_video")
    val isVideo: Boolean, // false
    @SerializedName("media")
    val media: Any, // null
    @SerializedName("media_only")
    val mediaOnly: Boolean, // false
    @SerializedName("name")
    val name: String, // t3_r4ds0p
    @SerializedName("permalink")
    val permalink: String, // /r/pics/comments/r4ds0p/felt_cute_and_festive_today_disabled_be_kind_i/
    @SerializedName("preview")
    val preview: Preview,
    @SerializedName("pwls")
    val pwls: Int, // 6
    @SerializedName("score")
    val score: Int, // 133645
    @SerializedName("subreddit")
    val subreddit: String, // pics
    @SerializedName("subreddit_id")
    val subredditId: String, // t5_2qh0u
    @SerializedName("subreddit_name_prefixed")
    val subredditNamePrefixed: String, // r/pics
    @SerializedName("thumbnail")
    val thumbnail: String, // https://b.thumbs.redditmedia.com/vjjAMo3o5YeD3KLefi1POg_0wZikrNGGqkU0ySmkREw.jpg
    @SerializedName("thumbnail_height")
    val thumbnailHeight: Int, // 140
    @SerializedName("thumbnail_width")
    val thumbnailWidth: Int, // 140
    @SerializedName("title")
    val title: String, // Felt cute and festive today :-) disabled be kind. I was feeling good 😊
    @SerializedName("ups")
    val ups: Int, // 133645
    @SerializedName("url")
    val url: String, // https://i.redd.it/vv2yl3jvde281.jpg
    @SerializedName("url_overridden_by_dest")
    val urlOverriddenByDest: String, // https://i.redd.it/vv2yl3jvde281.jpg
)