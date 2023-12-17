package org.vijaykumark.keyvaluestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vijaykumark.keyvaluestore.entity.KeyValue;

import java.time.LocalDateTime;
import java.util.List;

public interface KeyValueStoreRepositoryInterface extends JpaRepository<KeyValue, Long> {

    List<KeyValue> findKeyValueByUserID(String userID);

    List<KeyValue> findAllByUserIDAndCreatedAtBetween(String userID, LocalDateTime start, LocalDateTime end);
}
