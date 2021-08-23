import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translation;
import com.google.cloud.translate.testing.RemoteTranslateHelper;

import java.util.List;
import java.util.stream.Collectors;

class GoogleTranslate {

    private static Translate GOOGLE_TRANSLATE = RemoteTranslateHelper.create(SamplesTranslation.GOOGLE_KEY).getOptions().getService();;
    private static final String EN = "en";

    static List<String[]> googleTranslate(List<String> samples) {
        return samples.stream().map(s -> {
            Translation translation = GOOGLE_TRANSLATE.translate(s);
            String resultString;
            if (translation.getSourceLanguage().equals(EN)) {
                resultString = translation.getTranslatedText();
            } else {
                Translation resultTranslate = GOOGLE_TRANSLATE.translate(
                        translation.getTranslatedText(),
                        Translate.TranslateOption.sourceLanguage(EN),
                        Translate.TranslateOption.targetLanguage(translation.getSourceLanguage())
                );
                resultString = resultTranslate.getTranslatedText();
            }
            return new String[]{resultString};
        }).collect(Collectors.toList());
    }

}
