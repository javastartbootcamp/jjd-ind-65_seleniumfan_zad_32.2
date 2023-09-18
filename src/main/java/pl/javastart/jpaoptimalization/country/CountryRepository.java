package pl.javastart.jpaoptimalization.country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, String> {
    @Query("SELECT NEW pl.javastart.jpaoptimalization.country.CountryCityPopulationDto(co.name, ci.name, ci.population)" +
            "FROM Country co " +
            "LEFT JOIN City ci ON co.code = ci.country.code " +
            "WHERE (ci.country.code, ci.population) IN " +
            "  (SELECT c.country.code, MAX(c.population) FROM City c GROUP BY c.country.code) " +
            "OR ci.country IS NULL " +
            "ORDER BY co.name ASC")
    List<CountryCityPopulationDto> findCountryWithLargestCityPopulation();

    @Query("SELECT c.name, cl.language, cl.percentage FROM CountryLanguage cl JOIN Country c ON cl.countryCode = c.code " +
            "ORDER BY c.name ASC")
    List<Object[]> findCountryWithLanguage();
}
