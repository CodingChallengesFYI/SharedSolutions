#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int numberOfBytes(string filename) {
    ifstream file(filename);
    if(!file) {
        cerr << "Error Opening File " << filename << "\n";
        return 1;
    }
    file.seekg(0, ios::end);
    streamsize size = file.tellg();
    return size;
}

int numberOfLines(string filename) {
    ifstream file(filename);
    if(!file) {
        cerr << "Error Opening File " << filename << "\n";
        return 1;
    }
    int lines = 0;
    string line;
    while(getline(file, line)) {
        lines++;
    }
    return lines;
}

int numberOfWords(string filename) {
    ifstream file(filename);
    if(!file) {
        cerr << "Error Opening File " << filename << "\n";
        return 1;
    }
    string word;
    int wordCount = 0;
    while(file >> word) {
        wordCount++;
    }
    return wordCount++;
}

int numberOfChars(string filename) {
    ifstream file(filename);
    if(!file) {
        cerr << "Error Opening File " << filename << "\n";
        return 1;
    }
    char ch;
    int char_count = 0;
    while(file.get(ch)) {
        char_count++;
    }
    return char_count;
}

int main(int argc, char *argv[]) {
    string option = argv[1];
    string filename = argv[2];
    ifstream file(filename);
    if(!file) {
        cerr << "Error Opening File " << filename << "\n";
        return 1;
    }
    if(option == "-c") {
        cout << " " <<  numberOfBytes(filename) << " " << filename << "\n";
    } 
    else if(option == "-l"){
        cout << " " << numberOfLines(filename) << " " << filename << "\n";
    }
    else if(option == "-w") {
        cout << " " << numberOfWords(filename) << " " << filename << "\n";
    }
    else if(option == "-m") {
        cout << " " << numberOfChars(filename) << " " << filename << "\n";
    }
    else {
        cout << numberOfLines(filename) << " " << numberOfWords(filename) << " " << numberOfBytes(filename) << " " << filename << endl;
    }
    return 0;

} 

/*In this step your goal is to support being able to read from standard input if no filename is specified. If you’ve done it right your output should match this:

>cat test.txt | ccwc -l
7145
*/