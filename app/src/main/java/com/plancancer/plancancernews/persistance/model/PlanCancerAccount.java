package com.plancancer.plancancernews.persistance.model;

/**
 * Created by Yazid on 17/04/2016.
 */
public class PlanCancerAccount {


    private static PlanCancerAccount singleton;//=new PlanCancerAccount();

    private String login;
    private String mail;
    private String password;
    private String token;
    private String role;


    private String id;
    private String nom;
    private String pnom;
    private String photo;

    private static boolean authenticated;



    public static PlanCancerAccount getCurrentAccount(){
        return singleton;
    }



    public static PlanCancerAccount getAccount(String login, String password) {
        if (singleton != null) {
            singleton.setLogin(login);
            singleton.setPassword(password);
        } else
            singleton = new PlanCancerAccount(login, password);
        return singleton;
    }

    private PlanCancerAccount(String login,String password){
        this.login=login;
        this.password=password;
    }


    public static PlanCancerAccount getSingleton() {
        return singleton;
    }

    public void setAuthenticated(boolean authenticated) {
        PlanCancerAccount.authenticated = authenticated;
    }


    public boolean isAuthenticated() {
        return authenticated;
    }


    public static boolean isUserAuth(){
        return PlanCancerAccount.authenticated;
    }


    public static void setUserLogged(boolean authenticate){
        PlanCancerAccount.authenticated=authenticate;

    }

    public static boolean getCurrentUserAuthState(){
        return PlanCancerAccount.authenticated;

    }


    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public String getPnom() {
        return pnom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }
}
