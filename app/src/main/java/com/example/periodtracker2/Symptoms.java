package com.example.periodtracker2;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Symptoms extends AppCompatActivity
{
    private Spinner symptomSpinner;
    private TextView selfCareTipText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);
        symptomSpinner = findViewById(R.id.symptom_spinner);
        selfCareTipText= findViewById(R.id.self_care_tip_label);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Symptoms, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        symptomSpinner.setAdapter(adapter);

        symptomSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String symptom = parent.getItemAtPosition(position).toString();
                String selfCareTip = getSelfCareTip(symptom);
                selfCareTipText.setText(selfCareTip);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // do nothing
            }
        });
    }

    private String getSelfCareTip(String symptom)
    {
        switch (symptom) {
            case "Cramps":
                return ("\u2022 " + "Apply Heat to the lower Abdomen area to sooth the cramps and  get instant relief.\n" +
                        "\u2022 "+"Drink Water: helps reduce bloating.\n" +
                        "\u2022 "+"Try meditation and deep breathing: oxygen content in the body  as well as meditation can help reduce stress and the pain");
            case "Joint Pain":
                return ("\u2022 "+"Gentle Exercise: Exercises such as walking and yoga increases blood flow and reduces inflammation.\n" +
                        "\u2022 "+"Maintain a healthy diet: food rich in nutrients like fruits and vegetables help immensely in reducing inflammation.\n" +
                        "\u2022 "+"Apply heat: gently apply heat to the affected joint");
            case "Diarrhoea":
                return ("\u2022 "+"Stay Hydrated:Diarrhoea causes dehydration therefore, keeping yourself hydrated is essential.\n" +
                        "\u2022 "+"Avoid certain foods: foods such as caffeine, alcohol and dairy products increase diarrhoea.\n" +
                        "\u2022 "+"Eat Bland Food: Eat easily digestible food such as bananas, rice, bread etc.");
            case "Constipation":
                return ("\u2022 "+"Stay Hydrated:Water, tea and other liquids help soften stool.\n" +
                        "\u2022 "+"Eat fiber-rich foods: Fibre rich foods like fruits, vegetables and whole grains promote regular bowel movement.\n" +
                        "\u2022 "+"Gentle exercise: Walking and other light exercises could help ease bowel movement");
            case "Fatigue":
                return ("\u2022 "+"Get Rest: Sleep for about 7-8 hours at night and take occasional naps during the day if neccessary\n" +
                        "\u2022 "+"Stay Hydrated: Dehydration can cause fatigue hence remaining hydrated is essential.\n" +
                        "\u2022 "+"Eat Healthy:Fibre rich foods such as vegetables, fruits and whole foods combat fatigue.");
            default:
                return "";
        }
    }
}