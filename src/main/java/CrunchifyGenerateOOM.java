import java.io.Serializable;

public class CrunchifyGenerateOOM implements Task, Serializable {


//    public static void main(String[] args) throws Exception {
//        CrunchifyGenerateOOM memoryTest = new CrunchifyGenerateOOM();
//        memoryTest.generateOOM();
//    }

    public String execute(int iteratorValue) {
//        int iteratorValue = 20;
        System.out.println("\n=================> OOM test started..\n");
        try {
            for (int outerIterator = 1; outerIterator < 20; outerIterator++) {
                System.out.println("Iteration " + outerIterator + " Free Mem: " + formatSize(Runtime.getRuntime().freeMemory()));
                int loop1 = 2;
                int[] memoryFillIntVar = new int[iteratorValue];
                // feel memoryFillIntVar array in loop..
                do {
                    memoryFillIntVar[loop1] = 0;
                    loop1--;
                } while (loop1 > 0);
                iteratorValue = iteratorValue * 5;
                System.out.println("\nRequired Memory for next loop: " + formatSize(iteratorValue));
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatSize(long v) {
        if (v < 1024) return v + " B";
        int z = (63 - Long.numberOfLeadingZeros(v)) / 10;
        return String.format("%.1f %sB", (double) v / (1L << (z * 10)), " KMGTPE".charAt(z));
    }


}