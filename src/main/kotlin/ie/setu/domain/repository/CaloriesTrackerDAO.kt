package ie.setu.domain.repository

import ie.setu.domain.CaloriesTrackerDC
import ie.setu.domain.db.CaloriesTracker
import ie.setu.utils.mapToCaloriesTracker
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class CaloriesTrackerDAO {

    fun getAll(): ArrayList<CaloriesTrackerDC> {
        val caloriesBurntList: ArrayList<CaloriesTrackerDC> = arrayListOf()
        transaction {
            CaloriesTracker.selectAll().map {
                caloriesBurntList.add(mapToCaloriesTracker(it))
            }
        }
        return caloriesBurntList

    }

    fun save(acitivityData: CaloriesTrackerDC, calculatedCalories: Double) {
        transaction {
            CaloriesTracker.insert {
                it[userid] = acitivityData.userid
                it[activity] = acitivityData.activity
                it[date] = acitivityData.date
                it[duration] = acitivityData.duration
                it[CaloriesTracker.caloriesBurnt] = calculatedCalories
            }
        }

    }
}