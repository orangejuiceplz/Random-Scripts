public class MyProgram
{
    public static void main(String[] args)
    {
        String studentName = "Satoru Gojo";
        
        String courseName = "Cursed Energy Manipulation";
        
        int firstGrade = 90;
        
        int secondGrade = 100;
        
        int thirdGrade = 72;
        
        int sumOfGrades = firstGrade + secondGrade + thirdGrade;

        double averageGrade = sumOfGrades / 3.0;
        
        int difference = Math.max(firstGrade, Math.max(secondGrade, thirdGrade)) - Math.min(firstGrade, Math.min(secondGrade, thirdGrade));
        
        int product = firstGrade * secondGrade * thirdGrade;

        int remainder = sumOfGrades % 5;
        
        System.out.println("Student Name: " + studentName);
        
        System.out.println("Course Name: " + courseName);
        
        System.out.println("First Grade: " + firstGrade);
        
        System.out.println("Second Grade: " + secondGrade);
        
        System.out.println("Third Grade: " + thirdGrade);
        
        System.out.println("Average Grade: " + averageGrade);
        
        System.out.println("Difference of highest and lowest grade: " + difference);
        
        System.out.println("Product of the three grades: " + product);
        
        System.out.println("Remainder of the sum of the grades divided by 5: " + remainder);
        
    }
}
