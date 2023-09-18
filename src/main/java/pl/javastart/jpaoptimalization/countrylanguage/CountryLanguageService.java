package pl.javastart.jpaoptimalization.countrylanguage;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryLanguageService {

    private final CountryLanguageRepository countryLanguageRepository;

    public CountryLanguageService(CountryLanguageRepository countryLanguageRepository) {
        this.countryLanguageRepository = countryLanguageRepository;
    }

    public List<LanguageWithCountriesProjection> findLanguageWithCountries() {
        return countryLanguageRepository.findLanguageWithCountries();
    }
}
