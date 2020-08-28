package com.india.kotlinlistdemo.model

data class StudiosResponse(val code: Number?, val total_results: Number?, val message: String?, val data: List<Data>?)
data class Data(val studio_id: String?, val studio_category: String?, val studio_name: String?, val studio_logo: String?, val locality_name: String?, val city_id: String?, val about_studio: String?, val profile_pic: String?, val pin_code: String?, val isLadiesOnly: Boolean?, val address_line1: String?, val address_line2: String?, val latitude: String?, val longitude: String?, val workouts_limits_in_cycle: String?, val public_url: String?, val air_conditioner: String?, val wifi_facility: String?, val parking_facility: String?, val zone_name: String?, val studio_notice: String?, val open_time: String?, val favourite: Boolean?, val total_reviews: String?, val average_rating: String?, val distance: String?,  val activities: List<Activities>?)
data class Activities(val workout_id: String?, val workout_notice: String?, val display_endtime: Boolean?, val isLadiesOnly: Boolean?, val like_this_activity: String?, val workout_name: String?, val seo_locality_url: String?, val activity_id: String?, val workout_available_days: List<String>?, val workout_timing: List<Any>?, val workout_available_time: String?)

data class WDate(val workout_time_id: String?, val start_time: String?, val end_time: String?, val time_type: String?, val user_schedule_id: String?, val reserved_workout_time: String?, val other_info: Other_info?)

data class W1Date(val workout_time_id: String?, val start_time: String?, val end_time: String?, val time_type: String?, val user_schedule_id: String?, val reserved_workout_time: String?, val other_info: Other_info?)
data class Workout_timing(val W1Date: List<WDate>?)
data class Other_info(val user_notice: String?, val is_cancelable: String?, val reservedSchedulesTimes: List<Any>?, val workout_status: Number?)
