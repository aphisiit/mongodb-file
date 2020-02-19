package com.example.mongopdf.domain

import org.bson.types.Binary
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

data class PdfFile (
        @Id var id: String? = null,
        var title : String? = null,
        var file: Binary? = null
)
