package com.hosting.restapi.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

@Entity
@DynamicUpdate
public class Offer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@Column(columnDefinition = "TINYINT DEFAULT 0")
	private Integer finishedSteps = 0;
	
	@Column(columnDefinition = "BIT DEFAULT 0")
	private Boolean creationFinished = false;
	
	@Column(columnDefinition = "BIT DEFAULT 0")
	private Boolean isPublished = false;
	private String title;
	private String description;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "offer_type")
	private OfferType type;
	
	@OneToMany(mappedBy = "offer", orphanRemoval=true)
	Set<OfferReservationOption> reservationOptions;
	
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "offer_id")
	private Set<OfferImage> images = new HashSet<>();
	
	@OneToOne()
	@JoinColumn(name="lodging_id")
	@JsonIgnoreProperties(value = "offer")
	private Lodging lodging;
	
	@Column(columnDefinition = "BIT DEFAULT 0")
	private Boolean autoReservation = false;
	
	@Column(columnDefinition = "BIT DEFAULT 0")
	private Boolean autoAvailable = false;
	
	@Transient
	private Boolean isAvailable;
	
	@OneToMany(mappedBy = "offer", fetch=FetchType.LAZY)
	@JsonIgnore
	Set<Reservation> reservations;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "user_id", nullable = true)
	@JsonIgnore
	private User host;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getFinishedSteps() {
		return finishedSteps;
	}

	public void setFinishedSteps(Integer finishedSteps) {
		this.finishedSteps = finishedSteps;
	}

	public Boolean getCreationFinished() {
		return creationFinished;
	}

	public void setCreationFinished(Boolean creationFinished) {
		this.creationFinished = creationFinished;
	}

	public Boolean getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(Boolean isPublished) {
		this.isPublished = isPublished;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public OfferType getType() {
		return type;
	}

	public void setType(OfferType type) {
		this.type = type;
	}

	public Lodging getLodging() {
		return lodging;
	}

	public void setLodging(Lodging lodging) {
		this.lodging = lodging;
	}

	public Boolean getAutoReservation() {
		return autoReservation;
	}

	public void setAutoReservation(Boolean autoReservation) {
		this.autoReservation = autoReservation;
	}

	public Boolean getAutoAvailable() {
		return autoAvailable;
	}

	public void setAutoAvailable(Boolean autoAvailable) {
		this.autoAvailable = autoAvailable;
	}
	
	public Set<OfferImage> getImages() {
		return images;
	}

	public void setImages(Set<OfferImage> images) {
		this.images = images;
	}
	
	public Set<OfferReservationOption> getReservationOptions() {
		return reservationOptions;
	}

	public void setReservationOptions(Set<OfferReservationOption> reservationOptions) {
		this.reservationOptions = reservationOptions;
	}
	
	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", finishedSteps="
				+ finishedSteps + ", creationFinished=" + creationFinished + ", isPublished=" + isPublished + ", title="
				+ title + ", description=" + description + ", type=" + type + ", reservationOptions="
				+ reservationOptions + ", images=" + images + ", lodging=" + lodging + ", autoReservation="
				+ autoReservation + ", autoAvailable=" + autoAvailable + ", isAvailable=" + isAvailable
				+ ", reservations=" + reservations +  "]";
	}

	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
	}

	
	
	
}
