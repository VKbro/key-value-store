package org.vijaykumark.keyvaluestore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vijaykumark.keyvaluestore.dto.KeyValueDTO;
import org.vijaykumark.keyvaluestore.entity.KeyValue;
import org.vijaykumark.keyvaluestore.repository.KeyValueStoreRepositoryInterface;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KeyValueStoreService implements KeyValueStoreServiceInterface {

    private final KeyValueStoreRepositoryInterface keyValueStoreRepository;

    @Override
    public List<KeyValueDTO> getAllKeyValueData() {
        return keyValueStoreRepository.findAll().stream().map(KeyValueDTO::toDTO).toList();
    }

    @Override
    public List<KeyValueDTO> getKeyValueByUser(String user) {
        return keyValueStoreRepository
            .findKeyValueByUserID(user)
            .stream()
            .map(KeyValueDTO::toDTO)
            .toList();
    }

    @Override
    public List<KeyValueDTO> getKeyValueByUserAndKey(String user, String key) {
        return keyValueStoreRepository
            .findKeyValueByUserID(user)
            .stream()
            .filter(keyValue -> keyValue.getKeyValueData().containsKey(key))
            .map(KeyValueDTO::toDTO)
            .toList();
    }

    @Override
    public List<KeyValueDTO> getKeyValueDataBetween(String userID, LocalDateTime start, LocalDateTime end) {
        return keyValueStoreRepository
            .findAllByUserIDAndCreatedAtBetween(userID, start, end)
            .stream()
            .map(KeyValueDTO::toDTO)
            .toList();
    }

    @Override
    public KeyValueDTO updateKeyValueData(KeyValueDTO keyValueDTO) {
        Map.Entry<String, String> entry = keyValueDTO.getKeyValueData().entrySet().iterator().next();
        List<KeyValue> keyValueEntityList = keyValueStoreRepository.findKeyValueByUserID(keyValueDTO.getUserID());
        Optional<KeyValue> keyValueResult = keyValueEntityList
            .stream()
            .filter(
                keyValue ->
                    keyValue.getKeyValueData().containsKey(entry.getKey()) ||
                        keyValue.getKeyValueData().containsValue(entry.getValue()))
            .findFirst();

        if (keyValueResult.isPresent()) {
            keyValueResult.get().setKeyValueData(keyValueDTO.getKeyValueData());
            keyValueResult.get().setModifiedAt(LocalDateTime.now());
            keyValueStoreRepository.save(keyValueResult.get());
        } else {
            keyValueStoreRepository.save(keyValueDTO.toEntity());
        }

        return keyValueDTO;
    }

    @Override
    public void addKeyValueData(KeyValueDTO keyValueDTO) {
        keyValueStoreRepository.saveAndFlush(keyValueDTO.toEntity());
    }
}
