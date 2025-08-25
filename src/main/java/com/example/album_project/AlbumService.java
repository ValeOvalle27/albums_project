package com.example.album_project;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service

public class AlbumService {
	
		@Autowired
		AlbumRepository albumRepository;
		public List<Album> getAlbums(){
			return albumRepository.findAll();
			
		}
		
		public Optional<Album> getAlbum(Long id){
			return albumRepository.findById(id);
		}
		
		public Album saveOrUpdateAlbum(Album album) {
			return albumRepository.save(album);
		}
		
		public boolean deleteAlbum(Long albumId) {
		    if (albumRepository.existsById(albumId)) {
		        albumRepository.deleteById(albumId);
		        return true;
		    } else {
		        return false;
		    }
		}

}
		


