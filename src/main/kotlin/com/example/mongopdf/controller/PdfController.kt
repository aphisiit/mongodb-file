package com.example.mongopdf.controller

import com.example.mongopdf.service.PdfFileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Controller
@RequestMapping("/pdf")
class PdfController {

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
}
