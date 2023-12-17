package org.vijaykumark.keyvaluestore.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vijaykumark.keyvaluestore.dto.KeyValueDTO;
import org.vijaykumark.keyvaluestore.service.KeyValueStoreServiceInterface;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping({"", "/"})
@RequiredArgsConstructor
public class KeyValueStoreController {

    private final KeyValueStoreServiceInterface keyValueStoreService;

    @GetMapping
    public ResponseEntity<List<KeyValueDTO>> getAll() {
        return ResponseEntity.ok(keyValueStoreService.getAllKeyValueData());
    }

    @GetMapping("{userID}")
    public ResponseEntity<List<KeyValueDTO>> getAllByUserID(
        @PathVariable @Size(min = 4) String userID
    ) {
        return ResponseEntity.ok(keyValueStoreService.getKeyValueByUser(userID));
    }

    @GetMapping("{user}/{key}")
    public ResponseEntity<List<KeyValueDTO>> getAllByUserAndKey(
        @PathVariable @Size(min = 4) String user,
        @PathVariable @Size(min = 2) String key
    ) {
        List<KeyValueDTO> keyValuePairs = keyValueStoreService.getKeyValueByUserAndKey(user, key);
        if (keyValuePairs.isEmpty())
            return new ResponseEntity<>(keyValuePairs, HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(keyValuePairs);
    }

    @GetMapping("filter")
    public ResponseEntity<List<KeyValueDTO>> getAllByTime(
        @RequestParam("userID") @Size(min = 4) String userID,
        @RequestParam("starting")
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime starting,
        @RequestParam("ending")
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime ending
    ) {
        return ResponseEntity.ok(keyValueStoreService.getKeyValueDataBetween(userID, starting, ending));
    }

    @PutMapping("update")
    public ResponseEntity<KeyValueDTO> update(@RequestBody KeyValueDTO keyValueDTO) {
        return ResponseEntity.ok(keyValueStoreService.updateKeyValueData(keyValueDTO));
    }

    @PostMapping
    public ResponseEntity<KeyValueDTO> insert(
        @Valid @RequestBody KeyValueDTO keyValueDTO
    ) {
        keyValueStoreService.addKeyValueData(keyValueDTO);
        return new ResponseEntity<>(keyValueDTO, HttpStatus.CREATED);
    }
}
