package ie.setu.helpers

import ie.setu.domain.CaloriesTrackerDC
import ie.setu.domain.User
import ie.setu.domain.db.CaloriesTracker
import ie.setu.domain.db.Users
import ie.setu.domain.repository.CaloriesTrackerDAO
import ie.setu.domain.repository.UserDAO
import ie.setu.repository.user1
import ie.setu.repository.user2
import ie.setu.repository.user3
import org.jetbrains.exposed.sql.SchemaUtils
import org.joda.time.DateTime

val nonExistingEmail = "112233445566778testUser@xxxxx.xx"
val validName = "Test User 1"
val validEmail = "testuser1@test.com"

val users = arrayListOf<User>(
    User(name = "Alice Wonderland", email = "alice@wonderland.com", id = 1),
    User(name = "Bob Cat", email = "bob@cat.ie", id = 2),
    User(name = "Mary Contrary", email = "mary@contrary.com", id = 3),
    User(name = "Carol Singer", email = "carol@singer.com", id = 4)
)

val caloriestrackers = arrayListOf<CaloriesTrackerDC>(
    CaloriesTrackerDC(1,1, DateTime.now(),"Walking",10,40.0),
    CaloriesTrackerDC(2,2, DateTime.now(),"Jogging",8,106.64),
    CaloriesTrackerDC(3,3, DateTime.now(),"Cycling",15,99.9)
)

