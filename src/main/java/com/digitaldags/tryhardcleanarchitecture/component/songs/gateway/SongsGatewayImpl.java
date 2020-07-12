package com.digitaldags.tryhardcleanarchitecture.component.songs.gateway;

import com.digitaldags.tryhardcleanarchitecture.component.songs.mapper.AddSongsMapper;
import com.digitaldags.tryhardcleanarchitecture.component.songs.mapper.SongsMapper;
import com.digitaldags.tryhardcleanarchitecture.component.songs.model.domain.Songs;
import com.digitaldags.tryhardcleanarchitecture.component.songs.model.entity.SongsEntity;
import com.digitaldags.tryhardcleanarchitecture.component.songs.repository.SongsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongsGatewayImpl implements SongsGateway {
    private final SongsRepository repository;
    private final SongsMapper mapper;
    private final AddSongsMapper addSongsMapper;

    @Override
    public List<Songs> getAllSongs() {
        return mapper.toDomainList(repository.findAll());
    }

    @Override
    public Songs getSong(Long id) {
        SongsEntity entity = repository.getOne(id);
        return mapper.toDomain(entity);
    }

    @Override
    public Songs saveSong(Songs song) {
        SongsEntity entity = repository.save(addSongsMapper.toEntity(song));
        return mapper.toDomain(entity);
    }

    @Override
    public Songs update(Long id, Songs song) {
        repository.update(id, song.getTitle(), song.getArtist(), song.getAlbum());
        return mapper.toDomain(repository.getOne(id));
    }

    @Override
    public void delete(Long id) {
        repository.delete(repository.getOne(id));
    }
}
