#include <iostream>
#include <vector>
#include <cmath>

double calculateStandardDeviation(const std::vector<double>& numbers) {
    if (numbers.empty()) {
        return 0.0;
    }

    double sum = 0.0;
    for (const double& num : numbers) {
        sum += num;
    }
    double mean = sum / numbers.size();

    double squaredDiffSum = 0.0;
    for (const double& num : numbers) {
        double diff = num - mean;
        squaredDiffSum += diff * diff;
    }
    return std::sqrt(squaredDiffSum / numbers.size());
}

int main() {
    std::vector<double> numbers;
    double input;

    std::cout << "enter nums (enter a non-number to finish):\n";

    while (std::cin >> input) {
        numbers.push_back(input);
    }

    if (numbers.empty()) {
        std::cout << "nah, no numbers entered.\n";
    } else {
        double stdDev = calculateStandardDeviation(numbers);
        std::cout << "std deviation: " << stdDev << std::endl;
    }

    return 0;
}