package csv;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVDif {

    public static void main(String[] args) throws IOException, CsvException {
        List<String> google = CSVHelper.read("google_result.csv");
        List<String> aws = CSVHelper.read("aws_translate.csv");

        List<String[]> difs = new ArrayList<>();
        for (int i = 0; i < google.size() - 1; i++) {
            if (!google.get(i).equalsIgnoreCase(aws.get(i))) {
                difs.add(new String[]{google.get(i), aws.get(i)});
            }
        }

        CSVHelper.write("difs.csv", difs);
    }

}
