package com.example.nutribalance.Entities;
public class Message {
    private String COACH_MESSAGE = "You have a new message from user %s";
    private String USER_MESSAGE = "You have a new message from coach %s";
    private String NEW_SUBSCRIPTION = "You have a new subscription from user %s";
    private String REPORT = "You have a new report";
    private String DELETE_SUBSCRIPTION = "User %s has deleted his subscription";
    private String NEW_PLAN = "Your coach %s has created a new plan for you";
    private String UPDATE_PLAN = "Your coach %s has updated your plan";

    public String getMessage(String username, NotificationType type) {
        return switch (type) {
            case COACH_MESSAGE -> String.format(COACH_MESSAGE, username);
            case USER_MESSAGE -> String.format(USER_MESSAGE, username);
            case NEW_SUBSCRIPTION -> String.format(NEW_SUBSCRIPTION, username);
            case REPORT -> REPORT;
            case DELETE_SUBSCRIPTION -> String.format(DELETE_SUBSCRIPTION, username);
            case NEW_PLAN -> String.format(NEW_PLAN, username);
            case UPDATE_PLAN -> String.format(UPDATE_PLAN, username);
            default -> null;
        };
    }
}
