package com.tts.WeatherApp.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.tts.WeatherApp.domain.ZipCode;

public interface ZipCodeRepository extends CrudRepository<ZipCode, Long> {

    List<ZipCode> findByZipCode(String zipCode);

}




