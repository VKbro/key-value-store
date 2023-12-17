package org.vijaykumark.keyvaluestore.service;

import org.vijaykumark.keyvaluestore.dto.KeyValueDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface KeyValueStoreServiceInterface {
    List<KeyValueDTO> getAllKeyValueData();

    List<KeyValueDTO> getKeyValueByUser(String user);

    List<KeyValueDTO> getKeyValueByUserAndKey(String user, String key);

    List<KeyValueDTO> getKeyValueDataBetween(String userID, LocalDateTime start, LocalDateTime end);

    KeyValueDTO updateKeyValueData(KeyValueDTO keyValueDTO);
    void addKeyValueData(KeyValueDTO keyValue);
}
