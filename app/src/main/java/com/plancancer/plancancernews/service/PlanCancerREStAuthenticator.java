package com.plancancer.plancancernews.service;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.plancancer.plancancernews.core_services.applications.NewsApplication;
import com.plancancer.plancancernews.core_services.network.VolleySingleton;
import com.plancancer.plancancernews.persistance.model.PlanCancerAccount;

import org.json.JSONObject;

/**
 * Created by Yazid on 17/04/2016.
 */
public class PlanCancerREStAuthenticator implements AuthenticationController {

    private static PlanCancerREStAuthenticator authenticator=new PlanCancerREStAuthenticator();

    public static PlanCancerREStAuthenticator getAuthenticator(){
        return authenticator;
    }

    private PlanCancerREStAuthenticator(){

    }

    @Override
    public  boolean authenticateUser() {
        //TODO appel au service REST et reception du resultat JSON
        RequestQueue queue= VolleySingleton.createRequestQueue();
        PlanCancerAccount account=PlanCancerAccount.getCurrentAccount();

        //String url="http://10.0.22.101/plancancer/services/srv_connect.php?L="+account.getLogin()+"&P="+account.getPassword();
        //String url="http://10.0.22.101/plancancer/services/srv_connect.php?L=test&P=test";
        Log.e("CurrentUser","CurrentUser :: "+PlanCancerAccount.getCurrentAccount().getLogin());
        Log.e("CurrentUser","CurrentUser :: "+PlanCancerAccount.getCurrentAccount().getPassword());
        //String url="http://www.plan-cancer.dz/services/index.php?action=lg&L="+PlanCancerAccount.getCurrentAccount().getLogin()+"&P="+PlanCancerAccount.getCurrentAccount().getPassword();
        //String url="http://plan-cancer.esi.dz/index.php?service=lg&L="+PlanCancerAccount.getCurrentAccount().getLogin()+"&P="+PlanCancerAccount.getCurrentAccount().getPassword();
        String url="http://plan-cancer.esi.dz/index.php?service=lg&L=test&P=test";
        Log.e("currentURL","url :: "+url);
        Log.e("CurrentUSer: ",account.getLogin()+account.getPassword());

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getInt("id")==-1)
                        PlanCancerAccount.getCurrentAccount().setAuthenticated(false);
                    else {
                        PlanCancerAccount.getCurrentAccount().setId((String) response.get("id"));
                        PlanCancerAccount.getCurrentAccount().setId((String) response.get("nom"));
                        PlanCancerAccount.getCurrentAccount().setId((String) response.get("pnom"));
                        PlanCancerAccount.getCurrentAccount().setId((String) response.get("photo"));
                        PlanCancerAccount.getCurrentAccount().setAuthenticated(true);
                    }
                    Log.e("CurrentUSer from sing: ",PlanCancerAccount.getCurrentAccount().getLogin()+PlanCancerAccount.getCurrentAccount().getPassword());
                }catch (Exception e){
                    Log.e("authenticateUser",e.getMessage());
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO edit the error response of the user authenticator
                //Toast.makeText(NewsApplication.getNewsContext(),"Erreur : "+error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        queue.add(request);

        return PlanCancerAccount.isUserAuth();

    }


    public static void authenticateUserStat() {
        //TODO appel au service REST et reception du resultat JSON
        RequestQueue queue= VolleySingleton.getRequestQueue();
        String url="http://10.0.22.101/plancancer/services/srv_connect.php?L="+PlanCancerAccount.getAccount("test","test").getLogin()+"&P="+PlanCancerAccount.getCurrentAccount().getPassword();

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(NewsApplication.getNewsContext(),response.toString(),Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NewsApplication.getNewsContext(),"Erreur : "+error.getMessage(),Toast.LENGTH_LONG).show();

            }
        });


        queue.add(request);


    }
}
