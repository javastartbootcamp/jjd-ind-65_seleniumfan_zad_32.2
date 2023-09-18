package pl.javastart.jpaoptimalization;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.javastart.jpaoptimalization.country.CountryCityPopulationDto;
import pl.javastart.jpaoptimalization.country.CountryService;
import pl.javastart.jpaoptimalization.countrylanguage.CountryLanguageService;
import pl.javastart.jpaoptimalization.countrylanguage.LanguageWithCountriesProjection;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private final CountryService countryService;
    private final CountryLanguageService countryLanguageService;

    public MainController(CountryService countryService,
                          CountryLanguageService countryLanguageService) {
        this.countryService = countryService;
        this.countryLanguageService = countryLanguageService;
    }

    @GetMapping("/najwieksze-miasta")
    public String countryWithBiggestCity(Model model) {
        List<CountryCityPopulationDto> countries = countryService.findCountryWithLargestCityPopulation();
        model.addAttribute("countries", countries);

        return "countryWithBiggestCity";
    }

    @GetMapping("/kraje-i-jezyki")
    public String countryWithLanguages(Model model) {
        Map<String, Map<String, Double>> countryWithLanguage = countryService.findCountryWithLanguage();

        model.addAttribute("countries", countryWithLanguage);

        return "countryWithLanguages";
    }

    @GetMapping("/jezyki-i-kraje")
    public String languagesWithCountries(Model model) {
        List<LanguageWithCountriesProjection> languages = countryLanguageService.findLanguageWithCountries();

        model.addAttribute("languages", languages);
        return "languagesWithCountries";
    }

}
