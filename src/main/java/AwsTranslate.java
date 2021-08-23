import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClient;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;

import java.util.List;
import java.util.stream.Collectors;

class AwsTranslate {

    private static final String AUTO = "auto";
    private static final String EN = "en";
    private static BasicAWSCredentials AWS_CREDENTIALS = new BasicAWSCredentials(SamplesTranslation.AWS_ACCESS_KEY, SamplesTranslation.AWS_SECRET_KEY);

    private static AmazonTranslate AWS_TRANSLATE = AmazonTranslateClient.builder()
            .withCredentials(new AWSStaticCredentialsProvider(AWS_CREDENTIALS))
            .withRegion(SamplesTranslation.REGION)
            .build();

    static List<String[]> awsTranslate(List<String> samples) {
        return samples.stream().map(s -> {
            TranslateTextRequest engRequest = new TranslateTextRequest()
                    .withText(s)
                    .withSourceLanguageCode(AUTO)
                    .withTargetLanguageCode(EN);
            TranslateTextResult translateTextResultEng = AWS_TRANSLATE.translateText(engRequest);

            TranslateTextRequest resultRequest = new TranslateTextRequest()
                    .withText(translateTextResultEng.getTranslatedText())
                    .withSourceLanguageCode(EN)
                    .withTargetLanguageCode(translateTextResultEng.getSourceLanguageCode());
            return new String[]{AWS_TRANSLATE.translateText(resultRequest).getTranslatedText()};
        }).collect(Collectors.toList());
    }


}
