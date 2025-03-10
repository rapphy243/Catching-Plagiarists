import java.util.*;
import java.io.*;

/**
 * Write a description of class Essay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Essay
{
    private File folder;
    private String name;
    
    private Set<String> parse;
    
    public Essay(File folder, String name, int numWords)
    {
        this.folder = folder;
        this.name = name;
        
        this.parse = Parser.parseFile(getPath(), numWords);
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public String getPath()
    {
        return this.folder.toString() + "\\" + this.name;
    }
    
    public Set<String> getParse()
    {
        return this.parse;
    }
}
