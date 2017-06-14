package com.jvitatoe.workouts_main;

/**
 * Created by JVitatoe on 9/20/15.
 */
public class Workouts {

    public static final String[] WORKOUTS = {"Deadlift","Dumbbell flys","Tricep extension","Squat", "Calf raises"};
    public static final String[] DETAILS =
            {"~DEADLIFTS~\n " + "\nThe deadlift is an excellent compound exercise that targets the quads, " +
                    "hamstrings, gluteal muscles, lower back, traps, and forearms--not to mention it " +
                    "will make you feel like a beast when you do it.\n \nConventional deadlift:\n " +
                    "The deadlift can be broken down into three parts: The setup, the initial pull " +
                    "or drive, and the lockout.\n" +
                    "\n" +
                    "Setup: When performing a deadlift, a lifter will set up in a position that " +
                    "eccentrically loads the gluteus maximus, minimus, biceps femoris, " +
                    "semitendinosus, and semimembranosus while the muscles of the lumbar contract " +
                    "isometrically in an effort to stabilize the spine.\n" +
                    "\n" +
                    "    Set up behind the bar with it touching or nearly touching the legs.\n" +
                    "    Begin by hinging at the hips and knees, setting one's weight predominantly " +
                    "in the heels while maintaining flat feet.\n" +
                    "    Spine stays long and straight as hips hinge back, taking care not to allow " +
                    "knees to track forward over one’s toes." ,

                    "~DUMBBELL FLYS~\n" + "\nUsed to stimulate the pectoral muscles in the chest. " +
                            "It is performed by holding dumbbells (one in each hand), lying back on " +
                            "a bench or flat surface, and using the pectoral and deltoid muscles to " +
                            "adduct the weights inwards. Usually the elbows stay fairly fixed and " +
                            "arms almost straight.",


                    "~TRICEP EXTENSION~\n" + "\nThis medium intensity exercise is a good easy workout " +
                            "to increase the strength of your triceps." +"stand up with a dumbbell " +
                            "held by both hands. Your feet should be about shoulder width apart " +
                            "from each other. Slowly use both hands to grab the dumbbell and lift " +
                            "it over your head until both arms are fully extended",

                    "~Squat~", "~Calf raises~"};

    public static String[] getWORKOUTS() {
        return WORKOUTS;
    }

    public static String[] getDETAILS() {
        return DETAILS;
    }
}
