package com.example.mongopdf.domain

import java.io.InputStream

data class Video (
        var title: String? = null,
        var stream: InputStream? = null
) {
}
