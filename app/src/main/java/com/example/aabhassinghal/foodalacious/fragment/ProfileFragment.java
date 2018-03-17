package com.example.aabhassinghal.foodalacious.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aabhassinghal.foodalacious.R;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

/**
 * Created by aabhassinghal on 1/6/18.
 */

public class ProfileFragment extends Fragment {
    JSONObject response, profile_pic_data, profile_pic_url;
    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String strtext = getArguments().getString("edttext");
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView user_name = (TextView) view.findViewById(R.id.UserName);
        ImageView user_picture = (ImageView) view.findViewById(R.id.profilePic);
        TextView user_email = (TextView) view.findViewById(R.id.email);
        try {
            response = new JSONObject(strtext);
            user_email.setText(response.get("email").toString());
            user_name.setText(response.get("name").toString());
            profile_pic_data = new JSONObject(response.get("picture").toString());
            profile_pic_url = new JSONObject(profile_pic_data.getString("data"));
            Picasso.with(getActivity()).load(profile_pic_url.getString("url"))
                    .into(user_picture);

        } catch(Exception e){
            e.printStackTrace();
        }
        return view;
    }

}

