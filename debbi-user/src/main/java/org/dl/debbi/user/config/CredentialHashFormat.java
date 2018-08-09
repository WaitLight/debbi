//package org.dl.debbi.user.config;
//
//import org.apache.shiro.crypto.hash.Hash;
//import org.apache.shiro.crypto.hash.format.HashFormat;
//import org.apache.shiro.util.ByteSource;
//import org.springframework.stereotype.Component;
//
///**
// * 自定义密码格式化器
// * 于有salt的密码请自定义实现ParsableHashFormat然后把salt格式化到散列值中
// *
// * @author Dean
// * @version 0.0.1
// */
//@Component
//public class CredentialHashFormat implements HashFormat {
//
//    @Override
//    public String format(Hash hash) {
//        if (hash == null) {
//            return null;
//        }
//        ByteSource salt = hash.getSalt();
//        StringBuilder result = new StringBuilder();
//        result.append(hash.toBase64());
//        result.append(salt.toBase64());
//        return result.toString();
//    }
//}