package com.example.places.carappservice.NavPOC

import android.R
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.CarIcon
import androidx.car.app.model.ItemList
import androidx.car.app.model.ListTemplate
import androidx.car.app.model.Template
import androidx.car.app.model.Toggle
import androidx.car.app.navigation.model.MapWithContentTemplate
import androidx.core.graphics.drawable.IconCompat


class MapTemplateWithToggleDemoScreen(carContext: CarContext) : Screen(carContext) {
    private var mAvoidTolls = false
    private var mAvoidHighways = false
    private var mAvoidFerries = false

    fun MapTemplateWithToggleDemoScreen(carContext: CarContext) {
        super(carContext)
    }

    override fun onGetTemplate(): Template {
        val mToggleForTolls = Toggle.Builder { checked: Boolean ->
            mAvoidTolls = !mAvoidTolls
            invalidate()
        }.setChecked(mAvoidTolls).build()

        val mToggleForHighways = Toggle.Builder { checked: Boolean ->
            mAvoidHighways = !mAvoidHighways
            invalidate()
        }.setChecked(mAvoidHighways).build()

        val mToggleForFerries = Toggle.Builder { checked: Boolean ->
            mAvoidFerries = !mAvoidFerries
            invalidate()
        }.setChecked(mAvoidFerries).build()

        val listBuilder = ItemList.Builder()

        listBuilder.addItem(
            buildRowForTemplate(
                R.string.avoid_tolls_row_title,
                mToggleForTolls, buildCarIcon(R.drawable.baseline_toll_24)
            )
        )

        listBuilder.addItem(
            buildRowForTemplate(
                R.string.avoid_highways_row_title,
                mToggleForHighways, buildCarIcon(R.drawable.baseline_add_road_24)
            )
        )

        listBuilder.addItem(
            buildRowForTemplate(
                R.string.avoid_ferries_row_title,
                mToggleForFerries, buildCarIcon(R.drawable.baseline_directions_boat_filled_24)
            )
        )

        val header: Header = Builder()
            .setStartHeaderAction(Action.BACK)
            .addEndHeaderAction(Builder()
                .setOnClickListener { finish() }
                .setIcon(
                    CarIcon.Builder(
                        IconCompat.createWithResource(
                            carContext,
                            R.drawable.ic_close_white_24dp
                        )
                    )
                        .build()
                )
                .build())
            .setTitle(carContext.getString(R.string.route_options_demo_title))
            .build()

        val builder = MapWithContentTemplate.Builder()
            .setContentTemplate(
                ListTemplate.Builder()
                    .setSingleList(listBuilder.build())
                    .setHeader(header)
                    .build()
            )
        return builder.build()
    }

    private fun buildCarIcon(icon: Int): CarIcon {
        return CarIcon.Builder(IconCompat.createWithResource(carContext, icon))
            .build()
    }

    private fun buildRowForTemplate(title: Int, toggle: Toggle, icon: CarIcon): Row {
        return Builder()
            .setTitle(carContext.getString(title))
            .setImage(icon)
            .setToggle(toggle)
            .build()
    }
}