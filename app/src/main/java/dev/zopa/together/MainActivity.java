package dev.zopa.together;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String TOGETHER = "Вы вместе примерно - ";
    private static final String DAYS = " дн.";
    private static final String HOURS = " ч.";
    private static final String MIN = " мин.";
    private static final String MONTHS = " мес.";
    private static final String SEC = " сек.";
    private static final String WEEKS = " нед.";
    private static final String YEAR = " лет.";
    private static final String JULIA = " раз Юля перднет";
    private static final String EARTHQUAKES = " землетрясений";
    private static final String SIGHT = " тыс вздоха";
    private static final String HEARDBEANS = " тыс удара сердца";
    private static final String STEPS = " тыс шагов";
    private static final String LAUGHT = " раз смеялся";
    private static final String THISIS = "А это примерно:";
    private static final String THISTIME = " За это время:";
    private static final int DAY_OF_MONTH = 30;
    private static final int DAY_OF_WEEK = 7;
    private static final int MONTH_OF_YEAR = 12;

    int DIALOG_DATE = 1;
    int myYear = 2015;
    int myMonth = 7;
    int myDay = 5;

    TextView countDayView;
    TextView second;
    TextView min;
    TextView hours;
    TextView week;
    TextView mounths;
    TextView years;
    TextView perd;
    TextView earthquake;
    TextView sigh;
    TextView heardBeans;
    TextView laught;
    TextView steps;
    TextView thisis;
    TextView thisTime;

    SimpleDateFormat format;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.photo_settings:
                Toast.makeText(getApplicationContext(),
                        "фон изменен", Toast.LENGTH_LONG).show();
                return true;

            case R.id.color_settings:
                Toast.makeText(getApplicationContext(),
                        "Цвет текста изменен", Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        min = (TextView) findViewById(R.id.min);
        week = (TextView) findViewById(R.id.weeks);
        perd = (TextView) findViewById(R.id.heartBeats);
        sigh = (TextView) findViewById(R.id.sigh);
        steps = (TextView) findViewById(R.id.steps);
        years = (TextView) findViewById(R.id.years);
        hours = (TextView) findViewById(R.id.hour);
        thisis = (TextView) findViewById(R.id.thisIs);
        laught = (TextView) findViewById(R.id.laugh);
        second = (TextView) findViewById(R.id.second);
        mounths = (TextView) findViewById(R.id.mounth);
        thisTime = (TextView) findViewById(R.id.thisTime);
        heardBeans = (TextView) findViewById(R.id.perd);
        earthquake = (TextView) findViewById(R.id.earthquake);


        countDayView = (TextView) findViewById(R.id.countDayView);

        format = new SimpleDateFormat("dd/MM/yyyy");
    }


    // todo dobafit  fon
    public void onclick(View view) {
        showDialog(DIALOG_DATE);
    }


    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE) {
            DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
            return tpd;
        }
        return super.onCreateDialog(id);
    }
//todo save yer month day in SharePref.
    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear + 1;
            myDay = dayOfMonth;

            long timeUp = 0;


            try {
                timeUp = format.parse(myDay + "/" + myMonth + "/" + myYear).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            long diff = (System.currentTimeMillis()) - timeUp;

            long diffSec = TimeUnit.MILLISECONDS.toSeconds(diff);
            long diffMin = TimeUnit.MILLISECONDS.toMinutes(diff);
            long diffHour = TimeUnit.MILLISECONDS.toHours(diff);
            long diffDays = TimeUnit.MILLISECONDS.toDays(diff);
            long diffWeek = diffDays / DAY_OF_WEEK;
            long diffMounth = diffDays / DAY_OF_MONTH;
            long diffYear = diffMounth / MONTH_OF_YEAR;

            if (diff < 0) {
                Toast.makeText(MainActivity.this, "Нельзя смотреть в будующее!!!", Toast.LENGTH_SHORT).show();
            } else {

                thisTime.setText(THISTIME);
                thisis.setText(THISIS);

                if (diffDays > 0) {
                    countDayView.setText(TOGETHER + diffDays + DAYS);
                    perd.setText(diffDays * 15 + JULIA);
                    earthquake.setText(diffDays * 5 + EARTHQUAKES);
                    laught.setText(diffDays * 18 + LAUGHT);
                    heardBeans.setText(diffDays * 104 + HEARDBEANS);
                    steps.setText(diffDays * 8 + STEPS);
                    sigh.setText(diffDays * 12 + SIGHT);

                }
                if (diffSec > 0) {
                    second.setText(diffSec + SEC);
                }
                if (diffMin > 0) {
                    min.setText(diffMin + MIN);
                }
                if (diffHour > 0) {
                    hours.setText(diffHour + HOURS);
                }
                if (diffWeek > 0) {
                    week.setText(diffWeek + WEEKS);
                }
                if (diffMounth > 0) {
                    mounths.setText(diffMounth + MONTHS);
                }
                if (diffYear > 0) {
                    years.setText(diffYear + YEAR);
                }
            }
        }
    };

}
