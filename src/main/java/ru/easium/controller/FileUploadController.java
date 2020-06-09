package ru.easium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.easium.domain.Course;
import ru.easium.domain.Teacher;
import ru.easium.service.CourseService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {


    @RequestMapping(path = "/courseUpload", method = RequestMethod.POST)
    public String handleFormUpload(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            try (FileOutputStream stream = new FileOutputStream("uploaded.jpg")) {
                stream.write(file.getBytes());
            }
            return "redirect:uploadSuccses";
        }
        return "redirect:uploadFailure";
    }
}

