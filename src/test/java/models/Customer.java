package models;

public class Customer {
    private String meno;
    private String ulica;
    private String mesto;
    private String ZIP;
    private String tel_num;

    public Customer (String meno, String ulica, String mesto, String ZIP, String tel_num){
        this.meno = meno;
        this.ulica = ulica;
        this.mesto = mesto;
        this.ZIP = ZIP;
        this.tel_num = tel_num;
    }

    public String getMeno() {
      return meno;
    }
    public String getUlica() {
        return ulica;
    }
    public String getMesto() {
        return mesto;
    }
    public String getZIP() {
        return ZIP;
    }
    public String getTel_num() {
        return tel_num;
    }
}
