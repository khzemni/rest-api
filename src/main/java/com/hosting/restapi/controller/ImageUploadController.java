package com.hosting.restapi.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hosting.restapi.entity.OfferImage;
import com.hosting.restapi.service.ImageUploadService;

@RestController
@RequestMapping("/api/v1/fimages")
@CrossOrigin(origins = "*", methods= {RequestMethod.POST, RequestMethod.DELETE})
public class ImageUploadController {
	
	@Autowired
	private final ImageUploadService imageUploadService;
	
	ImageUploadController(ImageUploadService imageUploadService) {
		this.imageUploadService = imageUploadService;
	}
	
	@PostMapping()
	public List<OfferImage> uploadImage(
			@RequestParam("file") MultipartFile file, 
			@RequestParam("offerId") String offerId) 
					throws IOException {
		return imageUploadService.upload(file, Long.valueOf(offerId));
	}
	
	@DeleteMapping()
	public void deleteImage(
			@RequestParam("image") String image, 
			@RequestParam("offer") String offer) 
					throws IOException {
		this.imageUploadService.delete(image, Long.valueOf(offer));
	}
	

}
