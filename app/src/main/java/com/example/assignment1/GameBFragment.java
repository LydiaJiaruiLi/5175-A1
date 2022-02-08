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

/*
    This class is used to manage the content of the first page of game B. In this fragment, it
    has a "START" button on the bottom of page. If the button is clicked, it will start to show a
    animal picture for a random time between 1-3 seconds at the random position in 1 minute. After
    1 minute, it will jump to next screen and ask the user enter an integer as the number of
    animals the user has seen.
 */
public class GameBFragment extends Fragment implements View.OnClickListener {

    private ImageView imageView = null; // the image view
    private FrameLayout fr = null; // the image frame
    private int imgFrameWidth; // the width of image frame
    private int imgFrameHeight; // the height of image frame
    private int imgWidth; // the width of image
    private int imgHeight; // the height of image

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

        // Add a click listener on the "START" button
        view.findViewById(R.id.StartGameBButton).setOnClickListener(this);

        // Get the width and height of the animal image
        imageView = (ImageView) view.findViewById(R.id.animalImageView);
        imgWidth = imageView.getDrawable().getIntrinsicWidth();
        imgHeight = imageView.getDrawable().getIntrinsicHeight();

        // Get the width and height of the whole image frame
        fr = (FrameLayout) view.findViewById(R.id.imageFrame);
        imgFrameWidth = fr.getLayoutParams().width;
        imgFrameHeight = fr.getLayoutParams().height;

        return view;
    }

    int counter; // a counter used to count the number of images has been shown.
    boolean stop; // a flag to identify the stop position
    @Override
    public void onClick(View view){
        counter = 1;
        stop = false;
        displayAnimal();

        // move to score page after 1 minute
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                stop = true;
                System.out.println(counter);
                moveToAnswerPage(view, counter);
            }
        }, 60000); // delay to show next page after 1 minute
    }

    // This function is used to display images at random positions on the screen
    private void displayAnimal(){
        // Make the image visible
        imageView.setVisibility(View.VISIBLE);

        // random generate 1-3 seconds
        Random random = new Random();
        int min = 1;
        int max = 3;
        int randomTimer = random.nextInt((max - min) + 1) + min;

        // Generate a random position for image
        int randomPosX = random.nextInt(imgFrameWidth - imgWidth);
        int randomPosY = random.nextInt(imgFrameHeight - imgHeight);
        // Set the image position into generated random numbers
        imageView.setX(randomPosX);
        imageView.setY(randomPosY);

        // Display the image 1-3 seconds each time
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

    // This function is used to move and send the number of image has been shown to next fragment
    private void moveToAnswerPage(View view, int actualAmount){
        Bundle actual = new Bundle();
        actual.putInt("actualAmount", actualAmount);

        Navigation.findNavController(view).navigate(R.id.action_gameBFragment_to_gameBAnswerFragment, actual);
    }
}