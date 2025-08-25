package com.example.album_project;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping(path="api/v1/albums")
public class AlbumController {
	

		@Autowired
		private AlbumService albumService;
		
		// Get Albums
		
		@GetMapping
		public List<Album> getAll(){
			return albumService.getAlbums();
			
		}
		
		// Get Album by id
		
		@GetMapping("/{albumId}")
		public Optional<Album> getById(@PathVariable("albumId") Long albumId){
			return albumService.getAlbum(albumId);
				
		}
		

		// Create Album
		
		@PostMapping
		public Album createAlbum(@RequestBody Album album) {
		System.out.println(">>> Recibido en el controller: " + album);
		      return albumService.saveOrUpdateAlbum(album);
		}
		
		// Update Album
		
		@PutMapping("/{albumId}")
		public ResponseEntity<Album> updateAlbum(
		        @PathVariable("albumId") Long albumId,
		        @RequestBody Album albumDetails) {

		    return albumService.getAlbum(albumId)
		            .map(existingAlbum -> {
		                // Update the files
		                existingAlbum.setTitle(albumDetails.getTitle());
		                existingAlbum.setArtist(albumDetails.getArtist());
		                existingAlbum.setGenre(albumDetails.getGenre());
		                existingAlbum.setReleaseDate(albumDetails.getReleaseDate());

		                Album updatedAlbum = albumService.saveOrUpdateAlbum(existingAlbum);

		                return ResponseEntity.ok(updatedAlbum); // Return 200 with the object updated
		            })
		            .orElseGet(() -> ResponseEntity.notFound().build()); // if no exists, return 404
		}

		
		
		
		// Delete Album
		
		@DeleteMapping("/{albumId}")
		public ResponseEntity<String> deleteAlbum(@PathVariable Long albumId) {
		    boolean deleted = albumService.deleteAlbum(albumId);

		    if (deleted) {
		        return ResponseEntity.ok("Album was delete correctly");
		    } else {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND)
		                             .body("The album with ID " + albumId + " no exists ");
		    }
		}

		
		

	}




