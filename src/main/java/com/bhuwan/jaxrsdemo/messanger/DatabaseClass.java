/**
 * 
 */
package com.bhuwan.jaxrsdemo.messanger;

import java.util.HashMap;
import java.util.Map;

import com.bhuwan.jaxrsdemo.messanger.model.Message;
import com.bhuwan.jaxrsdemo.messanger.model.Profile;

/**
 * @author bhuwan
 *
 */
public class DatabaseClass {

    private static Map<Long, Message> messages = new HashMap<>();
    private static Map<String, Profile> profiles = new HashMap<>();

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }

}
