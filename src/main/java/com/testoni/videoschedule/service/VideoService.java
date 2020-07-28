package com.testoni.videoschedule.service;

import com.testoni.videoschedule.document.VideoDocument;
import com.testoni.videoschedule.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VideoService {

    @Autowired
    VideoRepository videoRepository;

    public Page<VideoDocument> findAll(Pageable pageable, String flag) {
        if (flag != null && flag.equals("next")) {
            return videoRepository.findByVideoDateAfterOrderByVideoDateAsc(LocalDateTime.now(), pageable);
        } else if (flag != null && flag.equals("previous")) {
            return videoRepository.findByVideoDateBeforeOrderByVideoDateDesc(LocalDateTime.now(), pageable);
        } else {
            return videoRepository.findAll(pageable);
        }
    }

    public Optional<VideoDocument> findById(String id) {
        return videoRepository.findById(id);
    }

    public VideoDocument save(VideoDocument videoDocument) {
        return videoRepository.save(videoDocument);
    }

    public void delete(VideoDocument videoDocument) {
        videoRepository.delete(videoDocument);
    }

}
