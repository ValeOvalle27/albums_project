package com.example.album_project;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "album")

public class Album {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long albumId;

	  @Column(name="title", unique = true)
	  private String title;

	  private String artist;
	  private String genre;

	  @Column(name="release_date")
	  private String releaseDate;
	}