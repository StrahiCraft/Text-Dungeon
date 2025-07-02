package graphics;

public class Color {
    public static String resetColor(){
        return "\u001B[0m";
    }

    /**
     * Takes in lower case string with a color name supported by the ANSI escape character standard.
     * <a href="https://en.wikipedia.org/wiki/ANSI_escape_code#Colors">Check supported colors here</a>
     * @param color
     * String of a color that is supported by the ANSI escape character standard
     * @return
     * an ANSI escape character for color
     */
    public static String getColor(String color){
        switch(color.toLowerCase()){
            case "black": return "\u001B[30m";
            case "red": return "\u001B[31m";
            case "green": return "\u001B[32m";
            case "yellow": return "\u001B[33m";
            case "blue": return "\u001B[34m";
            case "magenta": return "\u001B[35m";
            case "cyan": return "\u001B[36m";
            case "white": return "\u001B[37m";
            case "gray": return "\u001B[90m";
            case "bright red": return "\u001B[91m";
            case "bright green": return "\u001B[92m";
            case "bright yellow": return "\u001B[93m";
            case "bright blue": return "\u001B[94m";
            case "bright magenta": return "\u001B[95m";
            case "bright cyan": return "\u001B[96m";
            case "bright white": return "\u001B[97m";
            default: break;
        }
        return "\u001B[0m";
    }
}
