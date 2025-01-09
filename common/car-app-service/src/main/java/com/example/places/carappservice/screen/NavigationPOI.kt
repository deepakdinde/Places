package com.example.places.carappservice.screen

import android.os.Build
import androidx.annotation.OptIn
import androidx.annotation.RequiresApi
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.annotations.ExperimentalCarApi
import androidx.car.app.model.Action
import androidx.car.app.model.ActionStrip
import androidx.car.app.model.Distance
import androidx.car.app.model.Pane
import androidx.car.app.model.PaneTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import androidx.car.app.navigation.model.MapController
import androidx.car.app.navigation.model.MapWithContentTemplate
import androidx.car.app.navigation.model.NavigationTemplate
import androidx.car.app.navigation.model.RoutingInfo
import androidx.car.app.navigation.model.TravelEstimate
import java.time.Duration
import java.time.ZoneId
import java.time.ZonedDateTime

class NavigationPOI (carContext: CarContext, private val placeId: Int) : Screen(carContext) {

    @OptIn(ExperimentalCarApi::class) @RequiresApi(Build.VERSION_CODES.O)
    override fun onGetTemplate(): Template {

        val distance = Distance.create(10.0, Distance.UNIT_KILOMETERS)

        // Create an arrival time using ZonedDateTime
        val arrivalTime = ZonedDateTime.now(ZoneId.systemDefault()).plus(Duration.ofMinutes(15))

        // Construct the TravelEstimate
        val travelEstimate = TravelEstimate.Builder(distance, arrivalTime).build()

        // Create Routing Info
                /*val routingInfo = RoutingInfo.Builder()
                    //.setCurrentTravelEstimate(travelEstimate) // Required for navigation
                    .build()*/

        val row = Row.Builder().setTitle("Hello World!").build()
        val pane = Pane.Builder().addRow(row).build()
        val paneTemp = PaneTemplate.Builder(pane)
            .setHeaderAction(Action.APP_ICON)
            .build()


        val mapController = MapController.Builder()
            /*.setMapActionStrip(ActionStrip.Builder()
            .addAction(
                Action.Builder()
                    .setTitle("Searchh")
                    .setOnClickListener {
                        // Handle search button click
                    }
                    .build()
            )
            .build())*/


        return MapWithContentTemplate.Builder()
            .setContentTemplate(paneTemp)
            /*.setActionStrip(ActionStrip.Builder()
                .addAction(
                    Action.Builder()
                        .setTitle("Search")
                        .setOnClickListener {
                            // Handle search button click
                        }
                        .build()
                )
                .build())*/
            .setMapController(mapController.build())
            .build()
    }

}