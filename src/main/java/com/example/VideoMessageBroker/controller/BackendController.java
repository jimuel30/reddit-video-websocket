package com.example.VideoMessageBroker.controller;

import com.example.VideoMessageBroker.model.Video;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BackendController {


    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);

    private final SimpMessagingTemplate simpMessagingTemplate;

    public BackendController(final SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }


    @PostMapping("/notifyFrontEnd")
    public ResponseEntity<?> notifyFrontEnd(@RequestBody Video video){
        LOG.info("Message from Backend received: {}",video);
        final String topic = "/topic/" + video.getVideoId();
        simpMessagingTemplate.convertAndSend(topic, video);
        return ResponseEntity.ok().body(video);
    }

}
