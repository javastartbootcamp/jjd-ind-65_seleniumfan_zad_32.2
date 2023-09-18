package pl.javastart.jpaoptimalization.country;

public class CountryLanguageDto {
    private String countryName;
    private String language;
    private Double percentage;

    public CountryLanguageDto(String countryName, String language, Double percentage) {
        this.countryName = countryName;
        this.language = language;
        this.percentage = percentage;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
