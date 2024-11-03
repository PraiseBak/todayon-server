package com.kkpae.todayon.dto;

import org.springframework.web.multipart.MultipartFile;

public record ObjectRequest(String goal, MultipartFile goalCheckImage) {
}
