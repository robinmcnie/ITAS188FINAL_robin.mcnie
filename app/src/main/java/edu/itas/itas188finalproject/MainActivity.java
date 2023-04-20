package edu.itas.itas188finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Spinner toneSpinner;
    private EditText inputEditText;
    private Button submitButton;
    private TextView rephrasedTextView;
    private Button historyButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEditText = findViewById(R.id.editText);
        submitButton = findViewById(R.id.submit_button);
        rephrasedTextView = findViewById(R.id.rephrased_text);
        historyButton = findViewById(R.id.history_button);

        toneSpinner = findViewById(R.id.spinner_tone);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tones, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toneSpinner.setAdapter(adapter);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = inputEditText.getText().toString().trim();
                String selectedTone = toneSpinner.getSelectedItem().toString();

                    if (!userInput.isEmpty()) {
                        callOpenAIApi(userInput, selectedTone);
                    } else {
                        Toast.makeText(MainActivity.this, "Please enter some text", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        toneSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // You can perform actions based on the selected tone here if needed
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where nothing is selected if needed
            }


        });
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });


    }
    private void callOpenAIApi(String userInput, String selectedTone) {
        OpenAIApiService apiService = RetrofitClient.getClient();

        String prompt = "Rephrase the following with a " + selectedTone + " tone: " + userInput;

        int maxTokens = 50;
        OpenAIPrompt openAIPrompt = new OpenAIPrompt(prompt, 50);

        Call<OpenAIResponse> call = apiService.getRewrittenText(openAIPrompt);
        call.enqueue(new Callback<OpenAIResponse>() {
            @Override
            public void onResponse(Call<OpenAIResponse> call, Response<OpenAIResponse> response) {
                if (response.isSuccessful()) {
                    OpenAIResponse openAIResponse = response.body();
                    String rephrasedText = openAIResponse.getChoices().get(0).getText().trim();
                    rephrasedTextView.setText(rephrasedText);

                    // Save the user input and rephrased text to Firebase
                    saveDataToFirebase(userInput, rephrasedText, selectedTone);
                } else {
                    Toast.makeText(MainActivity.this, "Error: Unable to generate rephrased text", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OpenAIResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
    private void saveDataToFirebase(String userInput, String rephrasedText, String selectedTone) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("history");
        String key = databaseReference.push().getKey();
        RewrittenText rewrittenText = new RewrittenText(userInput, rephrasedText, selectedTone);
        databaseReference.child(key).setValue(rewrittenText)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "Data saved successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }



}