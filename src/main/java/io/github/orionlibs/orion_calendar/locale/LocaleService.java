package io.github.orionlibs.orion_calendar.locale;

import io.github.orionlibs.orion_calendar.locale.data_access.LocaleModel;
import io.github.orionlibs.orion_calendar.locale.data_access.LocalesModel;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;

public class LocaleService
{
    private static LocalesModel locales;
    static
    {
        locales = LocalesModel.of(new ArrayList<>(getUniqueLocales()));
    }


    private static Collection<LocaleModel> getUniqueLocales()
    {
        return getFilteredLocaleStream().map(LocaleAndTag::new)
                        .collect(Collectors.toMap(LocaleAndTag::getTag, LocaleService::createLocale, ((studioLocale, duplicateStudioLocale) -> studioLocale)))
                        .values();
    }


    public static Stream<Locale> getFilteredLocaleStream()
    {
        return Arrays.stream(Locale.getAvailableLocales())
                        //skip the bizarre ones
                        .filter(locale -> !"".equals(locale.getLanguage()) && !"".equals(locale.getCountry()) && locale.getCountry()
                                        .matches("^[a-zA-Z]{2}$"));
    }


    private static LocaleModel createLocale(LocaleAndTag localeAndTag)
    {
        return LocaleModel.builder()
                        .underscoreTag(localeAndTag.getTag())
                        .displayLanguage(localeAndTag.getLocale().getDisplayLanguage(Locale.UK))
                        .displayCountry(localeAndTag.getLocale().getDisplayCountry(Locale.UK))
                        .build();
    }


    @Getter
    private static class LocaleAndTag
    {
        private final Locale locale;
        private final String tag;


        public LocaleAndTag(final Locale locale)
        {
            this.locale = locale;

            if("".equals(locale.getVariant()))
            {
                tag = MessageFormat.format("{0}_{1}", locale.getLanguage(), locale.getCountry());
            }
            else
            {
                tag = MessageFormat.format("{0}_{1}_{2}", locale.getLanguage(), locale.getCountry(), locale.getVariant());
            }

        }
    }


    public static LocalesModel getLocales()
    {
        return locales;
    }
}