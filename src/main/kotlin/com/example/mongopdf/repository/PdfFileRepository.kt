package com.example.mongopdf.repository

import com.example.mongopdf.domain.PdfFile
import org.springframework.data.mongodb.repository.MongoRepository

interface PdfFileRepository : MongoRepository<PdfFile,String> {
}
