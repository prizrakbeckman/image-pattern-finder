package ma.hardprizrak.business.impl;

import ma.hardprizrak.business.ImageFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ImageFinderImpl implements ImageFinder {


    @Override
    public int[] matchFirstOccurencePattern(String[] image, int imageWidth, int imageHeight, String[] pattern, int patternHeight, int patternWidth) {
        List<Pair> intsResult = new ArrayList<>();
        int finalIndiceX = -1;
        int finalIndiceY = -1;

        for (int i = 0; i < imageHeight; i++) {
            String imageCurrentLine = image[i];
            String patternCurrentLine = pattern[i];
            List<Integer> currentIndexes = listOfIndexes(imageCurrentLine, patternCurrentLine);
            for (int k = 0; k < currentIndexes.size(); k++) {
                for (int j = 0; j < patternHeight && j < imageHeight; j++) {
                    imageCurrentLine = image[i + j].substring(currentIndexes.get(k));
                    patternCurrentLine = pattern[i + j];
                    if (!imageCurrentLine.contains(patternCurrentLine)) {
                        break;
                    }
                   if (j == patternHeight - 1) {
                        Pair p = new Pair(k, currentIndexes.get(k));
                        intsResult.add(p);
                    }
                }
            }
        }

        Pair pair = intsResult.stream().sorted().findFirst().orElse(new Pair(finalIndiceX, finalIndiceY));


        return new int[]{pair.indiceX, pair.indiceY};
    }

    private List<Integer> listOfIndexes(String word, String guess) {
        List<Integer> result = new ArrayList<>();
        int index = word.indexOf(guess);
        while (index >= 0) {
            System.out.println(index);
            index = word.indexOf(guess, index + 1);
            result.add(index);
        }
        return result;
    }

    
}

class Pair {
        int indiceX;
        int indiceY;

        public Pair(int indiceX, int indiceY) {
            this.indiceX = indiceX;
            this.indiceY = indiceY;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return this.indiceX == pair.indiceX && this.indiceY == pair.indiceY;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.indiceX, this.indiceY);
        }
    }


