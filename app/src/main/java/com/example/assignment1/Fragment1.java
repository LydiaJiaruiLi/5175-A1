package com.example.assignment1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NavigationRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class Fragment1 extends Fragment implements View.OnClickListener{
    Button OkButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_fist, container, false);

        OkButton = view.findViewById(R.id.StartButton);
        OkButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view){
        Navigation.findNavController(view).navigate(R.id.action_fragment1_to_fragment2);
    }
}
