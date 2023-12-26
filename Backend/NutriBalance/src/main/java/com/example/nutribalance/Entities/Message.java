package com.example.nutribalance.Entities;
public class Message {
    private String MESSAGE = "You have a new message";
    private String NEW_SUBSCRIPTION = "You have a new subscription from user %s";
    private String REPORT = "You have a new report";
    private String DELETE_SUBSCRIPTION = "User %s has deleted his subscription";
    private String NEW_PLAN = "Your coach %s has created a new plan for you";
    private String UPDATE_PLAN = "Your coach %s has updated your plan";

    public String getMessage(String username, NotificationType type) {
        return switch (type) {
            case MESSAGE -> MESSAGE;
            case NEW_SUBSCRIPTION -> String.format(NEW_SUBSCRIPTION, username);
            case REPORT -> REPORT;
            case DELETE_SUBSCRIPTION -> String.format(DELETE_SUBSCRIPTION, username);
            case NEW_PLAN -> String.format(NEW_PLAN, username);
            case UPDATE_PLAN -> String.format(UPDATE_PLAN, username);
            default -> null;
        };
    }
}
