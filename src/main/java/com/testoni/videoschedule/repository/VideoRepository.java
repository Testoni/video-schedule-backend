package com.testoni.videoschedule.repository;

import com.testoni.videoschedule.document.VideoDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface VideoRepository extends MongoRepository<VideoDocument, String> {

    Page<VideoDocument> findByVideoDateAfterOrderByVideoDateAsc(LocalDateTime date, Pageable pageable);

    Page<VideoDocument> findByVideoDateBeforeOrderByVideoDateDesc(LocalDateTime date, Pageable pageable);

}
