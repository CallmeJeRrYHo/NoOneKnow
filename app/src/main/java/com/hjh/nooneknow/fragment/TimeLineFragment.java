package com.hjh.nooneknow.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hjh.nooneknow.Config;
import com.hjh.nooneknow.R;
import com.hjh.nooneknow.activity.LoginActivity;
import com.hjh.nooneknow.localdata.MyContact;
import com.hjh.nooneknow.net.TimeLine;
import com.hjh.nooneknow.net.UpdateContacts;
import com.hjh.nooneknow.utils.Md5Utils;

import org.json.JSONArray;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link TimeLineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimeLineFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.rcvTimeLine)
    RecyclerView mRcvTimeLine;

    // TODO: Rename and change types of parameters
    private String phone;
    private String token;


    public TimeLineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TimeLineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TimeLineFragment newInstance(String param1, String param2) {
        TimeLineFragment fragment = new TimeLineFragment();
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
            phone = getArguments().getString(ARG_PARAM1);
            token = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_time_line, container, false);
        new UpdateContacts(Md5Utils.md5(phone), token, MyContact.getContact(getContext()), new UpdateContacts.SuccessCallback() {
            @Override
            public void onSuccess() {
                LoadMessage();
            }
        }, new UpdateContacts.FailCallback() {
            @Override
            public void onFail(int errorCode) {
                switch (errorCode) {
                    case Config.STATUS_INVALID_TOKEN:
                        Toast.makeText(getContext(),R.string.invalid_token,Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getContext(), LoginActivity.class));
                        getActivity().finish();
                        break;
                    default:
                        LoadMessage();
                }
            }
        });

        ButterKnife.bind(this, view);
        return view;
    }

    private void LoadMessage() {
        Log.d("1111", "LoadMessage: ");
        new TimeLine(Md5Utils.md5(phone), token, 1, 20, new TimeLine.SuccessCallback() {
            @Override
            public void OnSuccess(int page, int per, JSONArray timeline) {

            }
        }, new TimeLine.FailCallback() {
            @Override
            public void OnFail(int errorCode) {
                switch (errorCode){
                    case Config.STATUS_INVALID_TOKEN:
                        Toast.makeText(getContext(),R.string.invalid_token,Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getContext(), LoginActivity.class));
                        getActivity().finish();
                        break;
                    case Config.STATUS_FAIL:
                        Toast.makeText(getContext(),R.string.load_fail,Toast.LENGTH_LONG).show();
                        break;

                }
            }
        });
    }

}
