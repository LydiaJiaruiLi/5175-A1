package com.example.assignment1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameBFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameBFragment extends Fragment implements View.OnClickListener {

    private ImageView imageView = null;
    private FrameLayout fr = null;
    private int imgFrameWidth;
    private int imgFrameHeight;
    private int imgWidth;
    private int imgHeight;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GameBFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameBFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameBFragment newInstance(String param1, String param2) {
        GameBFragment fragment = new GameBFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_b, container, false);

        view.findViewById(R.id.StartGameBButton).setOnClickListener(this);

        imageView = (ImageView) view.findViewById(R.id.animalImageView);

        fr = (FrameLayout) view.findViewById(R.id.imageFrame);
        imgFrameWidth = fr.getLayoutParams().width;
        imgFrameHeight = fr.getLayoutParams().height;

        imgWidth = imageView.getDrawable().getIntrinsicWidth();
        imgHeight = imageView.getDrawable().getIntrinsicHeight();

        return view;
    }

    int counter;
    boolean stop;
    @Override
    public void onClick(View view){
        counter = 1;
        stop = false;
        displayAnimal();

        // move to score page after 1 minutes
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                stop = true;
                System.out.println(counter);
                moveToAnswerPage(view, counter);
            }
        }, 4000);

    }

    private void displayAnimal(){

        imageView.setVisibility(View.VISIBLE);
        Random random = new Random();

        int min = 1;
        int max = 3;

        // random generate 1-3 seconds
        int randomTimer = random.nextInt((max - min) + 1) + min;

        int randomPosX = random.nextInt(imgFrameWidth - imgWidth);
        int randomPosY = random.nextInt(imgFrameHeight - imgHeight);
        imageView.setX(randomPosX);
        imageView.setY(randomPosY);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!stop){
                    counter++;
                    imageView.setVisibility(View.INVISIBLE);
                    displayAnimal();
                }else{
                    stop = true;
                }
            }
        }, randomTimer * 1000);
    }

    private void moveToAnswerPage(View view, int actualAmount){
        Bundle actual = new Bundle();
        actual.putInt("actualAmount", actualAmount);

        Navigation.findNavController(view).navigate(R.id.action_gameBFragment_to_gameBAnswerFragment, actual);

    }

}