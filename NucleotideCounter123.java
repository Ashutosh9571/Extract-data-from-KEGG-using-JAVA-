
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class NucleotideCounter123 {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\ashut\\OneDrive\\Documents\\atgc.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sequence = new StringBuilder();
            int sequenceCount = 0;

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    // Process the sequence
                    if (sequence.length() > 0) {
                        sequenceCount++;
                        processSequence(sequence.toString(), sequenceCount);
                        sequence.setLength(0); // Clear the buffer for the next sequence
                    }
                } else {
                    sequence.append(line.trim());
                }
            }

            // Process the last sequence if present
            if (sequence.length() > 0) {
                sequenceCount++;
                processSequence(sequence.toString(), sequenceCount);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processSequence(String sequence, int sequenceCount) {
        int aCount = 0, tCount = 0, gCount = 0, cCount = 0;
        int atCount = 0, gcCount = 0;
        int length = sequence.length();

        for (int i = 0; i < sequence.length(); i++) {
            char nucleotide = sequence.charAt(i);
            switch (nucleotide) {
                case 'a': aCount++; break;
                case 't': tCount++; break;
                case 'g': gCount++; break;
                case 'c': cCount++; break;
            }

            // Count dimers
            if (i < sequence.length() - 1) {
                String dimer = sequence.substring(i, i + 2);
                if (dimer.equals("at")) atCount++;
                if (dimer.equals("gc")) gcCount++;
            }
        }

        // Calculate percentages
        double aPercentage = (aCount / (double) length) * 100;
        double tPercentage = (tCount / (double) length) * 100;
        double gPercentage = (gCount / (double) length) * 100;
        double cPercentage = (cCount / (double) length) * 100;

        // Print the results for each sequence
        System.out.println("Sequence " + sequenceCount + ":");
        System.out.println("Length: " + length);
        System.out.println("a: " + aCount + " (" + aPercentage + "%)");
        System.out.println("t: " + tCount + " (" + tPercentage + "%)");
        System.out.println("g: " + gCount + " (" + gPercentage + "%)");
        System.out.println("c: " + cCount + " (" + cPercentage + "%)");
        System.out.println("at dimers: " + atCount);
        System.out.println("gc dimers: " + gcCount);
        System.out.println();
    }
}
