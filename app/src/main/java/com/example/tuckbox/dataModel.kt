package com.example.tuckbox

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foods")
data class Food(
    @PrimaryKey val foodId: Int,
    val foodName: String,
    val foodExtraChoice: String?
)

@Entity(tableName = "food_extra_details")
data class FoodExtraDetails(
    @PrimaryKey val detailsId: Int,
    val detailsName: String,
    @ColumnInfo(name="food_id")
    val foodId: Int
)
 
@Entity(tableName = "users")
data class Users(
    @PrimaryKey val userId: Int,
    val userEmail: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val mobile: String
)

@Entity("delivery_address")
data class DeliveryAddresses(
    @PrimaryKey val addressId: Int,
    val address: String,
    @ColumnInfo("userId")
    val userId: Int
)

@Entity(tableName = "cities")
data class Cities(
    @PrimaryKey val cityId : Int,
    val cityName: String
)

@Entity(tableName = "time_slots")
data class TimeSlots(
    @PrimaryKey val timeSlotsId: Int,
    val timeSlot: String
)

@Entity(tableName = "orders")
data class Orders(
    @PrimaryKey val orderId: Int,
    val orderDate: String,
    val quantity: Int,
    @ColumnInfo("detailsId")
    val detailsId:Int,
    @ColumnInfo("cityId")
    val cityId: Int,
    @ColumnInfo("timeSlotsId")
    val timeSlotsId: Int,
    @ColumnInfo("userId")
    val userId: Int
)