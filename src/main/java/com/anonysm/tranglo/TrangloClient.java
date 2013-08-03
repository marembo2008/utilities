/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anonysm.tranglo;

import com.anonysm.tranglo.service.EPinReload;
import com.anonysm.tranglo.service.EPinReloadSoap;

/**
 *
 * @author kenn
 */
public class TrangloClient {
     static final String username = "afrocoin_test";
    static final String password = "282784";
    static final String transID = "0349848";
    
    public static void main(String[] args) {
        EPinReload ep = new EPinReload();
        EPinReloadSoap soap = ep.getEPinReloadSoap();
//        int res = soap.ping();
//        System.out.println("ping = "+ res);        
        
        String souceNo = "255710214698";
        String prodCode = "";
        String destNo = souceNo;
        int deno = 1500;
        String res =soap.requestReload(souceNo, destNo, prodCode, deno, username, password, transID);
       // soap.requestReload(souceNo, destNo, prodCode, deno, username, password, transID);
         System.out.println("res "+res);
    }
    
    
    
}
