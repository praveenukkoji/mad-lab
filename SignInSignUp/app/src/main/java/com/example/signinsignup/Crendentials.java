package com.example.signinsignup;

import java.util.HashMap;
import java.util.Map;

public class Crendentials {

    HashMap<String, String> crendentialsMapper = new HashMap<String, String>();

    public void addCrendentials(String username, String password){
        crendentialsMapper.put(username, password);
    }

    public boolean checkUsername(String username){
        return crendentialsMapper.containsKey(username);
    }

    public boolean verifyCrendentials(String username, String password){

        if(crendentialsMapper.containsKey(username)){

            if(password.equals(crendentialsMapper.get(username))){
                return true;
            }
        }

        return false;
    }

    public void loadCrendentials(Map<String, ?> preferencesMap){

        for(Map.Entry<String, ?> entries : preferencesMap.entrySet()){
            if(!entries.getKey().equals("RememberMe")){
                crendentialsMapper.put(entries.getKey(), entries.getValue().toString());
            }
        }
    }
}
