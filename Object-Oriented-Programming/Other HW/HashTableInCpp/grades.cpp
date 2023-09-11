// Name: Xinyi Cai
// USC NetID: xcai6647
// CSCI 455 PA5
// Spring 2023

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>

using namespace std;

//--------------------helper functions-------------------

// loop for different commands
void cmdLoop(Table * grades);
// print out the help messages
void helpMsg();
// things to do for insert command
void insertCmd(Table * grades);
// things to do for change command
void changeCmd(Table * grades);
// things to do for lookup command
void lookupCmd(Table * grades);
// things to do for remove command
void removeCmd(Table * grades);

int main(int argc, char * argv[]) {


   Table * grades;  // Table is dynamically allocated below, so we can call
                     // different constructors depending on input from the user.
   
   // optionally gets the hash table size from the command line
   if (argc > 1) {
      int hashSize = atoi(argv[1]);  // atoi converts c-string to int
      
      if (hashSize < 1) {
         cout << "Command line argument (hashSize) must be a positive number" 
              << endl;
         return 1;
      }

      grades = new Table(hashSize);

   }
   else {   // no command line args given -- use default table size
      grades = new Table();
   }


   grades->hashStats(cout);

   // add more code here
   // Reminder: use -> when calling Table methods, since grades is type Table*
    cmdLoop(grades);

    return 0;
}

void cmdLoop(Table * grades) {
    bool done = false;
    string command = "";
    while (!done) {
        cout << "cmd> ";
        cin >> command;
        if (command == "insert") {
            insertCmd(grades);
        }
        else if (command == "change") {
            changeCmd(grades);
        }
        else if (command == "lookup") {
            lookupCmd(grades);
        }
        else if (command == "remove") {
            removeCmd(grades);
        }
        else if (command == "print") {
            grades->printAll();
        }
        else if (command == "size") {
            cout << "size = " << grades->numEntries() << endl;;
        }
        else if (command == "stats") {
            grades->hashStats(cout);
        }
        else if (command == "help") {
            helpMsg();
        }
        else if (command == "quit") {
            done = true;
        }
        else {
            cout << "ERROR: invalid command" << endl;
            helpMsg();
        }
    }
}

// print out the help messages
void helpMsg() {
    cout<< "Valid commands are insert <name> <score>, " <<
        "change <name> <newscore>, lookup <name>, remove <name>, " <<
        "print, size, stats, help, quit " << endl;
}

// Insert this name and score in the grade table.
// If this name was already present,
// print a message to that effect, and don't do the insert.
// PRE: user input format : insert <name> <score>
void insertCmd(Table * grades) {
    string name;
    int score;
    cin >> name >> score;
    bool succeed = grades->insert(name, score);
    if (!succeed) {
        cout << "this name already exist" << endl;
    }
}

// Change the score for name. Print an appropriate message
// if this name isn't present.
// PRE: user input format : change <name> <newscore>
void changeCmd(Table * grades) {
    string key;
    int newscore;
    cin >> key >> newscore;
    int* score = grades->lookup(key);
    if (score == NULL) {
        cout << key << " does not exist. " << endl;
    }
    else {
        *score = newscore;
    }
}

// Lookup the name, and print out his or her score,
// or a message indicating that student is not in the table.
// PRE: user input format : lookup <name>
void lookupCmd(Table * grades) {
    string key;
    cin >> key;
    int* score = grades->lookup(key);
    if (score == NULL) {
        cout << key << " does not exist. " << endl;
    }
    else {
        cout << key << " " << *score << endl;
    }
}

// Remove this student. If this student wasn't in the grade table,
// print a message to that effect.
// PRE: user input format : remove <name>
void removeCmd(Table * grades) {
    string key;
    cin >> key;
    bool succeed = grades->remove(key);
    if (!succeed) {
        cout << key << " does not exist" << endl;
    }
}

