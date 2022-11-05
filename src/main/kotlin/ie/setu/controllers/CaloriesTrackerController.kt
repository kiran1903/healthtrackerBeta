package ie.setu.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import ie.setu.domain.CaloriesTrackerDC
import ie.setu.domain.repository.CaloriesTrackerDAO
import io.javalin.http.Context
import org.jetbrains.exposed.sql.transactions.inTopLevelTransaction

object CaloriesTrackerController {

    private val caloriesTrackerDAO = CaloriesTrackerDAO()

    fun getExistingData(ctx: Context) {
        val calories = caloriesTrackerDAO.getAll()
        if(calories.size!=0){
            ctx.status(200)
        }
        else{
            ctx.status(404)
        }
        ctx.json(calories)
    }

    fun addEntry(ctx: Context) {
        val mapper = jacksonObjectMapper()
        val acitivityData = mapper.readValue<CaloriesTrackerDC>(ctx.body())
        val caloriesBurnt = calculateCalories(acitivityData)
        caloriesTrackerDAO.save(acitivityData,caloriesBurnt)
        ctx.json(acitivityData)
    }

    private fun calculateCalories(acitivityData: CaloriesTrackerDC): Double {
        if(acitivityData.activity == "Walking")
            return (acitivityData.duration *4).toDouble()
        else if(acitivityData.activity == "Jogging")
            return acitivityData.duration * 13.33
        else if(acitivityData.activity == "Cycling")
            return acitivityData.duration * 6.66
        return 0.0
    }
}