package org.vijaykumark.keyvaluestore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;
import org.vijaykumark.keyvaluestore.entity.KeyValue;

import java.time.LocalDateTime;
import java.util.Map;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeyValueDTO {
    @NotBlank
    @Size(min = 4)
    private String userID;

    @NotNull
    private Map<@NotBlank @Size(min = 2) String, @NotBlank @Size(min = 2) String> keyValueData;

    public static KeyValueDTO toDTO(KeyValue keyValue) {
        return KeyValueDTO.builder()
            .userID(keyValue.getUserID())
            .keyValueData(keyValue.getKeyValueData())
            .build();
    }

    public KeyValue toEntity() {
        return KeyValue.builder()
            .userID(this.userID)
            .keyValueData(this.keyValueData)
            .createdAt(LocalDateTime.now())
            .modifiedAt(LocalDateTime.now())
            .build();
    }
}
