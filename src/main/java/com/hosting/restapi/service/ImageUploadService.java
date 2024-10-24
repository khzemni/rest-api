package com.hosting.restapi.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hosting.restapi.entity.Offer;
import com.hosting.restapi.entity.OfferImage;
import com.hosting.restapi.repository.OfferImageRepository;
import com.hosting.restapi.repository.OfferRepository;

@Service
public class ImageUploadService {
	
	@Value("${original.image.folder}")
    private String originalSizeImageFolder;
	
	@Value("${resized.image.folder}")
    private String resizedImageFolder;
	
	
	@Autowired
	private final OfferImageRepository offerImageRepository;
	@Autowired
	private final OfferRepository offerRepository;
	
	ImageUploadService(OfferImageRepository offerImageRepository, OfferRepository offerRepository) {
		this.offerImageRepository = offerImageRepository;
		this.offerRepository = offerRepository;
	}
	
//	public Offer upload(MultipartFile imageFile, Long offerId) throws IOException {
//		
//		Offer offer = this.offerRepository.findById(offerId)
//				.orElseThrow(() -> new RuntimeException("offer not exist"));
//		
//		// Offer Image instance
//		OfferImage originalImage = new OfferImage();
//		OfferImage resizedImage = new OfferImage();
//		
//		// Original directory
//		String originalSizePath = originalSizeImageFolder.concat(String.valueOf(offer.getId()+"\\"));
////		String resizedPath = resizedImageFolder.concat(String.valueOf(offer.getId()+"\\"));
//		String resizedPath = originalSizePath.concat("resized\\");
//
//		// Offer image directories
//		File OriginalSizeImageDirectory = new File(originalSizePath);
//		File ResizedImageDirectory = new File(resizedPath);
//		
//		// Create images directories if not exists 
//		if(!OriginalSizeImageDirectory.exists()) 
//			OriginalSizeImageDirectory.mkdir();
//		if(!ResizedImageDirectory.exists())
//			ResizedImageDirectory.mkdir();
//			
//		// Paths to save image file (original size and resized)
//		Path path1 = Paths.get(originalSizePath, imageFile.getOriginalFilename());
//		Path path2 = Paths.get(resizedPath, imageFile.getOriginalFilename());
//		
//		// Write original image
//		Files.write(path1, imageFile.getBytes());
//		
////		String newFileName = FilenameUtils.getBaseName(toResize.getName()) + FilenameUtils.getExtension(toResize.getName());
//		originalImage.setName(imageFile.getOriginalFilename());
//		originalImage.setType(imageFile.getContentType());
//		originalImage.setPath(path1.toString());
//		OfferImage savedOriginalImage = offerImageRepository.save(originalImage);
//		offer.getImages().add(savedOriginalImage);
//		this.offerRepository.save(offer);
//		
//		File toResize = path2.toFile();
//		
//		this.resizeImage(toResize);
//		String newFileName = FilenameUtils.getBaseName(toResize.getName()) +"."+ FilenameUtils.getExtension(toResize.getName());
//		resizedImage.setName(newFileName);
//		resizedImage.setType(imageFile.getContentType());
//		resizedImage.setPath(path2.toString());
//		OfferImage savedResizedImage = offerImageRepository.save(resizedImage);
//		offer.getImages().add(savedResizedImage);
//		
//
////		offer.getImages().addAll(Arrays.asList(savedOriginalImage, savedResizedImage));
//		return this.offerRepository.save(offer);
//	}
	
	public List<OfferImage> upload(MultipartFile imageFile, Long offerId) throws IOException {
		Offer offer = this.offerRepository.findById(offerId)
		.orElseThrow(() -> new RuntimeException("offer not exist"));
		// Offer Image instance
		OfferImage originalImage = new OfferImage();
		OfferImage resizedImage = new OfferImage();
		
		// Original directory
		String originalSizePath = originalSizeImageFolder.concat(String.valueOf(offer.getId()+"\\"));
		String resizedPath = originalSizePath.concat("resized\\");

		// Offer image directories
		File OriginalSizeImageDirectory = new File(originalSizePath);
		File ResizedImageDirectory = new File(resizedPath);
		
		// Create images directories if not exists 
		if(!OriginalSizeImageDirectory.exists()) 
			OriginalSizeImageDirectory.mkdir();
		if(!ResizedImageDirectory.exists())
			ResizedImageDirectory.mkdir();
			
		// Paths to save image file (original size and resized)
		Path path1 = Paths.get(originalSizePath, imageFile.getOriginalFilename());
		Path path2 = Paths.get(resizedPath, imageFile.getOriginalFilename());
		
//		Path path = Paths.get(originalSizeImageFolder, imageFile.getOriginalFilename());
		Files.write(path1, imageFile.getBytes());
		File toResize = path1.toFile();
		this.resizeImage(toResize, path2);
		
//		String newFileName = FilenameUtils.getBaseName(toResize.getName())
//                + "_600."
//                + FilenameUtils.getExtension(toResize.getName());
		
		originalImage.setName(imageFile.getOriginalFilename());
		originalImage.setType(imageFile.getContentType());
		originalImage.setPath(path1.toString());
		
		resizedImage.setName(imageFile.getOriginalFilename());
		resizedImage.setType(imageFile.getContentType());
		resizedImage.setPath(path2.toString());
		OfferImage savedOriginalImage = offerImageRepository.save(originalImage);
		OfferImage savedResizedImage = offerImageRepository.save(resizedImage);
		offer.getImages().addAll(Arrays.asList(savedOriginalImage, savedResizedImage));
//		offer.getImages().addAll(Arrays.asList(savedOriginalImage));
		this.offerRepository.save(offer);
		return Arrays.asList(savedOriginalImage, savedResizedImage);
	}
	
	private boolean resizeImage(File sourceFile, Path path) {
        try {
            BufferedImage bufferedImage = ImageIO.read(sourceFile);
            BufferedImage outputImage = Scalr.resize(bufferedImage, 400);
//            String newFileName = FilenameUtils.getBaseName(sourceFile.getName())+"."+FilenameUtils.getExtension(sourceFile.getName());
            File newImageFile = path.toFile();
            ImageIO.write(outputImage, "jpg", newImageFile);
            outputImage.flush();
            return true;
        } catch (IOException e) {
//            logger.error(e.getMessage(), e);
            return false;
        }
    }

//	public void delete(String imageName, Long offerId) {
//		// TODO Auto-generated method stub
//		OfferImage toDelete = this.offerImageRepository.findByNameAndOfferID(imageName, offerId);
//		this.offerImageRepository.delete(toDelete);
//	}
	
	public void delete(String imageName, Long offerId) {
		// TODO Auto-generated method stub
		
		Offer offer = this.offerRepository.findById(offerId).orElseThrow(() -> new RuntimeException("Offer does not exist"));
		
		List<OfferImage> toDelete = offer.getImages()
				.stream()
				.filter(image -> image.getName().equals(imageName))
				.collect(Collectors.toList());
		
		for(OfferImage image: toDelete) {
			String imagePath = image.getPath();
			File file = new File(imagePath);
			if (file.exists()) {
	            // Attempt to delete the file
	            if (file.delete()) {
	                System.out.println("File deleted successfully.");
	            } else {
	                System.out.println("Failed to delete the file.");
	            }
	        } else {
	            System.out.println("File does not exist.");
	        }
		}
		this.offerImageRepository.deleteAll(toDelete);
	}

}
