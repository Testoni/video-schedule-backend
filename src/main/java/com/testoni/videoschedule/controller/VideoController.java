package com.testoni.videoschedule.controller;

import com.testoni.videoschedule.document.VideoDocument;
import com.testoni.videoschedule.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class VideoController {

    @Autowired
    VideoService videoService;

    @GetMapping("/videos")
    public ResponseEntity<Page<VideoDocument>> getAllVideos(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                            @RequestParam(required = false) String flag) {
        Page<VideoDocument> videoPage = videoService.findAll(pageable, flag);
        if (videoPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Page<VideoDocument>>(videoPage, HttpStatus.OK);
        }
    }

    @GetMapping("/videos/{id}")
    public ResponseEntity<VideoDocument> getOneVideo(@PathVariable(value = "id") String id) {
        Optional<VideoDocument> videoO = videoService.findById(id);
        if (!videoO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<VideoDocument>(videoO.get(), HttpStatus.OK);
        }
    }

    @PostMapping("/videos")
    public ResponseEntity<VideoDocument> saveVideo(@RequestBody VideoDocument video) {
        video.setRegistrationDate(LocalDateTime.now());
        return new ResponseEntity<VideoDocument>(videoService.save(video), HttpStatus.CREATED);
    }

    @DeleteMapping("/videos/{id}")
    public ResponseEntity<?> deleteVideo(@PathVariable(value = "id") String id) {
        Optional<VideoDocument> videoO = videoService.findById(id);
        if (!videoO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            videoService.delete(videoO.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/videos/{id}")
    public ResponseEntity<VideoDocument> updateVideo(@PathVariable(value = "id") String id,
                                                     @RequestBody VideoDocument videoDocument) {
        Optional<VideoDocument> videoO = videoService.findById(id);
        if (!videoO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            videoDocument.setId(videoO.get().getId());
            return new ResponseEntity<VideoDocument>(videoService.save(videoDocument), HttpStatus.OK);
        }
    }

}
