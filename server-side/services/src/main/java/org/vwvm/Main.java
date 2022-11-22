package org.vwvm;

import io.jsonwebtoken.io.Decoders;
import org.vwvm.store.commons.utils.MD5Utils;

public class Main {
    public static void main(String[] args) {
        byte[] keyBytes = Decoders.BASE64.decode("blackbox");
        String blackbox = MD5Utils.md5("blackbox");
        assert blackbox != null;
        System.out.println(blackbox.length());
    }
}