package pt.ulisboa.tecnico.virtualthermostat_sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import pt.ulisboa.tecnico.virtualthermostat.VirtualThermostat;

public class MainActivity extends AppCompatActivity {

    private VirtualThermostat mVirtualThermostat;
    private EditText currentTemperature, targetTemperature;
    public static final int COLOR_HEAT = Color.parseColor("#F57F17");
    public static final int COLOR_COLD = Color.parseColor("#ff33b5e5");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mVirtualThermostat = (VirtualThermostat) findViewById(R.id.virtualThermostat);
        currentTemperature = (EditText) findViewById(R.id.currentTemperature);
        targetTemperature = (EditText) findViewById(R.id.targetTemperature);

        currentTemperature.setText("25");
        targetTemperature.setText("20");
        mVirtualThermostat.setCurrentTemperature("25");
        mVirtualThermostat.setProgress(20);

        setEcoTemperatureRange();

        mVirtualThermostat.setOnVirtualThermostatChangeListener(new VirtualThermostat.OnVirtualThermostatChangeListener() {

            @Override
            public void onStopTrackingTouch(VirtualThermostat seekArc) {
//                Toast.makeText(MainActivity.this, "Selected:"+ seekArc.getProgress(), Toast.LENGTH_SHORT).show();
                targetTemperature.setText(seekArc.getProgress()+"");
            }

            @Override
            public void onStartTrackingTouch(VirtualThermostat seekArc) {
            }

            @Override
            public void onProgressChanged(VirtualThermostat seekArc, int progress,
                                          boolean fromUser) {
            }
        });

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!currentTemperature.getText().toString().isEmpty()) {
                    mVirtualThermostat.setCurrentTemperature(currentTemperature.getText().toString());
                    int temperature = Integer.parseInt(currentTemperature.getText().toString());
                    int color = (temperature < 18)? COLOR_COLD : COLOR_HEAT;
                    mVirtualThermostat.setBackgroundColor(color);
                }
                if(!targetTemperature.getText().toString().isEmpty())
                    mVirtualThermostat.setProgress(Integer.parseInt(targetTemperature.getText().toString()));
            }
        });

    }

    private void setEcoTemperatureRange(){
        mVirtualThermostat.setLeafLimit(21, 26);
    }

}
