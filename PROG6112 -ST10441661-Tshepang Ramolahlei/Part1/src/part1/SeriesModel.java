package part1;

public class SeriesModel {
    public String SeriesId;
    public String SeriesName;
    public String SeriesAge;
    public String SeriesNumberOfEpisodes;

    public SeriesModel(String seriesId, String seriesName, String seriesAge, String seriesNumberOfEpisodes) {
        this.SeriesId = seriesId;
        this.SeriesName = seriesName;
        this.SeriesAge = seriesAge;
        this.SeriesNumberOfEpisodes = seriesNumberOfEpisodes;
    }

    public SeriesModel() {}

    @Override
    public String toString() {
        return String.format("SERIES ID: %s%nSERIES NAME: %s%nSERIES AGE RESTRICTION: %s%nNUMBER OF EPISODES: %s%n",
                SeriesId, SeriesName, SeriesAge, SeriesNumberOfEpisodes);
    }
}
