package ie.setu.domain.db

import org.jetbrains.exposed.sql.Table

object CaloriesTracker: Table("caloriestracker") {
    val userid = integer("userid")
    val date = datetime("date")
    val activity = varchar("activity",100)
    val duration = integer("duration")
    val caloriesBurnt = double("caloriesBurnt")
}