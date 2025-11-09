package ro.pub.cs.systems.eim.examplecolocviu1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondMainActivity extends AppCompatActivity {

    private Button sum, prod;
    private TextView text1, text2, text3, text4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.second_activity_main);

        sum = findViewById(R.id.sum);
        prod = findViewById(R.id.prod);

        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);

        int text1_copy = getIntent().getIntExtra("TEXT1_KEY", 0);
        int text2_copy = getIntent().getIntExtra("TEXT2_KEY", 0);
        int text3_copy = getIntent().getIntExtra("TEXT3_KEY", 0);
        int text4_copy = getIntent().getIntExtra("TEXT4_KEY", 0);


        text1.setText(String.valueOf(text1_copy));
        text2.setText(String.valueOf(text2_copy));
        text3.setText(String.valueOf(text3_copy));
        text4.setText(String.valueOf(text4_copy));

        Intent intentToParent = new Intent(); // copil -> parinte

        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                int res_sum = Integer.parseInt(text1.getText().toString()) +
                        Integer.parseInt(text2.getText().toString()) +
                        Integer.parseInt(text3.getText().toString()) +
                        Integer.parseInt(text4.getText().toString());

                intentToParent.putExtra("SUM", res_sum);
                setResult(RESULT_FIRST_USER, intentToParent);
                finish();
            }
        });

        prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                int res_prod = Integer.parseInt(text1.getText().toString()) *
                        Integer.parseInt(text2.getText().toString()) *
                        Integer.parseInt(text3.getText().toString()) *
                        Integer.parseInt(text4.getText().toString());

                intentToParent.putExtra("PROD", res_prod);
                setResult(RESULT_OK, intentToParent);
                finish();
            }
        });

    }
}