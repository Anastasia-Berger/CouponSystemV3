package com.jb.csv3.exeptions;

import org.springframework.http.HttpStatus;

public enum ErrMsg {

    // throw new CouponSystemException(ErrMsg.XXXXXXXX);


    DUPLICATE_COMPANY_NAME("That company name already exist, please contact us if you need help.", HttpStatus.CONFLICT),
    DUPLICATE_COUPON_TITLE("Your company already has coupon with same title, choose another title or update amount of existing coupon.", HttpStatus.CONFLICT),

    COMPANY_DOESNT_EXIST("Company doesnt exist", HttpStatus.NOT_FOUND),
    ILLEGAL_ACTION_EXCEPTION("Illegal Action", HttpStatus.FORBIDDEN),
    ZERO_VALUE("The value cannot be less or equal to zero", HttpStatus.NOT_FOUND),

    INCORRECT_LOGIN("OOPS! You've entered incorrect username or password.", HttpStatus.UNAUTHORIZED),
    INCORRECT_PASSWORD("OOPS! You've entered incorrect password. Try again...", HttpStatus.UNAUTHORIZED),

    OUT_OF_AMOUNT("Coupon is out of stock", HttpStatus.NOT_FOUND),
    EXPIRED("Sorry, this coupon already expired.", HttpStatus.NOT_FOUND),
    ALREADY_PURCHASED("Coupon is already purchased by you.", HttpStatus.CONFLICT),
    TOKEN_NOT_EXIST("Token not recognised", HttpStatus.NOT_FOUND),
    UNAUTHORIZED_EVENT("Unauthorized event", HttpStatus.UNAUTHORIZED),
    COUPON_IS_OUT_OF_STOCK("Coupon is out of stock", HttpStatus.NO_CONTENT),

    ID_NOT_EXIST("Id Not Exist", HttpStatus.NOT_FOUND),
    ID_ALREADY_EXIST("ID already exist", HttpStatus.CONFLICT),
    EMAIL_ALREADY_EXIST("Email already exist", HttpStatus.CONFLICT),
    EMAIL_OR_PASSWORD_INCORRECT("Wrong email or password", HttpStatus.UNAUTHORIZED),
    USER_ID_NOT_FOUND("Hi...login please", HttpStatus.UNAUTHORIZED),
    USER_ID_NOT_MATCH_TASK("Invalid Operation", HttpStatus.FORBIDDEN),
    AUTH("NOT AUTHORIZED", HttpStatus.UNAUTHORIZED);

    private String description;
    private HttpStatus status;

    ErrMsg(String description, HttpStatus status) {
        this.description = description;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
