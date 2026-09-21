package com.yourcompany.formbot.dto;

public class SubmissionResult {
    private boolean success;
    private String message;
    public SubmissionResult() {}
    public SubmissionResult(boolean success, String message) { this.success = success; this.message = message; }
    public static SubmissionResult success(String message) { return new SubmissionResult(true, message); }
    public static SubmissionResult failure(String message) { return new SubmissionResult(false, message); }
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
}
