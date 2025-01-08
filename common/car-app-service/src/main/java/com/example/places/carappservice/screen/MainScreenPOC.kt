package com.example.places.carappservice.screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.car.app.CarContext
import androidx.car.app.CarToast
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.ActionStrip
import androidx.car.app.model.Distance
import androidx.car.app.model.Template
import androidx.car.app.navigation.NavigationManager
import androidx.car.app.navigation.NavigationManagerCallback
import androidx.car.app.navigation.model.NavigationTemplate
import androidx.car.app.navigation.model.RoutingInfo
import androidx.car.app.navigation.model.TravelEstimate
import com.example.places.carappservice.Navigation.nav.NavigationService
import java.time.Duration
import java.time.ZoneId
import java.time.ZonedDateTime

class MainScreenPOC(carContext: CarContext) : Screen(carContext) {

    private val navigationManager = carContext.getCarService(NavigationManager::class.java)


    init {
        navigationManager.setNavigationManagerCallback(
            object : NavigationManagerCallback {
                override fun onStopNavigation() {
                    stopNavigation()
                }

                override fun onAutoDriveEnabled() {
                    Log.d("NavigationService", "onAutoDriveEnabled called")
                    CarToast.makeText(carContext, "Auto drive enabled", CarToast.LENGTH_LONG).show()
                }
            }
        )
        navigationManager.navigationStarted()
    }

    private fun stopNavigation() {
        navigationManager.navigationEnded()
        CarToast.makeText(carContext, "Navigation Stopped", CarToast.LENGTH_SHORT).show()
    }




    @RequiresApi(Build.VERSION_CODES.O)
    override fun onGetTemplate(): Template {

        val distance = Distance.create(10.0, Distance.UNIT_KILOMETERS)

        // Create an arrival time using ZonedDateTime
        val arrivalTime = ZonedDateTime.now(ZoneId.systemDefault()).plus(Duration.ofMinutes(15))

        // Construct the TravelEstimate
        val travelEstimate = TravelEstimate.Builder(distance, arrivalTime).build()

        // Create Routing Info
/*        val routingInfo = RoutingInfo.Builder()
            //.setCurrentTravelEstimate(travelEstimate) // Required for navigation
            .build()*/

        // Create and return Navigation Template
        return NavigationTemplate.Builder()
            //.setNavigationInfo(routingInfo)
            .setActionStrip(
                ActionStrip.Builder()
                    .addAction(
                        Action.Builder()
                            .setTitle("Search")
                            .setOnClickListener {
                                // Handle search button click
                            }
                            .build()
                    )
                    .build()
            )
            //.setHeaderAction(Action.APP_ICON) // Add header action
            .build()

        /*val distance = Distance.create(10.0, Distance.UNIT_KILOMETERS)

        // Create an arrival time using ZonedDateTime
        val arrivalTime = ZonedDateTime.now(ZoneId.systemDefault()).plus(Duration.ofMinutes(15))

        // Construct the TravelEstimate with the correct arguments
        val travelEstimate = TravelEstimate.Builder(distance, arrivalTime).build()


        // Create Routing Info
        val routingInfo = RoutingInfo.Builder()
            //.setCurrentTravelEstimate(travelEstimate)
            .build()


            // Create Navigation Template
            return NavigationTemplate.Builder()
                //.setNavigationInfo(routingInfo)
                .setActionStrip(
                    ActionStrip.Builder()
                        .addAction(
                            Action.Builder()
                                .setTitle("Search")
                                .setOnClickListener {
                                    // Handle search button click
                                }
                                .build()
                        )
                        .build()
                )
                //.setHeaderAction(Action.APP_ICON)
                .build()*/
        }
}