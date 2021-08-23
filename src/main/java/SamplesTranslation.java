import com.amazonaws.regions.Regions;
import com.opencsv.exceptions.CsvException;
import csv.CSVHelper;

import java.io.IOException;
import java.util.List;

public class SamplesTranslation {

    static final String AWS_ACCESS_KEY = "AWS_ACCESS_KEY";
    static final String AWS_SECRET_KEY = "AWS_SECRET_KEY";
    static final Regions REGION = Regions.US_EAST_1;
    static final String GOOGLE_KEY = "GOOGLE_KEY";

    public static void main(String[] args) throws IOException, CsvException {
        String lang = "RU";

        List<String> samples = CSVHelper.read(String.format("suggestions/from/sugg%s.csv", lang));

        List<String[]> awsResult = AwsTranslate.awsTranslate(samples);
        List<String[]> googleResult = GoogleTranslate.googleTranslate(samples);

        CSVHelper.write(String.format("suggestions/to/sugg%s_aws.csv", lang), awsResult);
        CSVHelper.write(String.format("suggestions/to/sugg%s_google.csv", lang), googleResult);
    }
}
