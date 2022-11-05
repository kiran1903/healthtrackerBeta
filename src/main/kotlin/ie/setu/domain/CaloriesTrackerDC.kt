package ie.setu.domain

import org.joda.time.DateTime
import java.time.Duration

import java.util.DoubleSummaryStatistics

data class CaloriesTrackerDC (
    val userid: Int,
    val date: DateTime,
    val activity: String,
    val duration: Int,
    val caloriesBurnt: Double
        )