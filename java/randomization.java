public class randomization {
    
    public static void main(String[] args) {
            
        String oldSeq = "110111010110111";
        String segment = "11";
        String newSeq;
        int a = oldSeq.indexOf(segment);
        String part1 = oldSeq.substring(0, a);
        String part2 = oldSeq.substring(a + segment.length());
        newSeq = part1 + part2;
        System.out.println(oldSeq);
        System.out.println(newSeq);
        
        
    }
}
