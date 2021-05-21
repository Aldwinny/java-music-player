package com.alds.music.player.utils;

public class Logger {

    private static long time = 0L;
    private static boolean timed = false;

    public static void log(String className, Object object) {
        System.out.println("[" + className + "] " + object);
    }

    public static void log(String className, String str) {
        System.out.println("[" + className + "] " + str);
    }

    public static void log(Object object) {
        System.out.println("[Application] " + object);
    }

    public static void log(String str) {
        System.out.println("[Application] " + str);
    }

    public static void status(String str) {
        System.out.println("[Status] " + str);
    }

    public static void register(String str) {
        System.out.println("[Register] " + str);
    }

    public static void err(String className, String str) {
        System.err.println("[" + className + "] " + str);
    }

    public static void err(Object object) {
        System.err.println("[Error]" + object);
    }

    public static void err(String str) {
        System.err.println("[Error] " + str);
    }

    public static void customLog(String className, Object str, String type) {
        System.out.println("[" + type + "] " + "@" + className + " : " + str);
    }

    public static void inittimer() {
        timed = true;
        time = System.currentTimeMillis();
        customLog(Logger.class.getSimpleName(), "started.", "Timer");
    }

    public static void time() {
        if (timed) {
            customLog(Logger.class.getSimpleName(), System.currentTimeMillis() - time, "Timer");
        }
    }
}
