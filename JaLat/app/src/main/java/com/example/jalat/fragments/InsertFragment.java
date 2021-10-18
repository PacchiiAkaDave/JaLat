package com.example.jalat.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jalat.R;
import com.example.jalat.activity.MainActivity;


/**
 *
 * @author Jan Weimer
 *
 * */
public class InsertFragment extends Fragment implements View.OnClickListener{

    TextView score;
    EditText inputName;
    Button insert;
    NavController navController;
    String name;
    int pts;

    public InsertFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_insert, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        insert = (Button) view.findViewById(R.id.insert_in_highscore);
        insert.setOnClickListener(this);
        score = (TextView) view.findViewById(R.id.yourScore);
        inputName = (EditText) view.findViewById(R.id.yourName);

        score.setText(String.valueOf(MainActivity.getPoints()));

        view.invalidate();

    }


    //Nach Bestaetigung Einsetzen in den Highscore
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.insert_in_highscore:
                name = inputName.getText().toString();
                pts = MainActivity.getPoints();
                MainActivity.insert(name,pts);
                navController.navigate(R.id.action_insertFragment_to_sylOrWordFragment);
        }
    }
}
