package com.jb.csv3.security;

import com.jb.csv3.beans.enums.ClientType;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.exeptions.ErrMsg;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TokenManager {
    private Map<UUID, Information> tokens = new HashMap<>();

    public UUID addToken(Information information) {
        //delete previous tokens of current user id;
        deleteEntriesByUserId(information.getId());

        // generate new token
        UUID uuid = UUID.randomUUID();

        // put the token within the Information Object
        tokens.put(uuid, information);

        return uuid;
    }

    public void deleteEntriesByUserId(int id) {
        tokens.entrySet().removeIf(obj -> obj.getValue().getId() == id);
    }

    public int getUserID(UUID uuid) throws CouponSystemException {

        Information information = tokens.get(uuid);
        if (information == null) {
            throw new CouponSystemException(ErrMsg.USER_ID_NOT_FOUND);
        }
//        System.out.println("from token manager, get user id, prints tokens " + tokens);
        return tokens.get(uuid).getId();
    }

    public ClientType getClientType(UUID uuid) throws CouponSystemException {

        Information information = tokens.get(uuid);
        if (information == null) {
            throw new CouponSystemException(ErrMsg.USER_ID_NOT_FOUND);
        }
        return tokens.get(uuid).getClientType();
    }

    public boolean isAdmin(UUID uuid) throws CouponSystemException {
        Information information = tokens.get(uuid);
        if (information == null) {
            throw new CouponSystemException(ErrMsg.USER_ID_NOT_FOUND);
        }
        if (!information.getClientType().equals(ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrMsg.AUTH);
        }

        return true;
    }
}
