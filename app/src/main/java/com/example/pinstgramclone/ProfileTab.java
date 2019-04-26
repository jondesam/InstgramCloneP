package com.example.pinstgramclone;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

/**
 * A simple {@link Fragment} subclass.
 */



public class ProfileTab extends Fragment {

    private EditText editName, editBio, editHobby, editJob, editSport;
    private Button btnUpdateInfo;


    public ProfileTab() {
        // Required empty public constructor

    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_users_tab, container, false);
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container,
                false);




        editName = view.findViewById(R.id.editName);
        editJob = view.findViewById(R.id.editJob);
        editHobby= view.findViewById(R.id.editHobby);
        editBio= view.findViewById(R.id.editBio);
        editSport= view.findViewById(R.id.editSport);

        btnUpdateInfo = view.findViewById(R.id.BtnUpdateInfo);

        final ParseUser parseUser = new ParseUser().getCurrentUser();

        if (parseUser.get("profileName") == null) {
            //editName.setText("");
            editName.setText(parseUser.get("username") + "" );
        } else {
            editName.setText(parseUser.get("profileName") + "" );
            editBio.setText(parseUser.get("profileBio") + "");
            editHobby.setText(parseUser.get("profileHobby") + "");
            editJob.setText(parseUser.get("profileJob") + "");
            editSport.setText(parseUser.get("profileSport") + "");
        }





        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parseUser.put("profileName", editName.getText().toString());
                parseUser.put("profileBio", editBio.getText().toString());
                parseUser.put("profileJob", editJob.getText().toString());
                parseUser.put("profileHobby",editHobby.getText().toString());
                parseUser.put("profileSport", editSport.getText().toString());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(getContext(),  "Updated!",
                                    Toast.LENGTH_LONG,FancyToast.INFO,true).show();
                        } else  {
                            FancyToast.makeText(getContext(),  e.getMessage(),
                                    Toast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }
                    }
                });
            }
        });





        return view;


    }


//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//            try {
//                InputMethodManager mImm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                mImm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
//                mImm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
//            } catch (Exception e) {
//               // Log.e(TAG, "setUserVisibleHint: ", e);
//                e.printStackTrace();
//            }
//        }
//    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
//
//    }

//    public void rootLayoutTapped(View view){
//
//        try{
//            InputMethodManager inputMethodManager =
//                    (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//            //inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),0 );
//
//            inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public void rootLayoutTapped(View v) {
//        switch(v.getId()) {
//
//
//            case R.id.layOut:
//            // Just like you were doing
//            try{
//                InputMethodManager inputMethodManager =
//                        (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                //inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),0 );
//
//                inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }



//    public static void hideKeyboard(Activity activity)  {
//        try{
//            InputMethodManager inputManager = (InputMethodManager)
//                    .getSystemService(Context.INPUT_METHOD_SERVICE);
//            View currentFocusedView = activity.getCurrentFocus();
//            if (currentFocusedView != null) {
//                inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }


}
