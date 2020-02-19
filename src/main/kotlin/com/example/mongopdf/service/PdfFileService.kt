package com.example.mongopdf.service

import com.example.mongopdf.domain.PdfFile
import com.example.mongopdf.domain.Photo
import com.example.mongopdf.repository.PdfFileRepository
import org.bson.BsonBinarySubType
import org.bson.types.Binary
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

interface PdfFileService {
    fun findAllPdf() : List<PdfFile>
    fun addPdfFile(title: String, file: MultipartFile) : String?
    fun getPdfFile(id: String) : PdfFile
}

@Service
class PdfFileServiceImpl : PdfFileService {

    @Autowired
    private lateinit var pdfFileRepository: PdfFileRepository

    override fun findAllPdf(): List<PdfFile> {
        return pdfFileRepository.findAll()
    }

    override fun addPdfFile(title: String, file: MultipartFile): String? {
        var pdfFile = PdfFile()
        pdfFile.title = title
        pdfFile.file = Binary(BsonBinarySubType.BINARY,file.bytes)
        pdfFile = pdfFileRepository.insert(pdfFile)
        return pdfFile.id
    }

    override fun getPdfFile(id: String): PdfFile {
        return pdfFileRepository.findById(id).get()
    }
}
