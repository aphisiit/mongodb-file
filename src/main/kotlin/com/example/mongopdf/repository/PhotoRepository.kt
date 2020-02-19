package com.example.mongopdf.repository

import com.example.mongopdf.domain.Photo
import org.springframework.data.mongodb.repository.MongoRepository

interface PhotoRepository : MongoRepository<Photo, String>
