#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <ctime>
#include <iomanip>
#include <fstream>
#include <sstream>
#include <random>
#include <chrono>

class QualityOfLife {
private:
    static std::string getCurrentDateTime() {
        auto now = std::chrono::system_clock::now();
        auto in_time_t = std::chrono::system_clock::to_time_t(now);
        std::stringstream ss;
        ss << std::put_time(std::localtime(&in_time_t), "%Y-%m-%d %X");
        return ss.str();
    }

public:
    static void showHelp() {
        std::cout << "Available commands:\n"
                  << "1. todo - a simple to-do list\n"
                  << "2. calc - simple calculator\n"
                  << "3. timer - set a countdown timer\n"
                  << "4. notes - take quick notes\n"
                  << "5. random - generate random numbers\n"
                  << "6. convert - conv between units\n"
                  << "7. quit - exit the program\n";
    }

    static void manageTodo() {
        std::vector<std::string> todos;
        std::string input;
        while (true) {
            std::cout << "\nto-do list mgr \n"
                      << "1. add task\n"
                      << "2. view tasks\n"
                      << "3. rm task\n"
                      << "4. back to main menu\n"
                      << "select an option: ";
            std::getline(std::cin, input);
            if (input == "1") {
                std::cout << "enter task: ";
                std::getline(std::cin, input);
                todos.push_back(input);
                std::cout << "task added\n";
            } else if (input == "2") {
                if (todos.empty()) {
                    std::cout << "no tasks\n";
                } else {
                    for (size_t i = 0; i < todos.size(); ++i) {
                        std::cout << i + 1 << ". " << todos[i] << '\n';
                    }
                }
            } else if (input == "3") {
                if (todos.empty()) {
                    std::cout << "no tasks to rm\n";
                } else {
                    std::cout << "enter task number to rm: ";
                    std::getline(std::cin, input);
                    int index = std::stoi(input) - 1;
                    if (index >= 0 && index < static_cast<int>(todos.size())) {
                        todos.erase(todos.begin() + index);
                        std::cout << "task rm'd\n";
                    } else {
                        std::cout << "incorrect task number. no exist\n";
                    }
                }
            } else if (input == "4") {
                break;
            }
        }
    }

    static void calculator() {
        double a, b;
        char op;
        std::cout << "enter calculation (e.g., 5 + 3): ";
        std::cin >> a >> op >> b;
        std::cin.ignore();
        switch (op) {
            case '+': std::cout << "res: " << a + b << '\n'; break;
            case '-': std::cout << "res: " << a - b << '\n'; break;
            case '*': std::cout << "res: " << a * b << '\n'; break;
            case '/': 
                if (b != 0) std::cout << "res: " << a / b << '\n';
                else std::cout << "err: divide by zero\n";
                break;
            default: std::cout << "invalid operator\n";
        }
    }

    static void setTimer() {
        int seconds;
        std::cout << "enter time in seconds: ";
        std::cin >> seconds;
        std::cin.ignore();
        std::cout << "timer set for " << seconds << " seconds\n";
        std::this_thread::sleep_for(std::chrono::seconds(seconds));
        std::cout << "\time up!\n";
    }

    static void takeNotes() {
        std::string note;
        std::cout << "enter your note (press enter twice to finish):\n";
        std::string line;
        while (std::getline(std::cin, line) && !line.empty()) {
            note += line + '\n';
        }
        std::ofstream file("notes.txt", std::ios::app);
        if (file.is_open()) {
            file << getCurrentDateTime() << '\n' << note << "\n\n";
            std::cout << "note saved to notes.txt\n";
        } else {
            std::cout << "unable to save note\n";
        }
    }

    static void generateRandom() {
        int min, max;
        std::cout << "enter range (min max): ";
        std::cin >> min >> max;
        std::cin.ignore();
        std::random_device rd;
        std::mt19937 gen(rd());
        std::uniform_int_distribution<> dis(min, max);
        std::cout << "rand number: " << dis(gen) << '\n';
    }

    static void convertUnits() {
        std::cout << "aval conversions:\n"
                  << "1. cel to fahr\n"
                  << "2. kilo to mi\n"
                  << "3. kilo to lbs\n"
                  << "choose a conversion: ";
        std::string choice;
        std::getline(std::cin, choice);
        double value;
        std::cout << "enter value: ";
        std::cin >> value;
        std::cin.ignore();
        if (choice == "1") {
            std::cout << value << "°C = " << (value * 9/5) + 32 << "°F\n";
        } else if (choice == "2") {
            std::cout << value << " km = " << value * 0.621371 << " miles\n";
        } else if (choice == "3") {
            std::cout << value << " kg = " << value * 2.20462 << " lbs\n";
        } else {
                std::cout << "incorrect choice\n";
        }
    }
};

int main() {
    std::string command;
    std::cout << "welcome to the QOL CLI\n";
    QualityOfLife::showHelp();
    
    while (true) {
        std::cout << "\enter a cmd (or 'help' for options): ";
        std::getline(std::cin, command);
        
        if (command == "help") {
            QualityOfLife::showHelp();
        } else if (command == "todo") {
            QualityOfLife::manageTodo();
        } else if (command == "calc") {
            QualityOfLife::calculator();
        } else if (command == "timer") {
            QualityOfLife::setTimer();
        } else if (command == "notes") {
            QualityOfLife::takeNotes();
        } else if (command == "random") {
            QualityOfLife::generateRandom();
        } else if (command == "convert") {
            QualityOfLife::convertUnits();
        } else if (command == "quit") {
            std::cout << "bye\n";
            break;
        } else {
            std::cout << "unknown command. type 'help' for options\n";
        }
    }
    return 0;
}