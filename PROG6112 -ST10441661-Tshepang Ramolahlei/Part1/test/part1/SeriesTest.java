package part1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.*;

public class SeriesTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    private Series series;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent)); // Capture console output
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);  // Reset console output
        System.setIn(originalIn);    // Reset input stream
    }

    @Test
    public void testCaptureSeries() {
        String input = String.join("\n",
                "S001",        // ID
                "Friends",     // Name
                "13",          // Age
                "236"          // Episodes
        );
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        series = new Series();
        series.CaptureSeries();

        List<SeriesModel> list = getSeriesList(series);
        assertEquals(1, list.size());

        SeriesModel s = list.get(0);
        assertEquals("S001", s.SeriesId);
        assertEquals("Friends", s.SeriesName);
        assertEquals("13", s.SeriesAge);
        assertEquals("236", s.SeriesNumberOfEpisodes);
    }

    @Test
public void testSearchSeries() {
    String input = "S100\n";
    System.setIn(new ByteArrayInputStream(input.getBytes())); // Set before Series constructor

    series = new Series();  // Now scanner reads from new System.in
    getSeriesList(series).add(new SeriesModel("S100", "The Office", "12", "201"));

    series.SearchSeries();

    String output = outContent.toString();
    assertTrue(output.contains("The Office"));
    assertTrue(output.contains("S100"));
}


   @Test
public void testUpdateSeries() {
    String input = String.join("\n",
            "S200",            // ID to update
            "Sherlock Holmes",  // New Name
            "17",              // New Age
            "15"               // New Episodes
    );

    System.setIn(new ByteArrayInputStream(input.getBytes()));

    series = new Series();  // Create after setting System.in
    getSeriesList(series).add(new SeriesModel("S200", "Sherlock", "16", "13"));

    series.UpdateSeries();

    SeriesModel updated = getSeriesList(series).get(0);
    assertEquals("Sherlock Holmes", updated.SeriesName);
    assertEquals("17", updated.SeriesAge);
    assertEquals("15", updated.SeriesNumberOfEpisodes);
}


   @Test
public void testDeleteSeries() {
    String input = String.join("\n",
            "S300",  // ID to delete
            "y"      // Confirmation
    );
    System.setIn(new ByteArrayInputStream(input.getBytes())); // <-- Set BEFORE creating Series

    series = new Series();  // Create after setting input stream
    getSeriesList(series).add(new SeriesModel("S300", "Stranger Things", "14", "34"));

    series.DeleteSeries();

    assertTrue(getSeriesList(series).isEmpty());
}


    @Test
    public void testSeriesReport() {
        series = new Series();
        getSeriesList(series).add(new SeriesModel("S400", "The Crown", "18", "40"));

        System.setIn(new ByteArrayInputStream("".getBytes()));
        series.SeriesReport();

        String output = outContent.toString();
        assertTrue(output.contains("The Crown"));
        assertTrue(output.contains("SERIES REPORT"));
    }

    @Test
    public void testExitSeriesApplication() {
        series = new Series();
        series.ExitSeriesApplication();

        String output = outContent.toString();
        assertTrue(output.contains("Thank you for using TV Series Manager"));
    }

    // === Reflection Helpers ===

    @SuppressWarnings("unchecked")
    private List<SeriesModel> getSeriesList(Series seriesInstance) {
        try {
            java.lang.reflect.Field field = Series.class.getDeclaredField("seriesList");
            field.setAccessible(true);
            return (List<SeriesModel>) field.get(seriesInstance);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
