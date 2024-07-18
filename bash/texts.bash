#!/bin/bash

if [ $# -eq 0 ]; then
    echo "Please provide a file name as an argument."
    exit 1
fi

file=$1

# exist file checker
if [ ! -f "$file" ]; then
    echo "File not found: $file"
    exit 1
fi

show_menu() {
    echo "select an operation:"
    echo "1. count words"
    echo "2. count lines"
    echo "3. conv to uppercase"
    echo "4. conv to lowercase"
    echo "5. find and replace"
    echo "6. leave"
}

while true; do
    show_menu
    read -p "enter your choice (1-6): " choice

    case $choice in
        1)
            word_count=$(wc -w < "$file")
            echo "word count: $word_count"
            ;;
        2)
            line_count=$(wc -l < "$file")
            echo "ln count: $line_count"
            ;;
        3)
            tr '[:lower:]' '[:upper:]' < "$file" > "${file}.upper"
            echo "uppercase version saved as ${file}.upper"
            ;;
        4)
            tr '[:upper:]' '[:lower:]' < "$file" > "${file}.lower"
            echo "lowercase version saved as ${file}.lower"
            ;;
        5)
            read -p "enter the word to find: " find_word
            read -p "enter the word to replace it with: " replace_word
            sed "s/$find_word/$replace_word/g" "$file" > "${file}.replaced"
            echo "result saved as ${file}.replaced"
            ;;
        6)
            echo "leaving..."
            exit 0
            ;;
        *)
            echo "invalid choice. don't try again. or do?"
            ;;
    esac

    echo
done