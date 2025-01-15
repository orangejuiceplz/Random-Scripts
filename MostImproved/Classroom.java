public class Classroom
{
    Student[] students;
    int numStudentsAdded;
    
    public Classroom(int numStudents)
    {
        students = new Student[numStudents];
        numStudentsAdded = 0;
    }
    
    public Student getMostImprovedStudent()
    {
        if (numStudentsAdded == 0)
        {
            return null;
        }
        
        Student mostImproved = students[0];
        int maxImprovement = mostImproved.getExamImprovement();
        
        for (int i = 1; i < numStudentsAdded; i++)
        {
            int currentImprovement = students[i].getExamImprovement();
            if (currentImprovement > maxImprovement)
            {
                maxImprovement = currentImprovement;
                mostImproved = students[i];
            }
        }
        
        return mostImproved;
    }
    
    public void addStudent(Student s)
    {
        students[numStudentsAdded] = s;
        numStudentsAdded++;
    }
    
    public void printStudents()
    {
        for(int i = 0; i < numStudentsAdded; i++)
        {
            System.out.println(students[i]);
        }
    }
}