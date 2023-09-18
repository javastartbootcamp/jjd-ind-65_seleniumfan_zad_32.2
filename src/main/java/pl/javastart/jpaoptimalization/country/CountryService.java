package pl.javastart.jpaoptimalization.country;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public List<CountryCityPopulationDto> findCountryWithLargestCityPopulation() {
        return countryRepository.findCountryWithLargestCityPopulation();
    }

    public Map<String, Map<String, Double>> findCountryWithLanguage() {
        List<Object[]> queryResults = countryRepository.findCountryWithLanguage();
        Map<String, Map<String, Double>> countryLanguageMap = new TreeMap<>();

        for (Object[] result : queryResults) {
            String countryName = (String) result[0];
            String language = (String) result[1];
            Double percentage = (Double) result[2];

            countryLanguageMap.computeIfAbsent(countryName, k -> new HashMap<>());
            countryLanguageMap.get(countryName).put(language, percentage);

        }

        sortDesc(countryLanguageMap);
        return countryLanguageMap;
    }

    private static void sortDesc(Map<String, Map<String, Double>> countryLanguageMap) {
        countryLanguageMap.forEach((country, languages) -> {
            languages = languages.entrySet()
                    .stream()
                    .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            countryLanguageMap.put(country, languages);
        });
    }
}
