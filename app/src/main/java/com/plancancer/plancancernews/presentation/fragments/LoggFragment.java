package com.plancancer.plancancernews.presentation.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.plancancer.R;
import com.plancancer.plancancernews.AuthServiceConsumer;
import com.plancancer.plancancernews.presentation.activities.NewsActivityDrawer;
import com.plancancer.plancancernews.persistance.model.PlanCancerAccount;
import com.plancancer.plancancernews.service.PlanCancerREStAuthenticator;

/**
 * Created by Yazid on 01/04/2016.
 */
public class LoggFragment extends Fragment implements AuthServiceConsumer,View.OnClickListener {

    public static final String ARG_PAGE = "LOGG_PAGE";

    private int mPage=100;

    private EditText loginEdit;
    private EditText passwordEdit;
    private Button btnSubmit;



    public static LoggFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        LoggFragment fragment = new LoggFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plancancer_logg_fragment, container, false);
        this.loginEdit= (EditText) view.findViewById(R.id.loginFormEdit);
        this.passwordEdit= (EditText) view.findViewById(R.id.passwordFormEdit);
        this.btnSubmit= (Button) view.findViewById(R.id.submitFormBtn);
        this.btnSubmit.setOnClickListener(this);
        return view;
    }


    public void onClick(View v) {
        if(v==this.btnSubmit)
            this.startAuthProcedure();


    }

    public void startAuthProcedure(){
        PlanCancerAccount.getSingleton().setLogin(this.loginEdit.getText().toString());
        PlanCancerAccount.getSingleton().setPassword(this.passwordEdit.getText().toString());
        PlanCancerREStAuthenticator.getAuthenticator().authenticateUser(this);

    }

    public void authenticationResult(Boolean authenticated) {
        if(authenticated)
            ((NewsActivityDrawer)this.getActivity()).handleUserLogged();
    }
}
