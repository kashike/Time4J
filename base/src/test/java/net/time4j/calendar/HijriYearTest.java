package net.time4j.calendar;

import net.time4j.PlainDate;
import net.time4j.format.expert.Iso8601Format;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@RunWith(Parameterized.class)
public class HijriYearTest {

    @Parameterized.Parameters(name= "{index}: iso={3}, west-islamic-civil({0}-{1}-{2})")
    public static Iterable<Object[]> data() {
        return Arrays.asList(
            new Object[][]{
                {1436, 12, 29, "2015-10-13"},
                {1436, 12, 30, "2015-10-14"},

                {1437, 1, 1, "2015-10-15"},
                {1437, 1, 2, "2015-10-16"},
                {1437, 1, 3, "2015-10-17"},
                {1437, 1, 4, "2015-10-18"},
                {1437, 1, 5, "2015-10-19"},
                {1437, 1, 6, "2015-10-20"},
                {1437, 1, 7, "2015-10-21"},
                {1437, 1, 8, "2015-10-22"},
                {1437, 1, 9, "2015-10-23"},
                {1437, 1, 10, "2015-10-24"},
                {1437, 1, 11, "2015-10-25"},
                {1437, 1, 12, "2015-10-26"},
                {1437, 1, 13, "2015-10-27"},
                {1437, 1, 14, "2015-10-28"},
                {1437, 1, 15, "2015-10-29"},
                {1437, 1, 16, "2015-10-30"},
                {1437, 1, 17, "2015-10-31"},
                {1437, 1, 18, "2015-11-01"},
                {1437, 1, 19, "2015-11-02"},
                {1437, 1, 20, "2015-11-03"},
                {1437, 1, 21, "2015-11-04"},
                {1437, 1, 22, "2015-11-05"},
                {1437, 1, 23, "2015-11-06"},
                {1437, 1, 24, "2015-11-07"},
                {1437, 1, 25, "2015-11-08"},
                {1437, 1, 26, "2015-11-09"},
                {1437, 1, 27, "2015-11-10"},
                {1437, 1, 28, "2015-11-11"},
                {1437, 1, 29, "2015-11-12"},
                {1437, 1, 30, "2015-11-13"},

                {1437, 2, 1, "2015-11-14"},
                {1437, 2, 2, "2015-11-15"},
                {1437, 2, 3, "2015-11-16"},
                {1437, 2, 4, "2015-11-17"},
                {1437, 2, 5, "2015-11-18"},
                {1437, 2, 6, "2015-11-19"},
                {1437, 2, 7, "2015-11-20"},
                {1437, 2, 8, "2015-11-21"},
                {1437, 2, 9, "2015-11-22"},
                {1437, 2, 10, "2015-11-23"},
                {1437, 2, 11, "2015-11-24"},
                {1437, 2, 12, "2015-11-25"},
                {1437, 2, 13, "2015-11-26"},
                {1437, 2, 14, "2015-11-27"},
                {1437, 2, 15, "2015-11-28"},
                {1437, 2, 16, "2015-11-29"},
                {1437, 2, 17, "2015-11-30"},
                {1437, 2, 18, "2015-12-01"},
                {1437, 2, 19, "2015-12-02"},
                {1437, 2, 20, "2015-12-03"},
                {1437, 2, 21, "2015-12-04"},
                {1437, 2, 22, "2015-12-05"},
                {1437, 2, 23, "2015-12-06"},
                {1437, 2, 24, "2015-12-07"},
                {1437, 2, 25, "2015-12-08"},
                {1437, 2, 26, "2015-12-09"},
                {1437, 2, 27, "2015-12-10"},
                {1437, 2, 28, "2015-12-11"},
                {1437, 2, 29, "2015-12-12"},

                {1437, 3, 1, "2015-12-13"},
                {1437, 3, 2, "2015-12-14"},
                {1437, 3, 3, "2015-12-15"},
                {1437, 3, 4, "2015-12-16"},
                {1437, 3, 5, "2015-12-17"},
                {1437, 3, 6, "2015-12-18"},
                {1437, 3, 7, "2015-12-19"},
                {1437, 3, 8, "2015-12-20"},
                {1437, 3, 9, "2015-12-21"},
                {1437, 3, 10, "2015-12-22"},
                {1437, 3, 11, "2015-12-23"},
                {1437, 3, 12, "2015-12-24"},
                {1437, 3, 13, "2015-12-25"},
                {1437, 3, 14, "2015-12-26"},
                {1437, 3, 15, "2015-12-27"},
                {1437, 3, 16, "2015-12-28"},
                {1437, 3, 17, "2015-12-29"},
                {1437, 3, 18, "2015-12-30"},
                {1437, 3, 19, "2015-12-31"},
                {1437, 3, 20, "2016-01-01"},
                {1437, 3, 21, "2016-01-02"},
                {1437, 3, 22, "2016-01-03"},
                {1437, 3, 23, "2016-01-04"},
                {1437, 3, 24, "2016-01-05"},
                {1437, 3, 25, "2016-01-06"},
                {1437, 3, 26, "2016-01-07"},
                {1437, 3, 27, "2016-01-08"},
                {1437, 3, 28, "2016-01-09"},
                {1437, 3, 29, "2016-01-10"},
                {1437, 3, 30, "2016-01-11"},

                {1437, 4, 1, "2016-01-12"},
                {1437, 4, 2, "2016-01-13"},
                {1437, 4, 3, "2016-01-14"},
                {1437, 4, 4, "2016-01-15"},
                {1437, 4, 5, "2016-01-16"},
                {1437, 4, 6, "2016-01-17"},
                {1437, 4, 7, "2016-01-18"},
                {1437, 4, 8, "2016-01-19"},
                {1437, 4, 9, "2016-01-20"},
                {1437, 4, 10, "2016-01-21"},
                {1437, 4, 11, "2016-01-22"},
                {1437, 4, 12, "2016-01-23"},
                {1437, 4, 13, "2016-01-24"},
                {1437, 4, 14, "2016-01-25"},
                {1437, 4, 15, "2016-01-26"},
                {1437, 4, 16, "2016-01-27"},
                {1437, 4, 17, "2016-01-28"},
                {1437, 4, 18, "2016-01-29"},
                {1437, 4, 19, "2016-01-30"},
                {1437, 4, 20, "2016-01-31"},
                {1437, 4, 21, "2016-02-01"},
                {1437, 4, 22, "2016-02-02"},
                {1437, 4, 23, "2016-02-03"},
                {1437, 4, 24, "2016-02-04"},
                {1437, 4, 25, "2016-02-05"},
                {1437, 4, 26, "2016-02-06"},
                {1437, 4, 27, "2016-02-07"},
                {1437, 4, 28, "2016-02-08"},
                {1437, 4, 29, "2016-02-09"},

                {1437, 5, 1, "2016-02-10"},
                {1437, 5, 2, "2016-02-11"},
                {1437, 5, 3, "2016-02-12"},
                {1437, 5, 4, "2016-02-13"},
                {1437, 5, 5, "2016-02-14"},
                {1437, 5, 6, "2016-02-15"},
                {1437, 5, 7, "2016-02-16"},
                {1437, 5, 8, "2016-02-17"},
                {1437, 5, 9, "2016-02-18"},
                {1437, 5, 10, "2016-02-19"},
                {1437, 5, 11, "2016-02-20"},
                {1437, 5, 12, "2016-02-21"},
                {1437, 5, 13, "2016-02-22"},
                {1437, 5, 14, "2016-02-23"},
                {1437, 5, 15, "2016-02-24"},
                {1437, 5, 16, "2016-02-25"},
                {1437, 5, 17, "2016-02-26"},
                {1437, 5, 18, "2016-02-27"},
                {1437, 5, 19, "2016-02-28"},
                {1437, 5, 20, "2016-02-29"},
                {1437, 5, 21, "2016-03-01"},
                {1437, 5, 22, "2016-03-02"},
                {1437, 5, 23, "2016-03-03"},
                {1437, 5, 24, "2016-03-04"},
                {1437, 5, 25, "2016-03-05"},
                {1437, 5, 26, "2016-03-06"},
                {1437, 5, 27, "2016-03-07"},
                {1437, 5, 28, "2016-03-08"},
                {1437, 5, 29, "2016-03-09"},
                {1437, 5, 30, "2016-03-10"},

                {1437, 6, 1, "2016-03-11"},
                {1437, 6, 2, "2016-03-12"},
                {1437, 6, 3, "2016-03-13"},
                {1437, 6, 4, "2016-03-14"},
                {1437, 6, 5, "2016-03-15"},
                {1437, 6, 6, "2016-03-16"},
                {1437, 6, 7, "2016-03-17"},
                {1437, 6, 8, "2016-03-18"},
                {1437, 6, 9, "2016-03-19"},
                {1437, 6, 10, "2016-03-20"},
                {1437, 6, 11, "2016-03-21"},
                {1437, 6, 12, "2016-03-22"},
                {1437, 6, 13, "2016-03-23"},
                {1437, 6, 14, "2016-03-24"},
                {1437, 6, 15, "2016-03-25"},
                {1437, 6, 16, "2016-03-26"},
                {1437, 6, 17, "2016-03-27"},
                {1437, 6, 18, "2016-03-28"},
                {1437, 6, 19, "2016-03-29"},
                {1437, 6, 20, "2016-03-30"},
                {1437, 6, 21, "2016-03-31"},
                {1437, 6, 22, "2016-04-01"},
                {1437, 6, 23, "2016-04-02"},
                {1437, 6, 24, "2016-04-03"},
                {1437, 6, 25, "2016-04-04"},
                {1437, 6, 26, "2016-04-05"},
                {1437, 6, 27, "2016-04-06"},
                {1437, 6, 28, "2016-04-07"},
                {1437, 6, 29, "2016-04-08"},

                {1437, 7, 1, "2016-04-09"},
                {1437, 7, 2, "2016-04-10"},
                {1437, 7, 3, "2016-04-11"},
                {1437, 7, 4, "2016-04-12"},
                {1437, 7, 5, "2016-04-13"},
                {1437, 7, 6, "2016-04-14"},
                {1437, 7, 7, "2016-04-15"},
                {1437, 7, 8, "2016-04-16"},
                {1437, 7, 9, "2016-04-17"},
                {1437, 7, 10, "2016-04-18"},
                {1437, 7, 11, "2016-04-19"},
                {1437, 7, 12, "2016-04-20"},
                {1437, 7, 13, "2016-04-21"},
                {1437, 7, 14, "2016-04-22"},
                {1437, 7, 15, "2016-04-23"},
                {1437, 7, 16, "2016-04-24"},
                {1437, 7, 17, "2016-04-25"},
                {1437, 7, 18, "2016-04-26"},
                {1437, 7, 19, "2016-04-27"},
                {1437, 7, 20, "2016-04-28"},
                {1437, 7, 21, "2016-04-29"},
                {1437, 7, 22, "2016-04-30"},
                {1437, 7, 23, "2016-05-01"},
                {1437, 7, 24, "2016-05-02"},
                {1437, 7, 25, "2016-05-03"},
                {1437, 7, 26, "2016-05-04"},
                {1437, 7, 27, "2016-05-05"},
                {1437, 7, 28, "2016-05-06"},
                {1437, 7, 29, "2016-05-07"},
                {1437, 7, 30, "2016-05-08"},

                {1437, 8, 1, "2016-05-09"},
                {1437, 8, 2, "2016-05-10"},
                {1437, 8, 3, "2016-05-11"},
                {1437, 8, 4, "2016-05-12"},
                {1437, 8, 5, "2016-05-13"},
                {1437, 8, 6, "2016-05-14"},
                {1437, 8, 7, "2016-05-15"},
                {1437, 8, 8, "2016-05-16"},
                {1437, 8, 9, "2016-05-17"},
                {1437, 8, 10, "2016-05-18"},
                {1437, 8, 11, "2016-05-19"},
                {1437, 8, 12, "2016-05-20"},
                {1437, 8, 13, "2016-05-21"},
                {1437, 8, 14, "2016-05-22"},
                {1437, 8, 15, "2016-05-23"},
                {1437, 8, 16, "2016-05-24"},
                {1437, 8, 17, "2016-05-25"},
                {1437, 8, 18, "2016-05-26"},
                {1437, 8, 19, "2016-05-27"},
                {1437, 8, 20, "2016-05-28"},
                {1437, 8, 21, "2016-05-29"},
                {1437, 8, 22, "2016-05-30"},
                {1437, 8, 23, "2016-05-31"},
                {1437, 8, 24, "2016-06-01"},
                {1437, 8, 25, "2016-06-02"},
                {1437, 8, 26, "2016-06-03"},
                {1437, 8, 27, "2016-06-04"},
                {1437, 8, 28, "2016-06-05"},
                {1437, 8, 29, "2016-06-06"},

                {1437, 9, 1, "2016-06-07"},
                {1437, 9, 2, "2016-06-08"},
                {1437, 9, 3, "2016-06-09"},
                {1437, 9, 4, "2016-06-10"},
                {1437, 9, 5, "2016-06-11"},
                {1437, 9, 6, "2016-06-12"},
                {1437, 9, 7, "2016-06-13"},
                {1437, 9, 8, "2016-06-14"},
                {1437, 9, 9, "2016-06-15"},
                {1437, 9, 10, "2016-06-16"},
                {1437, 9, 11, "2016-06-17"},
                {1437, 9, 12, "2016-06-18"},
                {1437, 9, 13, "2016-06-19"},
                {1437, 9, 14, "2016-06-20"},
                {1437, 9, 15, "2016-06-21"},
                {1437, 9, 16, "2016-06-22"},
                {1437, 9, 17, "2016-06-23"},
                {1437, 9, 18, "2016-06-24"},
                {1437, 9, 19, "2016-06-25"},
                {1437, 9, 20, "2016-06-26"},
                {1437, 9, 21, "2016-06-27"},
                {1437, 9, 22, "2016-06-28"},
                {1437, 9, 23, "2016-06-29"},
                {1437, 9, 24, "2016-06-30"},
                {1437, 9, 25, "2016-07-01"},
                {1437, 9, 26, "2016-07-02"},
                {1437, 9, 27, "2016-07-03"},
                {1437, 9, 28, "2016-07-04"},
                {1437, 9, 29, "2016-07-05"},
                {1437, 9, 30, "2016-07-06"},

                {1437, 10, 1, "2016-07-07"},
                {1437, 10, 2, "2016-07-08"},
                {1437, 10, 3, "2016-07-09"},
                {1437, 10, 4, "2016-07-10"},
                {1437, 10, 5, "2016-07-11"},
                {1437, 10, 6, "2016-07-12"},
                {1437, 10, 7, "2016-07-13"},
                {1437, 10, 8, "2016-07-14"},
                {1437, 10, 9, "2016-07-15"},
                {1437, 10, 10, "2016-07-16"},
                {1437, 10, 11, "2016-07-17"},
                {1437, 10, 12, "2016-07-18"},
                {1437, 10, 13, "2016-07-19"},
                {1437, 10, 14, "2016-07-20"},
                {1437, 10, 15, "2016-07-21"},
                {1437, 10, 16, "2016-07-22"},
                {1437, 10, 17, "2016-07-23"},
                {1437, 10, 18, "2016-07-24"},
                {1437, 10, 19, "2016-07-25"},
                {1437, 10, 20, "2016-07-26"},
                {1437, 10, 21, "2016-07-27"},
                {1437, 10, 22, "2016-07-28"},
                {1437, 10, 23, "2016-07-29"},
                {1437, 10, 24, "2016-07-30"},
                {1437, 10, 25, "2016-07-31"},
                {1437, 10, 26, "2016-08-01"},
                {1437, 10, 27, "2016-08-02"},
                {1437, 10, 28, "2016-08-03"},
                {1437, 10, 29, "2016-08-04"},

                {1437, 11, 1, "2016-08-05"},
                {1437, 11, 2, "2016-08-06"},
                {1437, 11, 3, "2016-08-07"},
                {1437, 11, 4, "2016-08-08"},
                {1437, 11, 5, "2016-08-09"},
                {1437, 11, 6, "2016-08-10"},
                {1437, 11, 7, "2016-08-11"},
                {1437, 11, 8, "2016-08-12"},
                {1437, 11, 9, "2016-08-13"},
                {1437, 11, 10, "2016-08-14"},
                {1437, 11, 11, "2016-08-15"},
                {1437, 11, 12, "2016-08-16"},
                {1437, 11, 13, "2016-08-17"},
                {1437, 11, 14, "2016-08-18"},
                {1437, 11, 15, "2016-08-19"},
                {1437, 11, 16, "2016-08-20"},
                {1437, 11, 17, "2016-08-21"},
                {1437, 11, 18, "2016-08-22"},
                {1437, 11, 19, "2016-08-23"},
                {1437, 11, 20, "2016-08-24"},
                {1437, 11, 21, "2016-08-25"},
                {1437, 11, 22, "2016-08-26"},
                {1437, 11, 23, "2016-08-27"},
                {1437, 11, 24, "2016-08-28"},
                {1437, 11, 25, "2016-08-29"},
                {1437, 11, 26, "2016-08-30"},
                {1437, 11, 27, "2016-08-31"},
                {1437, 11, 28, "2016-09-01"},
                {1437, 11, 29, "2016-09-02"},
                {1437, 11, 30, "2016-09-03"},

                {1437, 12, 1, "2016-09-04"},
                {1437, 12, 2, "2016-09-05"},
                {1437, 12, 3, "2016-09-06"},
                {1437, 12, 4, "2016-09-07"},
                {1437, 12, 5, "2016-09-08"},
                {1437, 12, 6, "2016-09-09"},
                {1437, 12, 7, "2016-09-10"},
                {1437, 12, 8, "2016-09-11"},
                {1437, 12, 9, "2016-09-12"},
                {1437, 12, 10, "2016-09-13"},
                {1437, 12, 11, "2016-09-14"},
                {1437, 12, 12, "2016-09-15"},
                {1437, 12, 13, "2016-09-16"},
                {1437, 12, 14, "2016-09-17"},
                {1437, 12, 15, "2016-09-18"},
                {1437, 12, 16, "2016-09-19"},
                {1437, 12, 17, "2016-09-20"},
                {1437, 12, 18, "2016-09-21"},
                {1437, 12, 19, "2016-09-22"},
                {1437, 12, 20, "2016-09-23"},
                {1437, 12, 21, "2016-09-24"},
                {1437, 12, 22, "2016-09-25"},
                {1437, 12, 23, "2016-09-26"},
                {1437, 12, 24, "2016-09-27"},
                {1437, 12, 25, "2016-09-28"},
                {1437, 12, 26, "2016-09-29"},
                {1437, 12, 27, "2016-09-30"},
                {1437, 12, 28, "2016-10-01"},
                {1437, 12, 29, "2016-10-02"},

                {1438, 1, 1, "2016-10-03"},
            }
        );
    }

    private HijriCalendar hijri;
    private PlainDate gregorian;

    public HijriYearTest(
        int year,
        int month,
        int dom,
        String iso
    ) throws ParseException {
        super();

        this.hijri = HijriCalendar.of(HijriAlgorithm.WEST_ISLAMIC_CIVIL, year, month, dom);
        this.gregorian = Iso8601Format.EXTENDED_CALENDAR_DATE.parse(iso);
    }

    @Test
    public void toGregorian() {
        assertThat(this.hijri.transform(PlainDate.class), is(this.gregorian));
    }

    @Test
    public void toHijri() {
        assertThat(this.gregorian.transform(HijriCalendar.class, HijriAlgorithm.WEST_ISLAMIC_CIVIL), is(this.hijri));
    }

}
