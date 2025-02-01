package bu.cs622.utility;

import bu.cs622.Main;

import java.net.URL;
import java.nio.file.Paths;

/**
 * Name: Reaz W. Rahman
 * Course: CS 622: Advanced Programming Techniques
 * Date: 1/31/2025
 * File name: Configs.java
 * Description: This class is responsible for storing configuration values.
 */

public class Configs {

    public static final String OUTPUT_FILE = "output.csv";

    public static final URL INPUT_URL = Main.class.getClassLoader().getResource("inputs");
    public static final String INPUT_PATH = Paths.get(Configs.INPUT_URL.getPath()).toString();

    public static final String HEADERS = "bullet_point,category,category_url,clickthrough_url,close_date,currency,funds_raised_amount,funds_raised_percent,image_url,is_indemand,is_pre_launch,is_promoted,is_proven,offered_by,open_date,perk_goal_percentage,perks_claimed,price_offered,price_retail,product_stage,project_id,project_type,source_url,tagline,tags,title";
    public static final int CLOSE_DATE_INDEX = 4;
    public static final int FUNDS_RAISED_AMOUNT_INDEX = 6;
}

