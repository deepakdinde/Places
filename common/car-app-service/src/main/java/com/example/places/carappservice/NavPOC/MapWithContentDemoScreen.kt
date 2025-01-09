package com.example.places.carappservice.NavPOC

import android.R
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action.BACK
import androidx.car.app.model.Header
import androidx.car.app.model.ItemList
import androidx.car.app.model.ListTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import androidx.car.app.sample.showcase.common.screens.mapdemos.mapwithcontent.MapTemplateWithToggleDemoScreen
import androidx.car.app.sample.showcase.common.screens.mapdemos.mapwithcontent.MapWithGridTemplateDemoScreen
import androidx.car.app.sample.showcase.common.screens.mapdemos.mapwithcontent.MapWithListTemplateDemoScreen
import androidx.car.app.sample.showcase.common.screens.mapdemos.mapwithcontent.MapWithMessageTemplateDemoScreen
import androidx.car.app.sample.showcase.common.screens.mapdemos.mapwithcontent.MapWithPaneTemplateDemoScreen
import androidx.car.app.versioning.CarAppApiLevels


class MapWithContentDemoScreen(carContext: CarContext) : Screen(carContext) {

    fun MapWithContentDemoScreen(carContext: CarContext) {
        super(carContext)
    }

    override fun onGetTemplate(): Template {
        val screenList: MutableList<Row> = ArrayList()
        if (carContext.carAppApiLevel >= CarAppApiLevels.LEVEL_7) {
            screenList.add(
                buildRowForTemplate(
                    MapWithMessageTemplateDemoScreen(
                        carContext
                    ),
                    R.string.map_with_message_demo_title
                )
            )
            screenList.add(
                buildRowForTemplate(
                    MapWithGridTemplateDemoScreen(carContext),
                    R.string.map_with_grid_demo_title
                )
            )
        }

        screenList.add(
            buildRowForTemplate(
                MapWithListTemplateDemoScreen(carContext),
                R.string.map_template_list_demo_title
            )
        )
        screenList.add(
            buildRowForTemplate(
                MapWithPaneTemplateDemoScreen(carContext),
                R.string.map_template_pane_demo_title
            )
        )

        if (carContext.carAppApiLevel >= CarAppApiLevels.LEVEL_6) {
            screenList.add(
                buildRowForTemplate(
                    MapTemplateWithToggleDemoScreen(carContext),
                    R.string.map_template_toggle_demo_title
                )
            )
        }

        val listBuilder = ItemList.Builder()

        for (i in screenList.indices) {
            listBuilder.addItem(screenList[i])
        }

        return ListTemplate.Builder()
            .setSingleList(listBuilder.build())
            .setHeader(
                Header.Builder()
                    .setTitle(carContext.getString(R.string.map_demos_title))
                    .setStartHeaderAction(BACK)
                    .build()
            )
            .build()
    }

    private fun buildRowForTemplate(screen: Screen, title: Int): Row {
        return Row.Builder()
            .setTitle(carContext.getString(title))
            .setOnClickListener { screenManager.push(screen) }
            .build()
    }
}