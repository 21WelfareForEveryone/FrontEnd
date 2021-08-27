package com.example.tave0819;

public class User{
    private String id, pwd, username, address, mToken;
    private int age, lifeCycle, income;
    private boolean gender, isMultiCultural, familyState, isDisabled, isVeterans;
    private String pushToken;

    public User(String username, String id, String pwd, String address, String mToken, int age, int lifeCycle, int income,
                boolean gender, boolean isMultiCultural, boolean familyState, boolean isDisabled, boolean isVeterans, String pushToken){
        this.username = username;
        this.id = id;
        this.pwd = pwd;
        this.address = address;
        this.mToken = mToken;
        this.age = age;
        this.lifeCycle = lifeCycle;
        this.income = income;
        this.gender = gender; // int 0,1
        this.isMultiCultural = isMultiCultural;
        this.isDisabled = isDisabled;
        this.familyState = familyState;
        this.isVeterans = isVeterans;
        this.pushToken = pushToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd){
        this.pwd = pwd;
    }

    public boolean  getGender(){return gender;}
    public void setGender(boolean gender){this.gender = gender;}
    public String getmToken(){return mToken;}
    public void setmToken(String mToken){this.mToken = mToken;}
    public int  getAge(){return age;}
    public void setAge(int age){this.age = age;}
    public int getLifeCycle(){return lifeCycle;}
    public void setLifeCycle(int lifeCycle){this.lifeCycle = lifeCycle;}
    public String getAddress(){return address;}
    public void setAddress(String address){this.address = address;}
    public int getIncome(){return income;}
    public void setIncome(int income){this.income = income;}

    public boolean getIsMultiCultural(){return isMultiCultural;}
    public void setIsMultiCultural(boolean isMultiCultural){this.isMultiCultural = isMultiCultural;}
    public boolean getIsDisabled(){return isDisabled;}
    public void  setIsDisabled(boolean isDisabled){this.isDisabled = isDisabled;}

    public boolean getFamilyState(){return familyState;}
    public void setFamilyState(boolean familyState){this.familyState = familyState;}

    public boolean getIsVeterans(){return isVeterans;}
    public void setIsVeterans(boolean isVeterans){this.isVeterans = isVeterans;}

    public String getPushToken(){return pushToken;}
    public void setPushToken(String pushToken){this.pushToken = pushToken;}

}
