#include <iostream>
#include <vector>
#include <string>

class Course {
public:
    std::string name;
    double grade;
    double credits;
    std::string type; // type refers to honors/ap/etc

    Course(std::string n, double g, double c, std::string t)
        : name(n), grade(g), credits(c), type(t) {}
};

double calculateGPA(const std::vector<Course>& courses) {
    double totalWeightedPoints = 0;
    double totalCredits = 0;

    for (const Course& course : courses) {
        double weightedGrade = course.grade;
        
        if (course.type == "Honors") {
            weightedGrade += 0.5;
        } else if (course.type == "AP") {
            weightedGrade += 1.0;
        }

        if (weightedGrade > 4.0) weightedGrade = 4.0;

        totalWeightedPoints += weightedGrade * course.credits;
        totalCredits += course.credits;
    }

    return totalCredits > 0 ? totalWeightedPoints / totalCredits : 0;
}

int main() {
    std::vector<Course> courses;
    std::string name, type;
    double grade, credits;
    char addMore;

    do {
        std::cout << "enter course name: ";
        std::cin >> name;

        std::cout << "enter grade (4.0 scale): ";
        std::cin >> grade;

        std::cout << "enter credits: ";
        std::cin >> credits;

        std::cout << "enter course type (reg/honors/ap): ";
        std::cin >> type;

        courses.emplace_back(name, grade, credits, type);

        std::cout << "add more course? (y/n): ";
        std::cin >> addMore;
    } while (addMore == 'y' || addMore == 'Y');

    double gpa = calculateGPA(courses);
    std::cout << "calculated. GPA is: " << gpa << std::endl;

    return 0;
}