package com.example.mongopdf.domain

import org.bson.types.Binary
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document(collection = "photos")
data class Photo(
        @Id var id: String? = null,
        var title: String? = null,
        var image: Binary? = null
)
