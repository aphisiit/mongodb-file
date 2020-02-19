package com.example.mongopdf.service

import com.example.mongopdf.domain.Video
import com.mongodb.BasicDBObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.gridfs.GridFsOperations
import org.springframework.data.mongodb.gridfs.GridFsTemplate
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

interface VideoService {
    fun addVideo(title: String, file: MultipartFile) : String?
    fun getVideo(id: String) : Video
}

@Service
class VideoServiceImpl : VideoService {

    @Autowired private lateinit var  gridFsTemplate: GridFsTemplate
    @Autowired private lateinit var  operation: GridFsOperations



    override fun addVideo(title: String, file: MultipartFile): String? {
        val metaData = BasicDBObject()
        metaData["type"] = "video"
        metaData["title"] = title
        val id = gridFsTemplate.store(
                file.inputStream, file.name, file.contentType, metaData
        )
        return id.toString()
    }

    override fun getVideo(id: String) : Video {
        val file = gridFsTemplate.findOne(Query(Criteria.where("_id").`is`(id)))
        val video = Video()
        video.title = file.metadata?.get("title")?.toString()
        video.stream = operation.getResource(file).inputStream
        return video
    }

}
