// Name: Xinyi Cai
// USC NetID: xcai6647
// CSCI 455 PA5
// Spring 2023

// Table.cpp  Table class implementation


#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>

// for hash function called in private hashCode method defined below
#include <functional>

using namespace std;


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************


Table::Table() {
    table = new ListType[HASH_SIZE];
    hashSize = HASH_SIZE;
}


Table::Table(unsigned int hSize) {
    table = new ListType[hSize];
    hashSize = hSize;
}


int * Table::lookup(const string &key) {
    int keyCode = hashCode(key);
    ListType findKey = find(table[keyCode], key);
    if (findKey == NULL) {
        return NULL;
    }
    else {
        return &(findKey->value);
    }
}


bool Table::remove(const string &key) {
    int keyCode = hashCode(key);
    return removeKey(table[keyCode], key);
}


bool Table::insert(const string &key, int value) {
    int keyCode = hashCode(key);
    return insertFront(table[keyCode], key, value);
}


int Table::numEntries() const {
    int num = 0;
    for (int i = 0; i < hashSize; i++) {
        num += getNum(table[i]);
    }
    return num;
}


void Table::printAll() const {
    for (int i = 0; i < hashSize; i++) {
        if (table[i] != NULL){ 
        printList(table[i]);
        }
    }
}


void Table::hashStats(ostream &out) const {
    out << "number of buckets: " << hashSize << endl;
    out << "number of entries: " << numEntries() << endl;

    int nonEmptyBucket = 0;
    int longestChain = 0;
    for (int i = 0; i < hashSize; i++) {
        if (getNum(table[i]) != 0) {
           nonEmptyBucket++;
           if (getNum(table[i]) > longestChain){
               longestChain = getNum(table[i]);
           }
        }
    }
    out << "number of non-empty buckets: " << nonEmptyBucket << endl;
    out << "longest chain: " << longestChain << endl;
}


// hash function for a string
// (we defined it for you)
// returns a value in the range [0, hashSize)
unsigned int Table::hashCode(const string &word) const {

   // Note: calls a std library hash function for string (it uses the good hash
   //   algorithm for strings that we discussed in lecture).
   return hash<string>()(word) % hashSize;

}


// add definitions for your private methods here

