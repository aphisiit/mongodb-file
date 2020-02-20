package com.example.mongopdf.controller

import com.example.mongopdf.service.PdfFileService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*
import kotlin.Exception

@Controller
@RequestMapping("/pdf")
class PdfController {

    var logger : Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private lateinit var pdfFileService: PdfFileService

    @GetMapping("/")
    fun findAllPdf(model: Model) : String{
        var listPdf = pdfFileService.findAllPdf()
        model.addAttribute("listPdf",listPdf)
        return "pdfList"
    }

    @GetMapping("/upload")
    fun uploadPdf(model: Model) : String{
        return "pdfUpload"
    }

    @PostMapping("/add")
    fun addPhoto(@RequestParam("title") title: String,
                 @RequestParam("pdf") file: MultipartFile, model: Model) : String {
        val id = pdfFileService.addPdfFile(title,file)
        return "redirect:/pdf/$id"
    }

    @GetMapping("/{id}")
    fun getPhoto(@PathVariable id: String, model: Model) : String {
        val photo = pdfFileService.getPdfFile(id)
        model.addAttribute("title",photo.title)
        model.addAttribute("pdfFile", Base64.getEncoder().encodeToString(photo.file?.data))
        return "pdf"
    }

    @PostMapping("/add10k")
    fun addPhoto10k(@RequestParam("title") title: String,
                 @RequestParam("pdf") file: MultipartFile, model: Model) : String {
        var id : String? = null
        try {
            for (i in 1..10000) {
                logger.info("file index $i")
                id = pdfFileService.addPdfFile(title, file)
            }
        }catch (e: Exception){
            logger.error("$e")
        }
        return "redirect:/pdf/$id"
    }

    @PostMapping("/add100k")
    fun addPhoto100k(@RequestParam("title") title: String,
                 @RequestParam("pdf") file: MultipartFile, model: Model) : String {
        var id : String? = null
        try {
            for (i in 1..100000) {
                logger.info("file index $i")
                id = pdfFileService.addPdfFile(title, file)
            }
        }catch (e: Exception){
            logger.error("$e")
        }
        return "redirect:/pdf/$id"
    }
}
