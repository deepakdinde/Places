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

package com.example.places.data

import com.example.places.data.model.Place

val PLACES = listOf(
    Place(
        0,
        "Tata Motors",
        "H3, H Block, MIDC, Pimpri Colony, Pimpri-Chinchwad, Pune, Maharashtra 411018.",
        18.6514022,73.8150023,9090909090
    ),
    Place(
        1,
        "Spine City Mall",
        "Santnagar, Spine Rd, PCNTDA, Sector No. 9, Moshi, Pimpri-Chinchwad, Maharashtra 412105",
        18.661931,73.820199,9090909090
    ),
    Place(
        2,
        "Tata Motors Commercial Vehicle Plant",
        "MR49+892, H Block, MIDC, Pimpri Colony, Pimpri-Chinchwad, Maharashtra 411018.",
        18.6589465,73.8138125,9090909090
    ),
    Place(
        3,
        "Hinjewadi Phase 1",
        "MR49+892, H Block, MIDC, Rohan Colony, Hinjewadi, Maharashtra 411018.",
        18.589800,73.743202,9090909090
    ),
    Place(
        4,
        "Pimpri Chowk",
        "Pimpri Chowk Underpass, Ajmera, Pimpri-Chinchwad - 411089, Maharashtra, India",
        18.6275114,73.8046814,9090909090
    ),
    Place(
        5,
        "Corinthians Club",
        "The Corinthians Club, Someshwar society road, Krushna Nagar, Pune - 411060, Maharashtra, India",
        18.463629,73.9195213,9090909090
    ),
    Place(
        6,
        "Vehicle Plant",
        "MR49+892, H Block, MIDC, Pimpri Colony, Pimpri-Chinchwad, Maharashtra 411018.",
        18.6589465,73.8138125,9090909090
    ),
    Place(
        7,
        "Aundh",
        "unnamed road, Aundh, Pune - 411027, Maharashtra, India",
        18.552970,73.804651,9090909090
    ),
)

class PlacesRepository {
    fun getPlaces(): List<Place> {
        return PLACES
    }

    fun getPlace(placeId: Int): Place? {
        return PLACES.find { it.id == placeId }
    }
}