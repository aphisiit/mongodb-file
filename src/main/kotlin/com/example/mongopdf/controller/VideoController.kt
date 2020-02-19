package com.example.mongopdf.controller

import com.example.mongopdf.domain.Video
import com.example.mongopdf.service.VideoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.util.FileCopyUtils
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/video")
class VideoController {

    @Autowired
    private lateinit var videoService: VideoService

    @PostMapping("/add")
    fun addVideo(@RequestParam("title") title: String, @RequestParam("file") file: MultipartFile, model: Model) : String {
        val id = videoService.addVideo(title,file)
        return "redirect:/video/$id"
    }

    @GetMapping("/{id}")
    fun  getVideo(@PathVariable id: String,model : Model) : String {
        val video: Video = videoService.getVideo(id)
        model.addAttribute("title",video.title)
        model.addAttribute("url", "/videos/stream/$id");
        return "videos";
    }

    @GetMapping("/stream/{id}")
    fun streamVideo(@PathVariable id: String,response: HttpServletResponse) {
        val video: Video = videoService.getVideo(id)
        video.stream?.let { FileCopyUtils.copy(it,response.outputStream) }
    }
}
