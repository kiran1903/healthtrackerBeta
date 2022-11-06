package ie.setu.controllers

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import ie.setu.domain.CaloriesTrackerDC
import ie.setu.domain.User
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
        val mapper = jacksonObjectMapper().registerModule(JodaModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        val acitivityData = mapper.readValue<CaloriesTrackerDC>(ctx.body())
        val caloriesBurnt = calculateCalories(acitivityData)
        caloriesTrackerDAO.save(acitivityData,caloriesBurnt)
        ctx.json(acitivityData)
    }

    fun calculateCalories(acitivityData: CaloriesTrackerDC): Double {
        when(acitivityData.activity ){
            "Walking" -> return (acitivityData.duration *4).toDouble()
            "Jogging" -> return (acitivityData.duration * 13.33)
            "Cycling" -> return (acitivityData.duration * 6.66)
        }
        return 0.0
    }

    fun getDataByUserId(ctx: Context) {
        val userData = caloriesTrackerDAO.findByUserID(ctx.pathParam("userid").toInt())
        if (userData != null) {
            ctx.json(userData)
        }
    }

    fun deleteData(ctx: Context) {
        caloriesTrackerDAO.delete(ctx.pathParam("userid").toInt())

    }

    fun updateData(ctx: Context) {
        val mapper = jacksonObjectMapper().registerModule(JodaModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        val userDataUpdates = mapper.readValue<CaloriesTrackerDC>(ctx.body())
        caloriesTrackerDAO.update(
            userid = ctx.pathParam("userid").toInt(),
            userData=userDataUpdates)
    }
}