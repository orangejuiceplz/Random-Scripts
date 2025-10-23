#include <iostream>
#include <vector>

void insertionSort(std::vector<int>& arr) {
    int n = arr.size();
    for (int i = 1; i < n; i++) {
        int key = arr[i]; // The element to be inserted
        int j = i - 1;   // Index of the last element in the sorted subarray

        // Move elements of arr[0..i-1], that are greater than key,
        // to one position ahead of their current position
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key; // Place the key in its correct position
    }
}

// Utility function to print an array
void printArray(const std::vector<int>& arr) {
    for (int x : arr) {
        std::cout << x << " ";
    }
    std::cout << std::endl;
}

int main() {
    std::vector<int> arr = {12, 11, 13, 5, 6};
    std::cout << "Original array: ";
    printArray(arr);

    insertionSort(arr);

    std::cout << "Sorted array: ";
    printArray(arr);

    return 0;
}
