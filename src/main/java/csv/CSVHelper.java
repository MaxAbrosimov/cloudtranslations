package csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CSVHelper {

    public static List<String> read(String fileName) throws IOException, CsvException {
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            List<String[]> r = reader.readAll();
            return r.stream().map(e -> e[0]).collect(Collectors.toList());
        }
    }

    public static void write(String fileName, List<String[]> result) throws IOException {
        try (ICSVWriter writer = new CSVWriterBuilder(
                new FileWriter(fileName))
                .withSeparator(',')
                .build()) {
            writer.writeAll(result);
        }
    }

}
