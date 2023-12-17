package org.vijaykumark.keyvaluestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;

@Validated
@SpringBootApplication
public class KeyValueStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeyValueStoreApplication.class, args);
    }

}
