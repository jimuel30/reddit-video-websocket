package com.example.VideoMessageBroker.controller;

import com.example.VideoMessageBroker.model.Video;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    private static final Logger LOG = LoggerFactory.getLogger(MessageController.class);


    public MessageController(final SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }


    @MessageMapping("/send/{videoId}") // This handles messages prefixed with /app/send/{videoId}
    public void sendMessageToTopic(@Payload  Video video) {
        // Send message to specific topic based on videoId
        LOG.info("Message received from client: {}",video);
        final String topic = "/topic/" + video.getVideoId();
        LOG.info("Topic Destination: {}",topic);
        simpMessagingTemplate.convertAndSend(topic, video);
    }




}
