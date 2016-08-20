package com.plancancer.plancancernews.service;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.plancancer.plancancernews.PlanCancerServiceConsumer;
import com.plancancer.plancancernews.core_services.applications.NewsApplication;
import com.plancancer.plancancernews.core_services.network.VolleySingleton;
import com.plancancer.plancancernews.persistance.model.PlanCancerComment;
import com.plancancer.plancancernews.persistance.model.PlanCancerPostListItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Yazid on 10/05/2016.
 */
public class ServiceAccess implements IServicesAccessController {

    private static ServiceAccess controlAccess = new ServiceAccess();
    public static ServiceAccess getServiceAccessController(){return controlAccess;}
    private  ArrayList<PlanCancerComment> comments=new ArrayList<PlanCancerComment>();


    PlanCancerServiceConsumer serviceConsumer;
    PlanCancerServiceConsumer serviceConsumer1;





    public void getCommentsService(PlanCancerServiceConsumer consumer){
            this.serviceConsumer1=consumer;
        this.getUserComments();
    }


    @Override
    public List<PlanCancerComment> getUserComments() {
        if(this.comments!=null)
            this.comments.clear();
        Log.e("here getUse","GetuserComment");
        RequestQueue queue = VolleySingleton.createRequestQueue();
        //String url = "http://10.0.22.101/plancancer/services/srv_mycomments.php?mid=" + PlanCancerAccount.getCurrentAccount().getId();
        //String url="http://www.plan-cancer.dz/services/index.php?action=mc&mid=1600001";
        String url="http://plan-cancer.esi.dz/services/index.php?service=mc&mid=1600001";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.e("JSON---->","JSON object"+response.toString());
                    JSONArray array=response.getJSONArray("items");
                    for(int i=0;i<array.length();i++){
                        PlanCancerComment comment=new PlanCancerComment();
                        comment.setId(array.getJSONObject(i).getString("id"));
                        comment.setDate(array.getJSONObject(i).getString("date"));
                        comment.setContent(array.getJSONObject(i).getString("content"));
                        comment.setLikes(array.getJSONObject(i).getInt("likes"));
                        comment.setUnlikes(array.getJSONObject(i).getInt("unlikes"));
                        comment.setPost(array.getJSONObject(i).getString("post"));
                        Log.e("comment n="+i,""+comment.getId());
                        Log.e("comment "+comment.getId(),"content :"+comment.getContent());
                        comments.add(comment);
                    }

                    serviceConsumer1.consumeService(comments);
                    //return;

                } catch (Exception e) {
                    Log.e("Exce:getUserComments", e.getMessage());
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NewsApplication.getNewsContext(), "Erreur : " + error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


        queue.add(request);


        return this.comments;


    }

    public void getPostListItem(PlanCancerServiceConsumer consumer){
        this.serviceConsumer=consumer;
        RequestQueue queue = VolleySingleton.createRequestQueue();
        //String url="http://www.plan-cancer.dz/services/index.php?action=menu";
        String url="http://plan-cancer.esi.dz/index.php?service=menu";

        final ArrayList<String> listDataHeader=new ArrayList<String>();
        final HashMap<String,List<PlanCancerPostListItem>> listDataChild=new HashMap<String,List<PlanCancerPostListItem>>();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<PlanCancerPostListItem> temporaryListDataChild=new ArrayList<PlanCancerPostListItem>();

                    JSONArray array=response.getJSONArray("items");
                    String currentHeader=array.getJSONObject(0).getString("header");
                    //listDataHeader.add(currentHeader);
                    String temporaryHeader;
                    JSONObject workingJsonObject;
                    for(int i=0;i<array.length();i++){
                        workingJsonObject=array.getJSONObject(i);
                        temporaryHeader=workingJsonObject.getString("header");
                        if(!temporaryHeader.equals(currentHeader)){
                            listDataHeader.add(currentHeader);
                            listDataChild.put(currentHeader,temporaryListDataChild);
                            temporaryListDataChild=new ArrayList<PlanCancerPostListItem>();
                            currentHeader=temporaryHeader;
                            //listDataHeader.add(currentHeader);
                        }
                        temporaryListDataChild.add(new PlanCancerPostListItem(currentHeader,
                                workingJsonObject.getString("title"),
                                "http://plan-cancer.esi.dz/".concat(workingJsonObject.getString("link").concat("&service=post").concat("&posteid="+workingJsonObject.getString("posteid"))),
                                workingJsonObject.getString("posteid"),
                                workingJsonObject.getBoolean("comment")));

                        //
                        //TODO Delete Logs
                      /*  Log.e("header :",""+currentHeader+"  ------------");
                        Log.e("title : ",workingJsonObject.getString("title")+"");
                        Log.e("link : ",workingJsonObject.getString("link")+"");
                        Log.e("postId : ",workingJsonObject.getString("posteid")+"");
                        Log.e("comment : ",workingJsonObject.getBoolean("comment")+"");
                        Log.e("end of header :","------------");*/
                    }
                    listDataHeader.add(currentHeader);
                    listDataChild.put(currentHeader,temporaryListDataChild);
                    //temporaryListDataChild=new ArrayList<PlanCancerPostListItem>();
                    //listDataHeader.add(currentHeader);


                    Log.e("Test ","It's here");
                    ArrayList<Object> list=new ArrayList<Object>();
                    list.add(listDataHeader);
                    list.add(listDataChild);
                    serviceConsumer.consumeServiceMultiple(list);


                } catch (Exception e) {

                    Log.e("Error : getPostListItem",""+ e.getMessage()+"Cause :" +e.toString());
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NewsApplication.getNewsContext(), "Erreur : " + error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        queue.add(request);


    }



    //service=addcomment&posteid=P1600022&memberid=1600001&comment="hello world"
    public void sendComment(PlanCancerServiceConsumer consumer){
        this.serviceConsumer=consumer;
        RequestQueue queue = VolleySingleton.createRequestQueue();
        String url="http://www.plan-cancer.dz/services/index.php?action=addcomment&posteid=P1603002&memberid=1600001&comment=\"Je suis Intéressé\"";

        final ArrayList<String> listDataHeader=new ArrayList<String>();
        final HashMap<String,List<PlanCancerPostListItem>> listDataChild=new HashMap<String,List<PlanCancerPostListItem>>();


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {


                } catch (Exception e) {
                    Log.e("Error : sendComment",""+ e.getMessage()+"Cause :" +e.toString());
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NewsApplication.getNewsContext(), "Erreur : " + error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        queue.add(request);

    }

















}
