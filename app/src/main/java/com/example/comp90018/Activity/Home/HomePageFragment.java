package com.example.comp90018.Activity.Home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.example.comp90018.DataModel.Feed;
import com.example.comp90018.R;

import java.util.ArrayList;

public class HomePageFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener listener;
    private static ArrayList<Feed> feeds_array=new ArrayList<>();
    //private static ArrayList<Feed> new_feeds_array;
    private TextView text_home;
    private ImageButton post;
    private ListView mainListView;
    private HomePageAdapter homepageAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    public static HomePageFragment newInstance(String param1, String param2) {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public HomePageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        //import adapter
        mainListView = (ListView) view.findViewById(R.id.browseListView);
        homepageAdapter = new HomePageAdapter(getActivity(),getData());
        mainListView.setAdapter(homepageAdapter);
        homepageAdapter.notifyDataSetChanged();

        text_home = (TextView) view.findViewById(R.id.text_home);

        post = (ImageButton) view.findViewById(R.id.add_post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to post activity;
                Intent intent = new Intent(getActivity(), PostActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private ArrayList<Feed> getData(){
        String username="Ann";
        String caption="Hello!";
        Drawable drawable = getResources().getDrawable(R.drawable.default_avatar);
        Bitmap touxiang = ((BitmapDrawable) drawable).getBitmap();
        Drawable drawable1 = getResources().getDrawable(R.drawable.photo);
        Bitmap postPhoto = ((BitmapDrawable) drawable1).getBitmap();
        ArrayList<String> comment=new ArrayList<>();
        ArrayList<String> like=new ArrayList<>();
        Boolean user_has_liked=false;
        Feed feed=new Feed(username, touxiang, postPhoto, comment, like, caption, user_has_liked);
        feeds_array.add(feed);
        return feeds_array;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    //do load feeds when view is visible to user
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
        }
        else {  }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (listener != null) {
            listener.onFragmentInteraction(uri);
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}