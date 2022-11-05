package ie.setu.utils

import ie.setu.domain.CaloriesTrackerDC
import ie.setu.domain.HealthParametersDC
import ie.setu.domain.MeasurementDTO
import ie.setu.domain.User
import ie.setu.domain.db.CaloriesTracker
import ie.setu.domain.db.HealthParameters
import ie.setu.domain.db.Measurements
import ie.setu.domain.db.Users
import org.jetbrains.exposed.sql.ResultRow

fun mapToUser(it: ResultRow) = User(
    id = it[Users.id],
    name = it[Users.name],
    email = it[Users.email]
)

fun mapToHealthParameter(it: ResultRow) = HealthParametersDC(
    userid = it[HealthParameters.userid],
    bloodPressure = it[HealthParameters.bloodPressure],
    glucose = it[HealthParameters.glucose],

    pulse = it[HealthParameters.pulse]
)
fun mapToMeasurementDTO(it: ResultRow) = MeasurementDTO(
    userid = it[Measurements.userid],
    weight = it[Measurements.weight],
    height = it[Measurements.height],
    bmi = it[Measurements.bmi]
)

fun mapToCaloriesTracker(it: ResultRow) = CaloriesTrackerDC(
    id = it[CaloriesTracker.id],
    userid = it[CaloriesTracker.userid],
    date = it[CaloriesTracker.date],
    activity = it[CaloriesTracker.activity],
    duration = it[CaloriesTracker.duration],
    caloriesBurnt = it[CaloriesTracker.caloriesBurnt]
)