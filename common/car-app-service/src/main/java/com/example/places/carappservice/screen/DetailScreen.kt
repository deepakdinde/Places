/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.places.carappservice.screen

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.net.Uri
import android.util.Log
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.IconCompat
import com.example.android.cars.carappservice.R
import com.example.places.data.PlacesRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

//import com.example.places.data.model.toIntent

class DetailScreen(carContext: CarContext, private val placeId: Int) : Screen(carContext) {
    private var isFavorite = false

    override fun onGetTemplate(): Template {
        val place = PlacesRepository().getPlace(placeId)
            ?: return MessageTemplate.Builder("Place not found")
                .setHeaderAction(Action.BACK)
                .build()

        // Check for location permission
        val permissionGranted = ContextCompat.checkSelfPermission(
            carContext,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (!permissionGranted) {
            return PaneTemplate.Builder(
                Pane.Builder()
                    .addRow(
                        Row.Builder()
                            .setTitle("Location permission is required.")
                            .addText("Please enable it in settings.")
                            .build()
                    )
                    .addAction(
                        Action.Builder()
                            .setTitle("Go to Settings")
                            .setOnClickListener {
                                // Direct the user to the app's settings page to enable the permission
                                /*val intent = Intent(
                                    android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                    Uri.parse("package:${carContext.packageName}")
                                )
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                carContext.startActivity(intent)*/
                            }
                            .build()
                    )
                    .build()
            )
                .setTitle("Permission Needed")
                .setHeaderAction(Action.BACK)
                .build()

        }

        val navigateAction = Action.Builder()
            .setTitle("Navigate")
            .setIcon(
                CarIcon.Builder(
                    IconCompat.createWithResource(
                        carContext,
                        R.drawable.baseline_navigation_24
                    )
                ).build()
            )
            // Only certain Intent actions are supported by `startCarApp`. Check its documentation
            // for all of the details. To open another app that can handle navigating to a location
            // you must use the CarContext.ACTION_NAVIGATE action and not Intent.ACTION_VIEW like
            // you might on a phone.
            .setOnClickListener {

                screenManager.push(NavigationPOI(carContext, placeId))


               /* val latitude = place.latitude
                val longitude = place.longitude

                 val geoUri = Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude")
                 Log.d("uj","Lat $latitude Long $longitude")
                // val geoUri = Uri.parse("geo:0,0?q=$latitude,$longitude")
                 val intent = Intent(Intent.ACTION_VIEW, geoUri).apply {
                     setPackage("com.google.android.apps.maps")
                 }
                 intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                 carContext.startActivity(intent)

                var URL =
                    "https://www.google.com/maps/dir/?api=1&travelmode=driving&dir_action=navigate&destination=18.5569149,73.7666521&origin=$latitude,$longitude"
                var location = Uri.parse(URL)
                var mapIntent = Intent(Intent.ACTION_VIEW, location)
                mapIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                carContext.startActivity(mapIntent)*/


            }
            .build()

        // Add Call Action
        val callAction = Action.Builder()
            .setTitle("Call")
            .setIcon(
                CarIcon.Builder(
                    IconCompat.createWithResource(
                        carContext,
                        androidx.core.R.drawable.ic_call_answer  // Replace with appropriate call icon resource
                    )
                ).build()
            )
            .setOnClickListener {
                screenManager.push(NavigationPOI(carContext, placeId))
                /*val phoneNumber = place.mobileNumber  // Ensure that the place has a phoneNumber field

                val callIntent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$phoneNumber")
                }
                callIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                carContext.startActivity(callIntent)*/
                //screenManager.push(SignInScreen(carContext))
            }
            .build()


        val actionStrip = ActionStrip.Builder()
            .addAction(
                Action.Builder()
                    .setIcon(
                        CarIcon.Builder(
                            IconCompat.createWithResource(
                                carContext,
                                R.drawable.baseline_favorite_24
                            )
                        ).setTint(
                            if (isFavorite) CarColor.RED else CarColor.createCustom(
                                Color.LTGRAY,
                                Color.DKGRAY
                            )
                        ).build()
                    )
                    .setOnClickListener {
                        isFavorite = !isFavorite
                        // Request that `onGetTemplate` be called again so that updates to the
                        // screen's state can be picked up
                        invalidate()
                    }.build()
            )
            .build()

        return PaneTemplate.Builder(
            Pane.Builder()
                .addAction(callAction)
                .addAction(navigateAction)
                .addRow(
                    Row.Builder()
                        .setTitle("Coordinates")
                        .addText("${place.latitude}, ${place.longitude}")
                        .build()
                ).addRow(
                    Row.Builder()
                        .setTitle("Description")
                        .addText(place.description)
                        .build()
                ).build()
        )
            .setTitle(place.name)
            .setHeaderAction(Action.BACK)
            .setActionStrip(actionStrip)
            .build()
    }

}