package com.example.tuckbox.roomDb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface roomDao {

    @Upsert
    suspend fun upsertFood(food: Food)
    @Delete
    suspend fun deleteFood(food: Food)
    @Query("SELECT * FROM foods")
    fun getAllFoood(): List<Food>
    @Query("SELECT * FROM food_extra_details WHERE food_id = :foodId")
    fun getFoodExtraDetails(foodId: Int): List<FoodExtraDetails>

}