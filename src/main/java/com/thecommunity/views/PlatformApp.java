package com.thecommunity.views;

import com.thecommunity.model.Platform;
import com.thecommunity.model.User;

import java.util.Scanner;

public class PlatformApp {
    private static Platform platform;
    private static User user;

    public static void main(String[] args) {
        displayWelcomePage();
    }

    private static void displayWelcomePage(){
        String entryPoint = """
                Enter 1 to Register
                Enter 2 to Log In
                Enter 3 to exit application
                """;
        int entryInput = collectIntegerInput(entryPoint);

        switch (entryInput) {
            case 1 -> goToRegisterPage();
            case 2 -> goToLoginPage();
            case 3 -> System.exit(0);
        }
    }

    private static void goToLoginPage() {
        String email = collectStringInput("Enter email address:");
        String pass_word = collectStringInput("Enter your Password");
        if(platform.isLogin(email, pass_word)){
            dashBoard();
        }
    }

    private static void goToRegisterPage() {
        String firstName = collectStringInput("Enter first name:");
        String lastName = collectStringInput("Enter last name:");
        String email = collectStringInput("Enter Emails Address:");
        String password = collectStringInput("Create Password:");
        user = new User(firstName, lastName, email);
        user.setPassword(password);
        platform.register(user);
        dashBoard();
    }

    private static void dashBoard(){
        String dashBoardMenu = """
                Enter 1 to create send message
                Enter 2 to view message
                Enter 3 to Log out
                """;
        int input = collectIntegerInput(dashBoardMenu);
        switch (input){
            case 1:
                String userName = collectStringInput("Enter username");
                String message = collectStringInput("Type message");
//                user.getAccount().sendMessage(message, platform, userName);
                break;
            case 2:
//                user.getAccount().displaySentMessage();
                break;
            case 3:
                break;
        }
    }

    private static String collectStringInput(String prompt){
        display(prompt);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    private static int collectIntegerInput(String prompt){
        display(prompt);
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    private static void display(String message){
        System.out.println(message);
    }
}
