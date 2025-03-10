import java.util.*;
import java.io.File;

/**
 * This EssayPair class represents . . .
 *
 * @author  (your name)
 * @version (todays date)
 */
public class EssayPair implements Comparable<EssayPair>
{
    private Essay essay1;
    private Essay essay2;
    
    int commonPhraseHits;    
    public EssayPair(Essay essay1, Essay essay2)
    {
        this.essay1 = essay1;
        this.essay2 = essay2;
        
        this.commonPhraseHits = commonPhraseHits();
    }
    
    public Essay getEssay1()
    {
        return this.essay1;
    }
    
    public Essay getEssay2()
    {
        return this.essay2;
    }
    
    public int getN()
    {
        if (essay1.getN() != essay2.getN())
        {
            System.out.println(essay1.getFileName() + " and " + essay2.getFileName() + "have different N counts.");
            System.out.println("They will not have any common hits.");
            return -1;
        }        
        return essay1.getN();
    }
    
    public int getCommonPhraseHits()
    {
        return this.commonPhraseHits;
    }
    
    public String toString()
    {
        return "[" + essay1.getFileName() + ", " + essay2.getFileName() + "]";
    }
    
    public int compareTo(EssayPair other)
    {
        return Integer.compare(this.commonPhraseHits, other.commonPhraseHits());
    }
    
    private int commonPhraseHits()
    {
        if (essay1.getN() != essay2.getN())
        {
            System.out.println(essay1.getFileName() + " and " + essay2.getFileName() + "have different N counts.");
            System.out.println("They will not have any common hits.");
            return -1;
        }
        Set<String> set = new HashSet<String>();
        // I LOVE VARIABLE REFRENCES
        set.addAll(essay1.getParse());
        //
        set.retainAll(essay2.getParse());
        return set.size();
    }
    
    public static void main(String[] args)
    {
        System.out.println("Testing Hardcoded Examples:");
        File folder = new File("./Small number of documents");
        EssayPair example = new EssayPair(new Essay(folder, "jrf1109.shtml.txt", 4), new Essay(folder,"sra31.shtml.txt", 4));
        System.out.print(example.toString() + " -> ");
        System.out.println(example.getCommonPhraseHits());

        example = new EssayPair(new Essay(folder, "abf0704.shtml.txt", 4), new Essay(folder, "edo26.shtml.txt", 4));
        System.out.print(example.toString() + " -> ");
        System.out.println(example.getCommonPhraseHits());

        example = new EssayPair(new Essay(folder, "abf0704.shtml.txt", 4), new Essay(folder, "abf70402.shtml.txt", 4));
        System.out.print(example.toString() + " -> ");
        System.out.println(example.getCommonPhraseHits());

        example = new EssayPair(new Essay(folder, "abf70402.shtml.txt", 4), new Essay(folder, "edo26.shtml.txt", 4));
        System.out.print(example.toString() + " -> ");
        System.out.println(example.getCommonPhraseHits());

        example = new EssayPair(new Essay(folder, "abf0704.shtml.txt", 4), new Essay(folder, "edo20.shtml.txt", 4));
        System.out.print(example.toString() + " -> ");
        System.out.println(example.commonPhraseHits());
    }
}
