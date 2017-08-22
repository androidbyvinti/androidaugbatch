package com.bmpl.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Switch bluetoothSwitch;
    Button visibleButton, pairedDeviceButton;
    ListView listView;
    //BluetoothAdapter class--> internal bluetooth driver info-->

    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        bluetoothSwitch = (Switch)findViewById(R.id.bluetoothSwitch);
        visibleButton = (Button)findViewById(R.id.visibleButton);
        pairedDeviceButton = (Button)findViewById(R.id.pairedDevices);
        listView = (ListView)findViewById(R.id.listView);

        if(bluetoothAdapter.isEnabled())
        {
            bluetoothSwitch.setChecked(true);
        }
        else {
            bluetoothSwitch.setChecked(false);
        }

        bluetoothSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(bluetoothAdapter== null)
                {
                    Toast.makeText(MainActivity.this, "Bluetooth Driver is not available", Toast.LENGTH_LONG).show();
                } else if(!bluetoothAdapter.isEnabled())
                {
                    //implicit intent
                    /*Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivity(intent);*/
                    bluetoothAdapter.enable();
                } else
                    {
                    bluetoothAdapter.disable();
                }
            }
        });
        visibleButton.setOnClickListener(this);
        pairedDeviceButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.visibleButton:
                //bluetoothAdapter.getState();
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 1000);
                startActivity(intent);
                break;

            case R.id.pairedDevices:

                break;
        }
    }
}