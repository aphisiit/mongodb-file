package com.example.mongopdf.service

import com.example.mongopdf.domain.PdfFile
import com.example.mongopdf.domain.Photo
import com.example.mongopdf.repository.PdfFileRepository
import org.bson.BsonBinarySubType
import org.bson.types.Binary
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.lang.Exception

interface PdfFileService {
    fun findAllPdf() : List<PdfFile>
    fun addPdfFile(title: String, file: MultipartFile) : String?
    fun getPdfFile(id: String) : PdfFile
    fun deletePdfFile(id: String)
}

@Service
class PdfFileServiceImpl : PdfFileService {

    private val logger : Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private lateinit var pdfFileRepository: PdfFileRepository

    override fun findAllPdf(): List<PdfFile> {
        logger.info("find all pdf file")
        return pdfFileRepository.findAll()
    }

    @Transactional
    override fun addPdfFile(title: String, file: MultipartFile): String? {
        logger.info("title : $title")
        return try {
            var pdfFile = PdfFile()
            pdfFile.title = title
            pdfFile.file = Binary(BsonBinarySubType.BINARY, file.bytes)
            pdfFile = pdfFileRepository.insert(pdfFile)
            pdfFile.id
        }catch (e : Exception) {
            null
        }
    }

    override fun getPdfFile(id: String): PdfFile {
        logger.info("get pdf file id : $id")
        return pdfFileRepository.findById(id).get()
    }

    override fun deletePdfFile(id: String) {
        logger.info("delete pdf file : $id")
        pdfFileRepository.deleteById(id)
    }
}
