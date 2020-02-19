package com.example.mongopdf.controller

import com.example.mongopdf.service.PhotoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Controller
@RequestMapping("/photo")
class PhotoController {

    @Autowired
    private lateinit var photoService: PhotoService

    @GetMapping("/")
    fun index() : String {
        return "uploadPhoto"
    }

    @PostMapping("/add")
    fun addPhoto(@RequestParam("title") title: String,
                 @RequestParam("image") image: MultipartFile, model: Model) : String {
        val id = photoService.addPhoto(title,image)
        return "redirect:/photo/$id"
    }

    @GetMapping("/{id}")
    fun getPhoto(@PathVariable id: String, model: Model) : String {
        val photo = photoService.getPhoto(id)
        model.addAttribute("title",photo.title)
        model.addAttribute("image", Base64.getEncoder().encodeToString(photo.image?.data))
        return "photo"
    }
}
