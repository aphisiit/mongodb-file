package com.example.mongopdf.service

import com.example.mongopdf.domain.Photo
import com.example.mongopdf.repository.PhotoRepository
import org.bson.BsonBinarySubType
import org.bson.types.Binary
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

interface PhotoService {
    fun addPhoto(title: String, file: MultipartFile) : String?
    fun getPhoto(id: String) : Photo
}

@Service
class PhotoServiceImpl : PhotoService {

    @Autowired
    private lateinit var photoRepository : PhotoRepository

    override fun addPhoto(title: String, file: MultipartFile): String? {
        var photo = Photo()
        photo.image = Binary(BsonBinarySubType.BINARY,file.bytes)
        photo = photoRepository.insert(photo)
        return photo.id
    }

    override fun getPhoto(id: String): Photo {
        return photoRepository.findById(id).get()
    }
}
