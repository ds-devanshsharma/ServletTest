package com.test;

import com.sun.deploy.security.SelectableSecurityManager;

public class ValidationServlet {

    static boolean checkAlphabate(String number){
        number= number.toLowerCase();
        boolean flag =true;
        for(int i=0;i<number.length();i++){
            if((number.charAt(i)>='a' && number.charAt(i)<= 'z' )){

            }else{
                flag=false;
                break;
            }

        }
        return flag;
    }
}
