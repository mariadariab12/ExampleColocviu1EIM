package ro.pub.cs.systems.eim.examplecolocviu1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ColocviuMainActivity extends AppCompatActivity {

    private Button secondActivity;
    private TextView text1, text2, text3, text4;
    private static final int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
    private boolean serviceStarted = false;
    private android.content.BroadcastReceiver messageReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.colocviu_activity_main);

        secondActivity = findViewById(R.id.secondActivity);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);

        secondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                try {
                    Intent intent = new Intent(ColocviuMainActivity.this,
                            SecondMainActivity.class);

                    int nr_text1 = Integer.parseInt(text1.getText().toString());
                    int nr_text2 = Integer.parseInt(text2.getText().toString());
                    int nr_text3 = Integer.parseInt(text3.getText().toString());
                    int nr_text4 = Integer.parseInt(text4.getText().toString());

                    intent.putExtra("TEXT1_KEY", nr_text1);
                    intent.putExtra("TEXT2_KEY", nr_text2);
                    intent.putExtra("TEXT3_KEY", nr_text3);
                    intent.putExtra("TEXT4_KEY", nr_text4);

                    startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Please enter valid numbers", Toast.LENGTH_SHORT).show();
                }
            }
        });

        messageReceiver = new android.content.BroadcastReceiver() {
            @Override
            public void onReceive(android.content.Context context, Intent intent) {
                if (intent == null || intent.getAction() == null) return;

                String action = intent.getAction();
                String timestamp = intent.getStringExtra("timestamp");
                int counter = intent.getIntExtra("counter", 0);

                // doar ca să vezi de unde a venit
                String msg = action + "\n" +
                        "time: " + timestamp + "\n" +
                        "counter: " + counter;

                android.util.Log.d("Test02", msg);

                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            }
        };

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putCharSequence("KEY_TEXT1", text1.getText());
        savedInstanceState.putCharSequence("KEY_TEXT2", text2.getText());
        savedInstanceState.putCharSequence("KEY_TEXT3", text3.getText());
        savedInstanceState.putCharSequence("KEY_TEXT4", text4.getText());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        CharSequence text1_saved = savedInstanceState.getCharSequence("KEY_TEXT1");
        CharSequence text2_saved = savedInstanceState.getCharSequence("KEY_TEXT2");
        CharSequence text3_saved = savedInstanceState.getCharSequence("KEY_TEXT3");
        CharSequence text4_saved = savedInstanceState.getCharSequence("KEY_TEXT4");

        text1.setText(text1_saved);
        text2.setText(text2_saved);
        text3.setText(text3_saved);
        text4.setText(text4_saved);

    }

    @Override
    @Deprecated
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            int res_sum = data.getIntExtra("SUM", -1);
            int res_prod = data.getIntExtra("PROD", -1);

            if (resultCode == RESULT_FIRST_USER) {
                Toast.makeText(this, "Sum: " + res_sum, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Prod: " + res_prod, Toast.LENGTH_SHORT).show();
            }

        }
    }

}