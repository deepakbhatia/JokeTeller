package com.jokes;

public class Jokes {

    //Singleton instance
    private static Jokes jokesInstance = new Jokes();

    public static String joke = "I'm afraid of my computer.\nI know when I turn it off,\nit's learning new things without me.";

    public static Jokes getInstance() {
        return jokesInstance;
    }

}
