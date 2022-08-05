package com.ppc.odc.data.creation;


import java.time.LocalDateTime;

public class DataCreatorConfiguration {
    public static int OPERATOR_COUNT = 30;
    public static int FEEDBACK_COUNT = 100;
    public static double FULLFILLED_FEEDBACK_FACTOR = 0.2;
    public static LocalDateTime FEEDBACK_STARTING_DATE = LocalDateTime.now();
    public static long FEEDBACK_TIME_SPAN_HOURS = 200;
}
