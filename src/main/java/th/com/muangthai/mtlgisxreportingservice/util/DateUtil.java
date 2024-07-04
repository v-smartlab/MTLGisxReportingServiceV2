package th.com.muangthai.mtlgisxreportingservice.util;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class DateUtil {
    private static final Locale LOCALE = new Locale("th", "TH");
    private static final Locale LOCALE_EN = new Locale("en", "EN");

    public static String toFormatString(Date date, String pattern) throws ParseException {
        String convertedDate = null;
        if (VsmUtil.isNotEmpty(date)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, LOCALE_EN);
            convertedDate = dateFormat.format(date);
        }
        return convertedDate;
    }
}
