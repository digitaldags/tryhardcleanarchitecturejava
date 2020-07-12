package com.digitaldags.tryhardcleanarchitecture.entrypoint.songs.controller;

import com.digitaldags.tryhardcleanarchitecture.component.songs.model.dto.request.SongRequestDto;
import com.digitaldags.tryhardcleanarchitecture.component.songs.model.dto.response.SongsResponseDto;
import com.digitaldags.tryhardcleanarchitecture.entrypoint.songs.service.SongsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongsController {
    private final SongsService service;

    @GetMapping
    List<SongsResponseDto> getAllSongs() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    SongsResponseDto getSong(@PathVariable Long id) {
        return service.getSong(id);
    }

    @PostMapping
    public ResponseEntity<?> saveSong(@RequestBody SongRequestDto requestDto) {
        return ResponseEntity.ok(service.addSong(requestDto));
    }

    @PutMapping(value = "/{id}")
    SongsResponseDto updateSong(@PathVariable Long id, @RequestBody SongRequestDto dto) {
        return service.updateSong(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteSong(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteSong(id));
    }
}
