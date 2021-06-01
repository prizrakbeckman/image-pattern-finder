package ma.hardprizrak.business;



public interface ImageFinder {

    int[] matchFirstOccurencePattern(String[] image, int imageWidth, int imageHeight, String[] pattern, int patternHeight, int patternWidth );

}
