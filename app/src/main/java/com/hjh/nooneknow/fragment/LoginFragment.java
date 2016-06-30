package com.hjh.nooneknow.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hjh.nooneknow.Config;
import com.hjh.nooneknow.R;
import com.hjh.nooneknow.activity.TimeLineActivity;
import com.hjh.nooneknow.net.NetConnection;
import com.hjh.nooneknow.utils.Md5Utils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.RequestBody;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.etPhoneNum)
    EditText mEtPhoneNum;
    @BindView(R.id.btnGetCode)
    Button mBtnGetCode;
    @BindView(R.id.etCode)
    EditText mEtCode;
    @BindView(R.id.btnLogin)
    Button mBtnLogin;


    // TODO: Rename and change types of parameters
    private String phone;
    private String token;


    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    private void GetCode() {
        if (TextUtils.isEmpty(mEtPhoneNum.getText()))
            Toast.makeText(getContext(), R.string.empty_phone_num, Toast.LENGTH_LONG).show();
        else {
            RequestBody body = new FormBody.Builder().add("action", "send_pass")
                    .add("phone", mEtPhoneNum.getText().toString())
                    .build();

            NetConnection.getmInstance().post(Config.SERVER, new NetConnection.ResultCallback() {
                @Override
                public void onSuccess(String s) {
                    Log.d("111111", "onSuccess: " + s);
                    try {
                        JSONObject j = new JSONObject(s);
                        switch (j.getInt(Config.KEY_STATUS)) {
                            case Config.STATUS_SUCCESS:
                                Toast.makeText(getContext(), R.string.get_code_success, Toast.LENGTH_LONG).show();
                                break;
                            case Config.STATUS_FAIL:
                                Toast.makeText(getContext(), R.string.get_code_fail, Toast.LENGTH_LONG).show();
                                break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), R.string.get_code_fail, Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFail(String s) {
                    Toast.makeText(getContext(), R.string.get_code_fail, Toast.LENGTH_LONG).show();
                }
            }, body);
        }
    }

    @OnClick({R.id.btnGetCode, R.id.btnLogin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnGetCode:
                GetCode();
                break;
            case R.id.btnLogin:
                Login();
                break;
        }
    }

    private void Login() {
        if (TextUtils.isEmpty(mEtPhoneNum.getText()))
            Toast.makeText(getContext(), R.string.empty_phone_num, Toast.LENGTH_LONG).show();
        else if (TextUtils.isEmpty(mEtCode.getText()))
            Toast.makeText(getContext(), R.string.empty_code, Toast.LENGTH_LONG).show();
        else {
            RequestBody body = new FormBody.Builder()
                    .add(Config.KEY_ACTION, Config.LOGIN)
                    .add(Config.KEY_PHONE_MD5, Md5Utils.md5(mEtPhoneNum.getText().toString()))
                    .add(Config.KEY_CODE, mEtCode.getText().toString())
                    .build();
            NetConnection.getmInstance().post(Config.SERVER, new NetConnection.ResultCallback() {
                @Override
                public void onSuccess(String s) {
                    Log.d("111111", "onSuccess: " + s);
                    try {
                        JSONObject j=new JSONObject(s);
                        if (j.getInt(Config.KEY_STATUS)==1){
                            Config.saveCacheToken(getContext(),j.getString(Config.KEY_TOKEN));
                            Config.savePhone(getContext(),mEtPhoneNum.getText().toString());
                            Intent i=new Intent(getContext(), TimeLineActivity.class);
                            i.putExtra(Config.KEY_PHONE,mEtPhoneNum.getText().toString());
                            i.putExtra(Config.KEY_TOKEN,j.getString(Config.KEY_TOKEN));
                            startActivity(i);
                            getActivity().finish();
                        }else
                            Toast.makeText(getContext(), R.string.login_fail, Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), R.string.login_fail, Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFail(String s) {
                    Toast.makeText(getContext(), R.string.login_fail, Toast.LENGTH_LONG).show();
                    return;
                }
            }, body);
        }
    }
}
