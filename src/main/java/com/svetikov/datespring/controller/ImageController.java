package com.svetikov.datespring.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.svetikov.datespring.models.ImagesModel;
import com.svetikov.datespring.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class ImageController {

    @Autowired
    ImageRepository imageRepository;

    @GetMapping("/")
    public String welcome() {
        return "save_img";
    }

    @PostMapping("/save/image")
    public String saveImage(@RequestParam("img") MultipartFile img) throws IOException {
        ImagesModel imagesModel = new ImagesModel();
        Date date = new Date();
        imagesModel.setBlob_image(img.getBytes());
        imagesModel.setDate_image(date);
        imageRepository.save(imagesModel);
        return "redirect:/show/all/image";
    }

    @GetMapping("/show/all/image")
    public String showPageImage(Model model) {

        List<ImagesModel> img_model = new ArrayList<>();

        for (ImagesModel im : imageRepository.findAll()) {
            img_model.add(im);
        }
        model.addAttribute("img_models", img_model);
        return "show_all_img";
    }

    @ResponseBody
    @GetMapping(value = "/show/all/image/{id_image}", consumes = MediaType.ALL_VALUE, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> showImg(@PathVariable long id_image) {

        return ResponseEntity.ok(imageRepository.findById(id_image).get().getBlob_image());
    }

}
