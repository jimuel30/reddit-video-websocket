package com.example.VideoMessageBroker.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Video {

    private long videoId;

    private String status;

    private Date dateRequested;

    private String url;

    private String title;

}
