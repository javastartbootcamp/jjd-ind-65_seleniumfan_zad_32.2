package pl.javastart.jpaoptimalization.countrylanguage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryLanguageRepository extends JpaRepository<CountryLanguage, CountryLanguageKey> {
    @Query(value = "SELECT cl.Language, GROUP_CONCAT(c.Name ORDER BY c.Name ASC) AS Countries " +
            "FROM countrylanguage cl " +
            "INNER JOIN country c ON cl.CountryCode = c.Code " +
            "GROUP BY cl.Language " +
            "ORDER BY cl.Language ASC", nativeQuery = true)
    List<LanguageWithCountriesProjection> findLanguageWithCountries();
}
