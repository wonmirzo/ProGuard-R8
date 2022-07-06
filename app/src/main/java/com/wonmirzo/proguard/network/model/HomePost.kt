package com.wonmirzo.proguard.network.model

import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName

data class HomePost (
    val id: String,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String,

    @SerializedName("promoted_at")
    val promotedAt: String? = null,

    val width: Long,
    val height: Long,
    val color: String,

    @SerializedName("blur_hash")
    val blurHash: String,

    val description: String? = null,

    @SerializedName("alt_description")
    val altDescription: String? = null,

    val urls: Urls,
    val links: ResponseLinks,
    val categories: JsonArray,
    val likes: Long,

    @SerializedName("liked_by_user")
    val likedByUser: Boolean,

    @SerializedName("current_user_collections")
    val currentUserCollections: JsonArray,

    val sponsorship: Sponsorship? = null,

    @SerializedName("topic_submissions")
    val topicSubmissions: TopicSubmissions,

    val user: User
)

data class ResponseLinks (
    val self: String,
    val html: String,
    val download: String,

    @SerializedName("download_location")
    val downloadLocation: String
)

data class Sponsorship (
    @SerializedName("impression_urls")
    val impressionUrls: List<String>,

    val tagline: String,

    @SerializedName("tagline_url")
    val taglineURL: String,

    val sponsor: User
)
data class User (
    val id: String,

    @SerializedName("updated_at")
    val updatedAt: String,

    val username: String,
    val name: String,

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String? = null,

    @SerializedName("twitter_username")
    val twitterUsername: String? = null,

    @SerializedName("portfolio_url")
    val portfolioURL: String? = null,

    val bio: String? = null,
    val location: String? = null,
    val links: UserLinks,

    @SerializedName("profile_image")
    val profileImage: ProfileImage,

    @SerializedName("instagram_username")
    val instagramUsername: String? = null,

    @SerializedName("total_collections")
    val totalCollections: Long,

    @SerializedName("total_likes")
    val totalLikes: Long,

    @SerializedName("total_photos")
    val totalPhotos: Long,

    @SerializedName("accepted_tos")
    val acceptedTos: Boolean,

    @SerializedName("for_hire")
    val forHire: Boolean,

    val social: Social
)

data class UserLinks (
    val self: String,
    val html: String,
    val photos: String,
    val likes: String,
    val portfolio: String,
    val following: String,
    val followers: String
)

data class ProfileImage (
    val small: String,
    val medium: String,
    val large: String
)

data class Social (
    @SerializedName("instagram_username")
    val instagramUsername: String? = null,

    @SerializedName("portfolio_url")
    val portfolioURL: String? = null,

    @SerializedName("twitter_username")
    val twitterUsername: String? = null,

)

data class TopicSubmissions (
    val health: Blockchain? = null,

    @SerializedName("textures-patterns")
    val texturesPatterns: Blockchain? = null,

    val nature: Blockchain? = null,
    val blockchain: Blockchain? = null,

    @SerializedName("food-drink")
    val foodDrink: Blockchain? = null,

    val interiors: Blockchain? = null,

    @SerializedName("business-work")
    val businessWork: Blockchain? = null,

    val travel: Blockchain? = null,
    val wallpapers: Blockchain? = null,
    val spirituality: Blockchain? = null,

    @SerializedName("color-theory")
    val colorTheory: Blockchain? = null
)

data class Blockchain (
    @SerializedName("approved_on")
    val approvedOn: String? = null
)

data class Urls (
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String,
)
